package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {
    public static final Finder<Long, User> find = new Finder<>(User.class);

    @Constraints.Required(message="error.user-username-required")
    @Id
    private String username;

    @Constraints.Required(message="error.user-type-required")
    private String type;

    private String token;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public static User selectUserByToken(String token) {
        return find.query().where().eq("token", token).findOne();
    }

    public static User selectUserByUsername(String username) {
        return find.query().where().eq("username", username).findOne();
    }
}
