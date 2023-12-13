package com.greenblat.deansoffice.repository.mapper;

import com.greenblat.deansoffice.model.Subject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubjectRowMapper implements RowMapper<Subject> {

    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Subject(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("department")
        );
    }

}
