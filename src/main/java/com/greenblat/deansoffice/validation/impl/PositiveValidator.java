package com.greenblat.deansoffice.validation.impl;

import com.greenblat.deansoffice.validation.Positive;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveValidator implements ConstraintValidator<Positive, Long> {

    @Override
    public boolean isValid(Long num, ConstraintValidatorContext constraintValidatorContext) {
        return num > 0;
    }

}
