package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.Finder;
import io.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

@Entity
public class Recipe extends Model {
    public static final Finder<Long, Recipe> find = new Finder<>(Recipe.class);

    @Id
    private String name;
    private String type;
    private String time;
    private String difficulty;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Allergen> allergens;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Step> steps;

    public Recipe() {

    }

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public static Recipe selectRecipe(String name) {
        return find.query().where().eq("name", name).findOne();
    }

    public static List<Recipe> selectRecipesList(String property, String value) {
        System.out.println("Se busca por: " + property + "y por: " + value);
        return find.query().where().eq(property, value).findList();
    }
}
