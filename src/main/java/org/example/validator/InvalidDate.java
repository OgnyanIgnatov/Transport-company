package org.example.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = InvalidDateValidator.class)
public @interface InvalidDate {
    String message() default "Invalid dates assigned";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
