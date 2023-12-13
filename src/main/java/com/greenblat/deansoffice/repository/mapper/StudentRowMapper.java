package com.greenblat.deansoffice.repository.mapper;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(
                rs.getLong("id"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("surname"),
                rs.getInt("university_year_admission"),
                FormEducation.valueOf(rs.getString("form_education")),
                rs.getInt("group_number")
        );
    }

}
