package com.greenblat.deansoffice.validation;

import com.greenblat.deansoffice.model.FormEducation;
import org.springframework.stereotype.Component;

@Component
public class StudentValidation {

    public void checkFormEducation(String formEducation) {
        if (!formEducation.equals(FormEducation.EVENING.name())
            && !formEducation.equals(FormEducation.CORRESPONDENCE.name())
            && !formEducation.equals(FormEducation.DAYTIME.name())) {
            var errorMessage = String.format("This form education [%s] is not valid.\n Possible form education: [%s], [%s], [%s]",
                    formEducation, FormEducation.CORRESPONDENCE, FormEducation.EVENING, FormEducation.DAYTIME);
        }

    }
}
