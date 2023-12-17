package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.GradeBook;

import java.util.Optional;

public interface GradeBookRepository {

    void insertGrade(GradeBook gradeBook);

    void updateGrade(GradeBook gradeBook);

    Optional<GradeBook> findByStudentId(Long studentId);

}
