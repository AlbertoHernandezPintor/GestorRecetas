package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User extends Model {
    public static final Finder<Long, User> find = new Finder<>(User.class);

    @Constraints.Required(message="error.user-username-required")
    @Id
    private String username;

    @Constraints.Required(message="error.user-type-required")
    private String type;

    private String token;

    @OneToOne(cascade = CascadeType.ALL)
    public FavoritesHistory favoritesHistory;

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

    public FavoritesHistory getFavoritesHistory() { return favoritesHistory; }

    public void setFavoritesHistory(FavoritesHistory favoritesHistory) { this.favoritesHistory = favoritesHistory; }

    public static User selectUserByToken(String token) {
        return find.query().where().eq("token", token).findOne();
    }

    public static User selectUserByUsername(String username) {
        return find.query().where().eq("username", username).findOne();
    }
}
