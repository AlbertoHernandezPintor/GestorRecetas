package validators;

import models.Allergen;
import models.Ingredient;
import play.data.validation.Constraints;
import play.libs.F;

import java.util.List;

public class AllergenValidator extends Constraints.Validator<List<Allergen>> {
    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>(
                "El alérgeno no es válido, tiene que estar formado por un nombre y una enfermedad", new Object[]{""}
        );
    }

    @Override
    public boolean isValid(List<Allergen> allergens) {
        if(null != allergens) {
            for (Allergen a : allergens) {
                if (null == a.getName() || "".equals(a.getName())
                        || null == a.getDiseases() || "".equals(a.getDiseases())) {
                    System.out.println("Lo tira el if");
                    return false;
                }
            }
        }
        //Los alérgenos son opcionales, si es null es porque no hay
        return true;
    }
}
