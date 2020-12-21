package models;

import java.util.ArrayList;
import java.util.Arrays;
import io.ebean.Model;
import javax.persistence.*;

@Entity
public class Recipe extends Model{

    public static ArrayList<Recipe> recipesCollection = new ArrayList<>();
    public static ArrayList<String> typesCollection = new ArrayList<>(Arrays.asList("vegetariano", "vegano", "india"));

    @Id
    private String name;
    private String type;
    private String time;
    private String difficulty;
    //private Ingredient[] ingredients;
    //private String[] steps;

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

    /*public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }*/

    /*public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }*/
}
