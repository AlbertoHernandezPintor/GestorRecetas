package controllers;

import actions.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DuplicateKeyException;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Content;
import javax.inject.Inject;
import views.xml.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        user.setToken(token);


        List<Recipe> recipes = new ArrayList<>();
        FavoritesHistory favoritesHistory = new FavoritesHistory();
        favoritesHistory.setUser(user);
        favoritesHistory.setRecipes(recipes);
        favoritesHistory.update();

        user.setFavoritesHistory(favoritesHistory);
        user.save();
        System.out.println("Ya se ha guardado el usuario con el token: " + user.getToken());
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

    @Security.Authenticated(ActionAuthentication.class)
    public Result deleteUser(Http.Request request) {
        Result response;
        Optional<String> username = request.queryString("username");
        Optional<String> token = request.header("Authentication");

        Messages messages = messagesApi.preferred(request);

        if(username.isPresent() && !"".equals(username.get())) {
            User userFinded = User.selectUserByUsername(username.get());

            if (null != userFinded) {
                if (token.get().equals(userFinded.getToken()) || token.get().startsWith("admin")) {
                    userFinded.delete();
                    if (request.accepts("application/xml")) {
                        Content content = userDeleted.render(userFinded, messages);
                        response = Results.ok(content);
                    } else if (request.accepts("application/json")) {
                        ObjectNode result = Json.newObject();
                        result.put("success", true);
                        result.put("message",  messages.at("info.message-user-deleted", username.get()));
                        response = Results.ok(result);
                    } else {
                        ObjectNode result = Json.newObject();
                        result.put("success", false);
                        result.put("message", messages.at("error.unsupported-format"));
                        response = Results.badRequest(result);
                    }
                } else {
                    return Results.status(401, messages.at("error.Unauthorized"));
                }
            } else {
                ObjectNode result = Json.newObject();
                result.put("success", false);
                result.put("message", messages.at("error.user-not-exists", username.get()));
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
