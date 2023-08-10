package spring.learning.springmvc.domain;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import spring.learning.springmvc.annotations.CourseCode;

import java.util.LinkedHashMap;

// lombok added for avoiding boilerplate code
@Getter @Setter
public class Customer {
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Last name is required with min length of 1 character")
    private String lastName;

    @NotNull(message = "Free passes is required")
    @Min(value = 0, message = "Should be greater than or equal to zero")
    @Max(value = 10, message = "Should be less than or equal to ten")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message="Only 5 chars / digits")
    private String postalCode;

    @CourseCode(
            value= {"SPRING", "JAVA"},
            message="Should start with SPRING or JAVA")
    private String courseCode;
}
