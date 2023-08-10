package spring.learning.springmvc.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// constraints is validated by CourseCodeConstraintValidator
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// annotation can be used on methods and fields
@Target({ ElementType.METHOD, ElementType.FIELD })
// how long will the constraint can be used
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    // define default value expected: can be different on class using annotation
    String[] value() default "SPRING!";
    // define default error message
    String message() default "Must start with SPRING!";
    // define default group
    Class<?>[] groups() default {};
    // define default payload
    Class<? extends Payload[]>[] payload() default {};
}
