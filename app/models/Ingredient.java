package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient extends Model {
    public static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    @Id
    private String name;
    private ArrayList<String> allergens;
    private String type;

    @ManyToMany(mappedBy = "ingredients")
    public List<Recipe> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
        this.allergens = allergens;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Ingredient selectIngredient(String name) {
        return find.query().where().eq("name", name).findOne();
    }
}
