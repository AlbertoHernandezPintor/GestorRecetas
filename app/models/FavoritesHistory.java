package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class FavoritesHistory extends Model {
    public static final Finder<Long, FavoritesHistory> find = new Finder<>(FavoritesHistory.class);

    @Id
    public long id;

    @OneToOne(mappedBy = "favoritesHistory")
    public User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
