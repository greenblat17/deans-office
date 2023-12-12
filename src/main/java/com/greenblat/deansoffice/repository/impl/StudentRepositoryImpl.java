package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer countByFormEducation(FormEducation formEducation) {
        var sql = """
                SELECT COUNT(*)
                FROM student
                WHERE form_education = ?
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class, formEducation.name());
    }

    @Override
    public void insertStudent(Student student) {
        var sql = """
                INSERT INTO student (last_name, first_name, surname, university_year_admission, form_education, group_number)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                student.getLastName(),
                student.getFirstName(),
                student.getSurname(),
                student.getUniversityYearAdmission(),
                student.getFormEducation(),
                student.getGroupNumber()
        );
    }

    @Override
    public void updateStudent(Student student) {
        var sql = """
                UPDATE student
                SET 
                    (last_name, first_name, surname, university_year_admission, form_education, group_number)
                        = (?, ?, ?, ?, ?, ?)
                WHERE id = ?
                """;
        jdbcTemplate.update(
                sql,
                student.getLastName(),
                student.getFirstName(),
                student.getSurname(),
                student.getUniversityYearAdmission(),
                student.getFormEducation(),
                student.getGroupNumber(),
                student.getId()
        );
    }


}
