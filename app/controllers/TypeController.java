package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DuplicateKeyException;
import models.Type;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import play.twirl.api.Content;
import views.xml.typeCreated;
import views.xml.typeDeleted;
import views.xml.types;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TypeController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result createType(Http.Request request) {
        Result response;
        Type type;

        Form<Type> form = formFactory.form(Type.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        type = form.get();

        try {
            type.save();
        } catch(DuplicateKeyException e) {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "El tipo " + type.getName() + " ya existe");
            return Results.status(CONFLICT, result);
        }

        if (request.accepts("application/xml")) {
            Content content = typeCreated.render(type);
            response = Results.ok(content);
        } else if (request.accepts("application/json")) {
            ObjectNode result = Json.newObject();
            result.put("success", true);
            result.put("message", "El tipo " + type.getName() + " ha sido creado con éxito");
            response = Results.ok(result);
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", "Formato no soportado");
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result getTypes(Http.Request request) {
        Result response;

        List<Type> recipeTypes;
        recipeTypes = Type.selectTypesList();

        if (request.accepts("application/xml")) {
            Content content = types.render(recipeTypes);
            response = Results.ok(content);
        } else if (request.accepts("application/json")) {
            ArrayNode jsonArray = Json.newArray();
            ObjectNode result = Json.newObject();
            for(Type type : recipeTypes) {
                ObjectNode typeJson = Json.newObject();
                typeJson.put("name", type.getName());
                typeJson.put("description", type.getDescription());
                jsonArray.add(typeJson);
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

    public Result deleteType(Http.Request request) {
        Result response;

        Optional<String> typeName = request.queryString("name");

        if(typeName.isPresent() && !"".equals(typeName.get())) {
            String name = typeName.get();

            Type typeFinded = Type.selectType(name);

            if(null == typeFinded) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", "El tipo " + typeName.get() + " no existe");
                response = Results.notFound(result);
            } else {
                typeFinded.delete();
                if (request.accepts("application/xml")) {
                    Content content = typeDeleted.render(typeFinded);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", "El tipo " + typeFinded.getName() + " ha sido eliminado con éxito");
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
}
