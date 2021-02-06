package controllers;

import actions.ActionAuthentication;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Content;
import views.xml.*;

import javax.inject.Inject;
import java.util.Optional;

public class FavoritesHistoryController extends Controller {

    @Inject
    FormFactory formFactory;

    private final play.i18n.MessagesApi messagesApi;

    @Inject
    public FavoritesHistoryController(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    @Security.Authenticated(ActionAuthentication.class)
    public Result addRecipeToFavorites(Http.Request request) {
        Result response;

        Optional<String> recipeName = request.queryString("recipeName");
        Optional<String> token = request.header("Authentication");

        Messages messages = messagesApi.preferred(request);

        User userFinded = User.selectUserByToken(token.get());

        if(recipeName.isPresent() && !"".equals(recipeName.get())) {
            Recipe recipeFinded;

            String name = recipeName.get();

            recipeFinded = Recipe.selectRecipe(name);

            if (userFinded.getFavoritesHistory().getRecipes().size() != 0) {
                for(Recipe recipe : userFinded.getFavoritesHistory().getRecipes()) {
                    if(recipe.getName().equals(recipeFinded.getName())) {
                        ObjectNode result = Json.newObject();
                        result.put("success", false);
                        result.put("message",  messages.at("info.message-recipe-added", recipeFinded.getName()));
                        response = Results.ok(result);

                        return response;
                    }
                }
            }

            if(null != recipeFinded) {
                if (request.accepts("application/xml")) {
                    userFinded.getFavoritesHistory().getRecipes().add(recipeFinded);
                    userFinded.getFavoritesHistory().update();

                    Content content = recipeToFavorites.render(recipeFinded, messages);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    userFinded.getFavoritesHistory().getRecipes().add(recipeFinded);
                    userFinded.getFavoritesHistory().update();

                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message",  messages.at("info.message-recipe-added-favorites", recipeFinded.getName()));
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
                result.put("message", messages.at("error.recipe-not-exist", name));
                response = Results.notFound(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.search-factor"));
            response = Results.badRequest(result);
        }

        return response;
    }

    @Security.Authenticated(ActionAuthentication.class)
    public Result getRecipesFromFavoritesHistory(Http.Request request) {
        Result response;

        Messages messages = messagesApi.preferred(request);

        Optional<String> token = request.header("Authentication");

        User userFinded = User.selectUserByToken(token.get());

        if (request.accepts("application/xml")) {
            Content content = recipes.render(userFinded.getFavoritesHistory().getRecipes());
            response = Results.ok(content);
        } else if (request.accepts("application/json")) {
            ArrayNode jsonArray = Json.newArray();
            for (Recipe recipeFinded : userFinded.getFavoritesHistory().getRecipes()) {
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
                jsonArray.add(result);
            }
            response = Results.ok(jsonArray);
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.unsupported-format"));
            response = Results.badRequest(result);
        }

        return response;
    }

    @Security.Authenticated(ActionAuthentication.class)
    public Result deleteRecipeFromFavoritesHistory(Http.Request request) {
        Result response;
        Recipe recipe = null;
        Optional<String> recipeName = request.queryString("recipeName");
        Optional<String> token = request.header("Authentication");

        Messages messages = messagesApi.preferred(request);



        if(recipeName.isPresent() && !"".equals(recipeName.get())) {
            User userFinded = User.selectUserByToken(token.get());
            FavoritesHistory history = userFinded.getFavoritesHistory();

            for (Recipe r : history.getRecipes()) {
                if(recipeName.get().equals(r.getName())) {
                    history.getRecipes().remove(r);
                    recipe = r;
                    break;
                }
            }

            if(null != recipe) {
                userFinded.getFavoritesHistory().update();

                if (request.accepts("application/xml")) {
                    Content content = recipeDeletedFromFavorites.render(recipe, messages);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message",  messages.at("info.message-recipe-deleted-favorites", recipeName.get()));
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
                result.put("message", messages.at("error.favorite-recipe-not-exist", recipeName.get()));
                response = Results.notFound(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.search-factor"));
            response = Results.badRequest(result);
        }

        return response;
    }
}
