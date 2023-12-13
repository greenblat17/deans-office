package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.repository.GradeBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GradeBookRepositoryImpl implements GradeBookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insertGrade(GradeBook gradeBook) {
        var sql = """
                INSERT INTO grade_book (student_id, subject_id, grade)
                VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                gradeBook.getStudent().getId(),
                gradeBook.getSubject().getId(),
                gradeBook.getGrade()
        );
    }

    @Override
    public void updateGrade(GradeBook gradeBook) {
        var sql = """
                UPDATE grade_book
                SET (student_id, subject_id, grade) = (?, ?, ?)
                WHERE id = ?
                """;
        jdbcTemplate.update(
                sql,
                gradeBook.getStudent().getId(),
                gradeBook.getSubject().getId(),
                gradeBook.getGrade(),
                gradeBook.getId()
        );
    }

}
