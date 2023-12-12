package com.greenblat.deansoffice.validation.impl;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.validation.CheckFormEducation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckFormEducationValidator implements ConstraintValidator<CheckFormEducation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals(FormEducation.EVENING.name())
               || s.equals(FormEducation.CORRESPONDENCE.name())
               || s.equals(FormEducation.DAYTIME.name());
    }

}
