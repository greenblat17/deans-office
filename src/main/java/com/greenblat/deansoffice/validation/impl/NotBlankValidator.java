package com.greenblat.deansoffice.validation.impl;

import com.greenblat.deansoffice.validation.NotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.isBlank();
    }

}
