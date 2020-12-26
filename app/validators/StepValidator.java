package validators;

import models.Allergen;
import models.Step;
import play.data.validation.Constraints;
import play.libs.F;

import java.util.List;

public class StepValidator extends Constraints.Validator<List<Step>> {
    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>(
                "El paso no es válido, tiene que estar formado por un nombre y una descripción", new Object[]{""}
        );
    }

    @Override
    public boolean isValid(List<Step> steps) {
        if(null != steps) {
            for (Step s : steps) {
                if (null == s.getName() || "".equals(s.getName())
                        || null == s.getDescription() || "".equals(s.getDescription())) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
