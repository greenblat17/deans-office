package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.repository.GradeBookRepository;
import com.greenblat.deansoffice.repository.mapper.GradeBookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GradeBookRepositoryImpl implements GradeBookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final GradeBookRowMapper gradeBookRowMapper;

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

    @Override
    public Optional<GradeBook> findByStudentId(Long studentId) {
        var sql = """
                SELECT
                    gb.id, gb.grade,
                    st.last_name, st.first_name, st.surname, st.form_education, st.group_number, st.university_year_admission,
                    s.name, s.department
                FROM grade_book gb
                INNER JOIN subject s ON s.id = gb.subject_id
                INNER JOIN student st ON st.id = gb.student_id 
                WHERE gb.student_id = ?
                """;
        return jdbcTemplate.query(sql, gradeBookRowMapper, studentId)
                .stream()
                .findFirst();
    }

}
