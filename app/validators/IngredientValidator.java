package validators;

import models.Ingredient;
import play.data.validation.Constraints;
import play.libs.F;
import java.util.List;

public class IngredientValidator extends Constraints.Validator<List<Ingredient>> {
    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>(
                "El ingrediente no es válido, tiene que estar formado por un nombre y un tipo", new Object[]{""}
        );
    }

    @Override
    public boolean isValid(List<Ingredient> ingredients) {
        if(null != ingredients) {
            for(Ingredient i : ingredients) {
                if(null == i.getName() || "".equals(i.getName())
                        || null == i.getType() || "".equals(i.getType())) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
