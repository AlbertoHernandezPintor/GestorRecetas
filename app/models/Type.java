package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Type extends Model {
    public static final Finder<Long, Type> find = new Finder<>(Type.class);

    @Constraints.Required(message="error.type-name-required")
    @Id
    private String name;

    @Constraints.Required(message="error.type-description-required")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<Type> selectTypesList() {
        return find.query().findList();
    }

    public static Type selectType(String name) {
        return find.query().where().eq("name", name).findOne();
    }
}
