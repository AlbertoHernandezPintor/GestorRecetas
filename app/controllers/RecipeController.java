package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DuplicateKeyException;
import models.Allergen;
import models.Ingredient;
import models.Recipe;
import models.Type;
import play.libs.Json;
import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.twirl.api.Content;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import views.xml.*;

public class RecipeController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result createRecipe(Http.Request request) {
        Result response;
        boolean find = false;
        Recipe recipe;

        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        recipe = form.get();

        boolean typeFinded = false;
        List<Type> recipeTypes;
        recipeTypes = Type.selectTypesList();
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
                result.put("message", "La receta " + recipe.getName() + " ya existe");
                return Results.status(CONFLICT, result);
            }

            if (request.accepts("application/xml")) {
                Content content = recipeCreated.render(recipe);
                response = Results.ok(content);
            } else if (request.accepts("application/json")) {
                ObjectNode result = Json.newObject();
                result.put("success", true);
                result.put("message", "La receta " + recipe.getName() + " ha sido creada con éxito");
                response = Results.ok(result);
            } else {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "Formato no soportado");
                response = Results.badRequest(result);
            }
        } else {
            ArrayNode jsonArray = Json.newArray();
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "Tipo de la receta no permitido, a continuación se muestran los tipos permitidos");
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
        boolean find = false;

        Optional<String> recipeName = request.queryString("name");

        if(recipeName.isPresent() && !"".equals(recipeName.get())) { //Comprobar "" porque si no puede hacer búsquedas de nombres en blanco
            Recipe recipeFinded;

            String name = recipeName.get();

            recipeFinded = Recipe.selectRecipe(name);

            if (request.accepts("application/xml")) {
                Content content = recipe.render(recipeFinded);
                response = Results.ok(content);
            } else if (request.accepts("application/json")) {
                JsonNode result = Json.toJson(recipeFinded);
                response = Results.ok(result);
            } else {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "Formato no soportado");
                response = Results.badRequest(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "No se ha elegido ningún factor de búsqueda");
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result patchRecipe(Http.Request request) {
        Result response;
        Recipe recipe;

        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        recipe = form.get();

        Recipe recipeFinded = Recipe.selectRecipe(recipe.getName());

        if (null != recipeFinded) {
            boolean typeFinded = false;
            List<Type> recipeTypes;
            recipeTypes = Type.selectTypesList();
            for(Type type : recipeTypes) {
                if(type.getName().equals(recipe.getType())) {
                    typeFinded = true;
                    break;
                }
            }

            if(typeFinded) {
                recipe.update();
                if (request.accepts("application/xml")) {
                    Content content = recipePatched.render(recipe);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", "La receta " + recipe.getName() + " ha sido modificada con éxito");
                    response = Results.ok(result);
                } else {
                    ObjectNode result = Json.newObject();
                    result.put("success", false);
                    result.put("message", "Formato no soportado");
                    response = Results.badRequest(result);
                }
            } else {
                ArrayNode jsonArray = Json.newArray();
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "Tipo de la receta no permitido, a continuación se muestran los tipos permitidos");
                for (Type type : recipeTypes) {
                    jsonArray.add(type.getName());
                }
                result.put("tipos", jsonArray);
                response = Results.badRequest(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "La receta " + recipe.getName() + " no existe");
            response = Results.notFound(result);
        }

        return response;
    }

    public Result deleteRecipe(Http.Request request) {
        Result response;
        Optional<String> recipeName = request.queryString("name");

        if(recipeName.isPresent() && !"".equals(recipeName.get())) {
            String name = recipeName.get();

            Recipe recipeFinded = Recipe.selectRecipe(name);

            if(null == recipeFinded) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "La receta " + recipeName.get() + " no existe");
                response = Results.notFound(result);
            } else {
                recipeFinded.delete();
                if (request.accepts("application/xml")) {
                    Content content = recipeDeleted.render(recipeFinded);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", "La receta " + recipeFinded.getName() + " ha sido eliminada con éxito");
                    response = Results.ok(result);
                } else {
                    ObjectNode result = Json.newObject();
                    result.put("success", false);
                    result.put("message", "Formato no soportado");
                    response = Results.badRequest(result);
                }
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "No se ha elegido ningún factor de búsqueda");
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result getRecipes(Http.Request request) {
        Result response;
        boolean find = false;

        Optional<String> recipeType = request.queryString("type");
        Optional<String> recipeTime = request.queryString("time");
        Optional<String> recipeDifficulty = request.queryString("difficulty");

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
                result.put("message", "No se ha encontrado ninguna receta para los parámetros establecidos");
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
                    result.put("message", "Formato no soportado");
                    response = Results.badRequest(result);
                }
            }
        } else {
            //El if que había aquí sobraba
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "El parámetro establecido no es válido"); //Se cambia el mensaje que devuelve
            response = Results.notFound(result);
        }

        return response;
    }
}
