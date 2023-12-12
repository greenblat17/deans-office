package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.model.FormEducation;
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


}
