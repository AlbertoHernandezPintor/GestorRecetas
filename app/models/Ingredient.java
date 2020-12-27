package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints.Required;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingredient extends Model {
    public static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    @Id
    private String name;

    private String type;

    @ManyToMany(mappedBy = "ingredients")
    public List<Recipe> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
