package models;

public class Ingredient {

    private String name;
    private String[] allergens;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAllergens() {
        return allergens;
    }

    public void setAllergens(String[] allergens) {
        this.allergens = allergens;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
