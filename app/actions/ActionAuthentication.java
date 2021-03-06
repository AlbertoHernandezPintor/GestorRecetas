package actions;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Http;
import play.mvc.Security;
import models.User;

import javax.inject.Inject;
import java.util.Optional;

public class ActionAuthentication extends Security.Authenticator {

    private final play.i18n.MessagesApi messagesApi;

    @Inject
    public ActionAuthentication(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    @Override
    public Optional<String> getUsername(Http.Request req) {
        Optional<String> token = req.header("Authentication");

        if (!token.isPresent()) {
            return Optional.empty();
        } else {
            User user = User.selectUserByToken(token.get());

            if (null != user) {
                return Optional.of("Correcto");
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public Result onUnauthorized(Http.Request req) {
        Messages messages = messagesApi.preferred(req);

        return Results.status(401, messages.at("error.Unauthorized"));
    }
}
