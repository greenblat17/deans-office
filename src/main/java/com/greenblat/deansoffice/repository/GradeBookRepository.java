package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.GradeBook;

public interface GradeBookRepository {

    void insertGrade(GradeBook gradeBook);

    void updateGrade(GradeBook gradeBook);

}
