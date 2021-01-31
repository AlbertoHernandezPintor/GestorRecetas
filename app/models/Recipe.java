package models;

import java.util.List;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import validators.*;
import javax.persistence.*;

@Entity
public class Recipe extends Model {
    public static final Finder<Long, Recipe> find = new Finder<>(Recipe.class);

    @Required(message="error.recipe-name-required")
    @Id
    private String name;

    @Required(message="error.recipe-type-required")
    private String type;

    @Required(message="error.recipe-time-required")
    @Pattern(value = "^[0-9]+$", message = "error.recipe-time-number")
    private String time;

    @Required(message="error.recipe-difficulty-required")
    private String difficulty;

    @ManyToMany(cascade = CascadeType.ALL)
    @Required(message="error.recipe-ingredient-required")
    @Constraints.ValidateWith(IngredientValidator.class)
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.ALL)
    @Constraints.ValidateWith(AllergenValidator.class)
    private List<Allergen> allergens;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    @Required(message="error.recipe-step-required")
    @Constraints.ValidateWith(StepValidator.class)
    private List<Step> steps;

    @ManyToMany(mappedBy = "recipes")
    public List<FavoritesHistory> favoritesHistories;

    public Recipe() {

    }

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public List<FavoritesHistory> getFavoritesHistories() {
        return favoritesHistories;
    }

    public void setFavoritesHistories(List<FavoritesHistory> favoritesHistories) {
        this.favoritesHistories = favoritesHistories;
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
