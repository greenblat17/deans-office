package com.greenblat.deansoffice.validation;

import com.greenblat.deansoffice.validation.impl.PositiveValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PositiveValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Positive {

    String message() default "Parameter should be positive";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
