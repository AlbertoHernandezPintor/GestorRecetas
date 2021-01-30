package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DuplicateKeyException;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import play.twirl.api.Content;
import javax.inject.Inject;
import views.xml.*;

public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    private final play.i18n.MessagesApi messagesApi;

    @Inject
    public UserController(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    public Result registerUser(Http.Request request) {
        Result response;
        User user;

        Messages messages = messagesApi.preferred(request);

        Form<User> form = formFactory.form(User.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return Results.status(CONFLICT, form.errorsAsJson());
        }
        user = form.get();

        try {
            user.save();
        } catch(DuplicateKeyException e) {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.message-repeated-user", user.getUsername()));
            return Results.status(CONFLICT, result);
        }

        String token = "";
        if (user.getType().equals("user")) {
            long randomEndToken = Math.round(Math.random()*99999999);
            token = "user-" + user.getUsername() + String.valueOf(randomEndToken);
        } else if (user.getType().equals("admin")) {
            long randomEndToken = Math.round(Math.random()*99999999);
            token = "admin-" + user.getUsername() + String.valueOf(randomEndToken);
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.message-type-user", user.getType()));
            return Results.status(BAD_REQUEST, result);
        }

        if (request.accepts("application/xml")) {
            Content content = userRegister.render(user, messages, token);
            response = Results.ok(content);
        } else if (request.accepts("application/json")) {
            ObjectNode result = Json.newObject();
            result.put("success", true);
            result.put("message", messages.at("info.message-user-created", user.getUsername()));
            result.put("token", token);
            response = Results.ok(result);
        } else {
            ObjectNode result = Json.newObject();
            result.put("success", false);
            result.put("message", messages.at("error.unsupported-format"));
            response = Results.badRequest(result);
        }

        return response;
    }
}
