package spring.learning.springmvc.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator
        implements ConstraintValidator<CourseCode, String> {
    private String[] prefixes;
    @Override
    public void initialize(CourseCode constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        prefixes = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(
            String code,
            ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;
        for(String prefix: prefixes) {
            result = code == null || code.startsWith(prefix);
            if(result)
                break;
        }
        return result;
    }
}
