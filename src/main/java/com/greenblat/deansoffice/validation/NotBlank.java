package com.greenblat.deansoffice.validation;

import com.greenblat.deansoffice.validation.impl.NotBlankValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotBlankValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {

    String message() default "Parameter should be not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
