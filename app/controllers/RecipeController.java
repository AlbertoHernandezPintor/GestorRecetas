package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DuplicateKeyException;
import models.*;
import play.cache.SyncCacheApi;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.twirl.api.Content;
import javax.inject.Inject;
import java.util.*;

import views.xml.*;

public class RecipeController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    private SyncCacheApi cache;

    private final play.i18n.MessagesApi messagesApi;

    @Inject
    public RecipeController(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    public Result createRecipe(Http.Request request) {
        Result response;
        Recipe recipe;

        Messages messages = messagesApi.preferred(request);

        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        recipe = form.get();

        boolean typeFinded = false;
        List<Type> recipeTypes;
        recipeTypes = cache.getOrElseUpdate(
                "types-cache",
                () -> {
                    return Type.selectTypesList();
                }
        );
        for(Type type : recipeTypes) {
            if(type.getName().equals(recipe.getType())) {
                typeFinded = true;
                break;
            }
        }

        if(typeFinded) {
            //si el ingrediente ya existe, se actualiza para que se actualice la relación N:M
            for(Ingredient ingredient : recipe.getIngredients()) {
                if(null != Ingredient.selectIngredient(ingredient.getName())) {
                    ingredient.update();
                }
            }

            //si el alérgeno ya existe, se actualiza para que se actualice la relación N:M
            for(Allergen allergen : recipe.getAllergens()) {
                if(null != Allergen.selectAllergen(allergen.getName())) {
                    allergen.update();
                }
            }

            try {
                recipe.save();
            } catch(DuplicateKeyException e) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.message-repeated-recipe", recipe.getName()));
                return Results.status(CONFLICT, result);
            }

            if (request.accepts("application/xml")) {
                Content content = recipeCreated.render(recipe, messages);
                response = Results.ok(content);
            } else if (request.accepts("application/json")) {
                ObjectNode result = Json.newObject();
                result.put("success", true);
                result.put("message", messages.at("info.message-recipe-created", recipe.getName()));
                response = Results.ok(result);
            } else {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.unsupported-format"));
                response = Results.badRequest(result);
            }
        } else {
            ArrayNode jsonArray = Json.newArray();
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.type-not-allowed"));
            for (Type type : recipeTypes) {
                jsonArray.add(type.getName());
            }
            result.put("tipos", jsonArray);
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result getRecipe(Http.Request request) {
        Result response;

        Messages messages = messagesApi.preferred(request);

        Optional<String> recipeName = request.queryString("name");

        if(recipeName.isPresent() && !"".equals(recipeName.get())) { //Comprobar "" porque si no puede hacer búsquedas de nombres en blanco
            Recipe recipeFinded;

            String name = recipeName.get();

            recipeFinded = Recipe.selectRecipe(name);

            if (request.accepts("application/xml")) {
                Content content = recipe.render(recipeFinded);
                response = Results.ok(content);
            } else if (request.accepts("application/json")) {
                ObjectNode result = Json.newObject();
                result.put("name", recipeFinded.getName());
                result.put("type", recipeFinded.getType());
                result.put("time", recipeFinded.getTime());
                result.put("dififculty", recipeFinded.getDifficulty());

                ArrayNode ingredients = Json.newArray();
                for(Ingredient ingredient : recipeFinded.getIngredients()) {
                    ObjectNode ingredientJson = Json.newObject();
                    ingredientJson.put("name", ingredient.getName());
                    ingredientJson.put("type", ingredient.getType());
                    ingredients.add(ingredientJson);
                }
                result.put("ingredients", ingredients);

               ArrayNode allergens = Json.newArray();
                for(Allergen allergen : recipeFinded.getAllergens()) {
                    ObjectNode allergenJson = Json.newObject();
                    allergenJson.put("name", allergen.getName());
                    allergenJson.put("diseases", allergen.getDiseases());
                    allergens.add(allergenJson);
                }
                result.put("allergens", allergens);

                ArrayNode steps = Json.newArray();
                for(Step step : recipeFinded.getSteps()) {
                    ObjectNode stepJson = Json.newObject();
                    stepJson.put("name", step.getName());
                    stepJson.put("description", step.getDescription());
                    steps.add(stepJson);
                }
                result.put("steps", steps);
                response = Results.ok(result);
            } else {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.unsupported-format"));
                response = Results.badRequest(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.search-factor"));
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result patchRecipe(Http.Request request) {
        Result response;
        Recipe recipe;

        Messages messages = messagesApi.preferred(request);

        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        recipe = form.get();

        Recipe recipeFinded = Recipe.selectRecipe(recipe.getName());

        if (null != recipeFinded) {
            boolean typeFinded = false;
            List<Type> recipeTypes;
            recipeTypes = cache.getOrElseUpdate(
                    "types-cache",
                    () -> {
                        return Type.selectTypesList();
                    }
            );
            for(Type type : recipeTypes) {
                if(type.getName().equals(recipe.getType())) {
                    typeFinded = true;
                    break;
                }
            }

            if(typeFinded) {
                recipe.update();
                if (request.accepts("application/xml")) {
                    Content content = recipePatched.render(recipe, messages);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", messages.at("info.message-recipe-modified", recipe.getName()));
                    response = Results.ok(result);
                } else {
                    ObjectNode result = Json.newObject();
                    result.put("success", false);
                    result.put("message", messages.at("error.unsupported-format"));
                    response = Results.badRequest(result);
                }
            } else {
                ArrayNode jsonArray = Json.newArray();
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.type-not-allowed"));
                for (Type type : recipeTypes) {
                    jsonArray.add(type.getName());
                }
                result.put("tipos", jsonArray);
                response = Results.badRequest(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.recipe-not-exist", recipe.getName()));
            response = Results.notFound(result);
        }

        return response;
    }

    public Result deleteRecipe(Http.Request request) {
        Result response;
        Optional<String> recipeName = request.queryString("name");

        Messages messages = messagesApi.preferred(request);

        if(recipeName.isPresent() && !"".equals(recipeName.get())) {
            String name = recipeName.get();

            Recipe recipeFinded = Recipe.selectRecipe(name);

            if(null == recipeFinded) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.recipe-not-exist", recipeName.get()));
                response = Results.notFound(result);
            } else {
                recipeFinded.delete();
                if (request.accepts("application/xml")) {
                    Content content = recipeDeleted.render(recipeFinded, messages);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message",  messages.at("info.message-recipe-deleted", recipeFinded.getName()));
                    response = Results.ok(result);
                } else {
                    ObjectNode result = Json.newObject();
                    result.put("success", false);
                    result.put("message", messages.at("error.unsupported-format"));
                    response = Results.badRequest(result);
                }
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.search-factor"));
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result getRecipes(Http.Request request) {
        Result response;

        Optional<String> recipeType = request.queryString("type");
        Optional<String> recipeTime = request.queryString("time");
        Optional<String> recipeDifficulty = request.queryString("difficulty");

        Messages messages = messagesApi.preferred(request);

        if(recipeType.isPresent() || recipeTime.isPresent() || recipeDifficulty.isPresent()) {
            List<Recipe> recipesFinded;

            if(recipeType.isPresent()) {
                String type = recipeType.get();

                recipesFinded = Recipe.selectRecipesList("TYPE", type);
            } else if (recipeTime.isPresent()) {
                String time = recipeTime.get();

                recipesFinded = Recipe.selectRecipesList("time", time);
            } else {
                String difficulty = recipeDifficulty.get();

                recipesFinded = Recipe.selectRecipesList("difficulty", difficulty);
            }

            if(recipesFinded.isEmpty()) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.no-recipe-found"));
                response = Results.notFound(result);
            } else {
                if (request.accepts("application/xml")) {
                    Content content = recipes.render(recipesFinded);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    JsonNode result = Json.toJson(recipesFinded);
                    response = Results.ok(result);
                } else {
                    ObjectNode result = Json.newObject();
                    result.put("success", false);
                    result.put("message", messages.at("error.unsupported-format"));
                    response = Results.badRequest(result);
                }
            }
        } else {
            //El if que había aquí sobraba
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.invalid-parameter")); //Se cambia el mensaje que devuelve
            response = Results.notFound(result);
        }

        return response;
    }
}
