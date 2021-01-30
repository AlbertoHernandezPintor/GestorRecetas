package controllers;

import actions.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DuplicateKeyException;
import models.Type;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Content;
import views.xml.typeCreated;
import views.xml.typeDeleted;
import views.xml.types;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import play.cache.SyncCacheApi;


public class TypeController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    private SyncCacheApi cache;

    private final play.i18n.MessagesApi messagesApi;

    @Inject
    public TypeController(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    @Security.Authenticated(AdminActionAuthentication.class)
    public Result createType(Http.Request request) {
        Result response;
        Type type;

        Messages messages = messagesApi.preferred(request);

        Form<Type> form = formFactory.form(Type.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        type = form.get();

        try {
            type.save();
            cache.set("types-cache", Type.selectTypesList());
        } catch(DuplicateKeyException e) {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.message-repeated-type", type.getName()));
            return Results.status(CONFLICT, result);
        }

        if (request.accepts("application/xml")) {
            Content content = typeCreated.render(type, messages);
            response = Results.ok(content);
        } else if (request.accepts("application/json")) {
            ObjectNode result = Json.newObject();
            result.put("success", true);
            result.put("message", messages.at("info.message-type-created", type.getName()));
            response = Results.ok(result);
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.unsupported-format"));
            response = Results.badRequest(result);
        }

        return response;
    }

    public Result getTypes(Http.Request request) {
        Result response;

        List<Type> recipeTypes = cache.getOrElseUpdate(
                "types-cache",
                () -> {
                    return Type.selectTypesList();
                }
        );

        Messages messages = messagesApi.preferred(request);

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
            result.put("message", messages.at("error.unsupported-format"));
            response = Results.badRequest(result);
        }
        return response;
    }

    @Security.Authenticated(AdminActionAuthentication.class)
    public Result deleteType(Http.Request request) {
        Result response;

        Optional<String> typeName = request.queryString("name");

        Messages messages = messagesApi.preferred(request);

        if(typeName.isPresent() && !"".equals(typeName.get())) {
            String name = typeName.get();

            Type typeFinded = Type.selectType(name);

            if(null == typeFinded) {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.type-not-exist", typeName.get()));
                response = Results.notFound(result);
            } else {
                typeFinded.delete();
                if (request.accepts("application/xml")) {
                    Content content = typeDeleted.render(typeFinded, messages);
                    response = Results.ok(content);
                } else if (request.accepts("application/json")) {
                    ObjectNode result = Json.newObject();
                    result.put("success", true);
                    result.put("message", messages.at("info.message-type-deleted", typeFinded.getName()));
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
}
