package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.Recipe;
import play.libs.Json;
import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.twirl.api.Content;
import javax.inject.Inject;
import java.util.ArrayList;
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
        } else {
            recipe = form.get();

            for (Recipe currentRecipe : Recipe.recipesCollection) {
                if (recipe.getName().equals(currentRecipe.getName())) {
                    find = true;
                    break;
                }
            }
        }

        if (find) {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "La receta " + recipe.getName() + " ya existe");
            response = Results.status(CONFLICT, result);
        } else {
            boolean typeFinded = false;
            for(int i = 0; i < Recipe.typesCollection.size(); i++) {
                if(Recipe.typesCollection.get(i).equals(recipe.getType().toLowerCase())) {
                    typeFinded = true;
                    break;
                }
            }

            if(typeFinded) {
                if (request.accepts("application/xml")) {
                    Recipe.recipesCollection.add(recipe);
                    Content content = recipeCreated.render(recipe);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    Recipe.recipesCollection.add(recipe);
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
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "Tipo de la receta no permitido, a continuación se muestran los tipos permitidos");
                for (int i = 0; i < Recipe.typesCollection.size(); i++) {
                    result.put("tipo " + (i+1), Recipe.typesCollection.get(i));
                }
                response = Results.badRequest(result);
            }
        }

        return response;
    }

    public Result getRecipe(Http.Request request) {
        Result response;
        boolean find = false;

        Optional<String> recipeName = request.queryString("name");

        if(recipeName.isPresent()) {
            Recipe recipeFinded = new Recipe();

            String name = recipeName.get();

            for (Recipe currentRecipe: Recipe.recipesCollection) {
                if (name.equals(currentRecipe.getName())) {
                    find = true;
                    recipeFinded = currentRecipe;
                    break;
                }
            }

            if(!find) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "La receta " + recipeName.get() + " no existe");
                response = Results.notFound(result);
            } else {
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
        int counter = -1;
        Recipe recipe;

        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        recipe = form.get();
        for (int i = 0; i < Recipe.recipesCollection.size(); i++) {
            if (recipe.getName().equals(Recipe.recipesCollection.get(i).getName())) {
                counter = i;
                break;
            }
        }
        if (counter != -1) {
            boolean typeFinded = false;
            for(int i = 0; i < Recipe.typesCollection.size(); i++) {
                if(Recipe.typesCollection.get(i).equals(recipe.getType().toLowerCase())) {
                    typeFinded = true;
                    break;
                }
            }
            if(typeFinded) {
                Recipe.recipesCollection.set(counter, recipe);
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
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "Tipo de la receta no permitido, a continuación se muestran los tipos permitidos");
                for (int i = 0; i < Recipe.typesCollection.size(); i++) {
                    result.put("tipo " + (i+1), Recipe.typesCollection.get(i));
                }
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
        boolean find = false;

        Optional<String> recipeName = request.queryString("name");

        if(recipeName.isPresent()) {
            Recipe recipeFinded = new Recipe();

            String name = recipeName.get();

            for (Recipe currentRecipe: Recipe.recipesCollection) {
                if (name.equals(currentRecipe.getName())) {
                    find = true;
                    recipeFinded = currentRecipe;
                    break;
                }
            }

            if(!find) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "La receta " + recipeName.get() + " no existe");
                response = Results.notFound(result);
            } else {
                if (request.accepts("application/xml")) {
                    Recipe.recipesCollection.remove(recipeFinded);
                    Content content = recipeDeleted.render(recipeFinded);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    Recipe.recipesCollection.remove(recipeFinded);
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
            ArrayList<Recipe> recipesFinded = new ArrayList<>();

            if(recipeType.isPresent()) {
                String type = recipeType.get();

                for (Recipe currentRecipe: Recipe.recipesCollection) {
                    if (type.equals(currentRecipe.getType())) {
                        find = true;
                        recipesFinded.add(currentRecipe);
                    }
                }
            } else if (recipeTime.isPresent()) {
                String time = recipeTime.get();

                for (Recipe currentRecipe: Recipe.recipesCollection) {
                    if (time.equals(currentRecipe.getTime())) {
                        find = true;
                        recipesFinded.add(currentRecipe);
                    }
                }
            } else {
                String difficulty = recipeDifficulty.get();

                for (Recipe currentRecipe: Recipe.recipesCollection) {
                    if (difficulty.equals(currentRecipe.getDifficulty())) {
                        find = true;
                        recipesFinded.add(currentRecipe);
                    }
                }
            }

            if(!find) {
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
            if(Recipe.recipesCollection.size() > 0) {
                if (request.accepts("application/xml")) {
                    Content content = recipes.render(Recipe.recipesCollection);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    JsonNode result = Json.toJson(Recipe.recipesCollection);
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
                result.put("message", "No se ha encontrado ninguna receta");
                response = Results.notFound(result);
            }
        }

        return response;
    }

    public Result getTypes(Http.Request request) {
        Result response;
        if (request.accepts("application/xml")) {
            Content content = types.render(Recipe.typesCollection);
            response = Results.ok(content);
        } else if (request.accepts("application/json")) {
            ArrayNode jsonArray = Json.newArray();
            ObjectNode result = Json.newObject();
            for(String type : Recipe.typesCollection) {
                jsonArray.add(type);
            }
            result.put("tipos", jsonArray);
            response = Results.ok(result);
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "Formato no soportado");
            response = Results.badRequest(result);
        }
        return response;
    }

    public Result createType(Http.Request request) {
        Result response;
        boolean typeFinded = false;
        Optional<String> recipeType = request.queryString("type");
        if(recipeType.isPresent() && !recipeType.get().equals("")) {
            String newType = recipeType.get();

            for(int i = 0; i < Recipe.typesCollection.size(); i++) {
                if(Recipe.typesCollection.get(i).equals(newType)) {
                    typeFinded = true;
                    break;
                }
            }
            if(!typeFinded) {
                Recipe.typesCollection.add(newType);
                if (request.accepts("application/xml")) {
                    Content content = typeCreated.render(newType);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", "El tipo " + newType + " ha sido añadido con éxito");
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
                result.put("message", "Este tipo de receta ya existe");
                response = Results.badRequest(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "No ha especificado ningún tipo para añadir");
            response = Results.badRequest(result);
        }
        return response;
    }

    public Result deleteType(Http.Request request) {
        Result response;
        int counter = -1;
        Optional<String> recipeType = request.queryString("type");

        if(recipeType.isPresent() && !recipeType.get().equals("")) {
            String newType = recipeType.get();

            for(int i = 0; i < Recipe.typesCollection.size(); i++) {
                if(Recipe.typesCollection.get(i).equals(newType)) {
                    counter = i;
                    break;
                }
            }
            if(counter != -1) {
                Recipe.typesCollection.remove(counter);
                if (request.accepts("application/xml")) {
                    Content content = typeDeleted.render(newType);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", "El tipo " + newType + " ha sido eliminado con éxito");
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
                result.put("message", "El tipo expecificado no existe");
                response = Results.badRequest(result);
            }
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "No ha especificado ningún tipo para eliminar");
            response = Results.badRequest(result);
        }
        return response;
    }
}
