package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints.Required;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Allergen extends Model {

    public static final Finder<Long, Allergen> find = new Finder<>(Allergen.class);

    @Id
    @Required(message="El nombre del alérgeno es obligatorio")
    private String name;

    @Required(message="La enfermedad del alérgeno es obligatoria")
    private String diseases;

    @ManyToMany(mappedBy = "allergens")
    public List<Recipe> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public static Allergen selectAllergen(String name) {
        return find.query().where().eq("name", name).findOne();
    }
}
