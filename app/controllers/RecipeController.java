package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ebean.DuplicateKeyException;
import models.Ingredient;
import models.Recipe;
import play.libs.Json;
import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.twirl.api.Content;
import javax.inject.Inject;
import java.util.ArrayList;
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
        for(int i = 0; i < Recipe.typesCollection.size(); i++) {
            if(Recipe.typesCollection.get(i).equals(recipe.getType().toLowerCase())) {
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

            //Genera el JSON con los steps
            recipe.setStepsJson();
            try {
                //Convertimos los alérgenos en un JSON para que los alérgenos se almacenen coreectamente en BBDD
                for(Ingredient i : recipe.getIngredients()) {
                    i.setAllergensJson();
                }

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
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "Tipo de la receta no permitido, a continuación se muestran los tipos permitidos");
            for (int i = 0; i < Recipe.typesCollection.size(); i++) {
                result.put("tipo " + (i+1), Recipe.typesCollection.get(i));
            }
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
        int counter = -1;
        Recipe recipe;

        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        recipe = form.get();

        Recipe recipeFinded = Recipe.selectRecipe(recipe.getName());

        if (null != recipeFinded) {
            boolean typeFinded = false;
            for(int i = 0; i < Recipe.typesCollection.size(); i++) {
                if(Recipe.typesCollection.get(i).equals(recipe.getType().toLowerCase())) {
                    typeFinded = true;
                    break;
                }
            }
            if(typeFinded) {
                recipe.setStepsJson();
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
            List<Recipe> recipesFinded = new ArrayList<>();

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

            if(null == recipesFinded || recipesFinded.isEmpty()) {
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
