package com.greenblat.deansoffice.repository.mapper;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.model.Subject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GradeBookRowMapper implements RowMapper<GradeBook> {

    @Override
    public GradeBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GradeBook(
                rs.getLong("id"),
                buildStudent(rs),
                buildSubject(rs),
                rs.getShort("grade")
        );
    }

    private Student buildStudent(ResultSet rs) throws SQLException {
        return Student.builder()
                .lastName(rs.getString("last_name"))
                .firstName(rs.getString("first_name"))
                .surname(rs.getString("surname"))
                .universityYearAdmission(rs.getInt("university_year_admission"))
                .formEducation(FormEducation.valueOf(rs.getString("form_education")))
                .groupNumber(rs.getInt("group_number"))
                .build();
    }

    private Subject buildSubject(ResultSet rs) throws SQLException {
        return Subject.builder()
                .name(rs.getString("name"))
                .department(rs.getString("department"))
                .build();
    }

}
