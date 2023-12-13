package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.SubjectRepository;
import com.greenblat.deansoffice.repository.mapper.SubjectRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubjectRepositoryImpl implements SubjectRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SubjectRowMapper subjectRowMapper;

    @Override
    public Optional<Subject> findByName(String name) {
        var sql = """
                SELECT id, name, department
                FROM subject
                WHERE name = ?
                """;
        return jdbcTemplate.query(sql, subjectRowMapper, name)
                .stream()
                .findFirst();
    }

}
