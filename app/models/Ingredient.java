package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.Finder;
import io.ebean.Model;
import play.libs.Json;

import javax.persistence.Entity;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingredient extends Model {
    public static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    @Id
    private String name;
    private List<String> allergens;
    private String type;

    @ManyToMany(mappedBy = "ingredients")
    public List<Recipe> recipes;

    private String allergensJson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public String getAllergensJson() {
        return allergensJson;
    }

    public void setAllergensJson() {
        ObjectNode result = Json.newObject();
        for(int i = 0; i < this.allergens.size(); i++) {
            result.put("Alérgeno " + (i+1), this.allergens.get(i));
        }
        this.allergensJson = result.toString();
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
