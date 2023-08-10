package spring.learning.springmvc.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator
        implements ConstraintValidator<CourseCode, String> {
    private String prefix;
    @Override
    public void initialize(CourseCode constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        prefix = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(
            String code,
            ConstraintValidatorContext constraintValidatorContext) {
        return code == null || code.startsWith(prefix);
    }
}
