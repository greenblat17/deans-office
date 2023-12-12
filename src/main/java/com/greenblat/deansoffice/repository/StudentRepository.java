package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.Student;

public interface StudentRepository {

    Integer countByFormEducation(FormEducation formEducation);

    void insertStudent(Student student);

    void updateStudent(Student student);

}
