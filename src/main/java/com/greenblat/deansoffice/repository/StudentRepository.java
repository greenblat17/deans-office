package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.FormEducation;

public interface StudentRepository {

    Integer countByFormEducation(FormEducation formEducation);

}
