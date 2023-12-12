package com.greenblat.deansoffice.validation;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.validation.impl.CheckFormEducationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckFormEducationValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckFormEducation {

    String message() default "Possible form education: [DAYTIME], [EVENING], [CORRESPONDENCE]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
