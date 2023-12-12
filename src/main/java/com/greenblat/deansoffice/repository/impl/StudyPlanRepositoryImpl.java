package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.mapper.StudyPlanRowMapper;
import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.StudyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudyPlanRepositoryImpl implements StudyPlanRepository {

    private final JdbcTemplate jdbcTemplate;
    private final StudyPlanRowMapper studyPlanRowMapper;

    @Override
    public List<StudyPlan> selectBySubject(Subject subject) {
        var sql = """
                SELECT sp.id, sp.subject_id, sp.semester, sp.hours, sp.reporting_form, s.name, s.department
                FROM study_plan sp
                JOIN subject s ON s.id = sp.subject_id
                WHERE s.name = ?
                """;
        return jdbcTemplate.query(sql, studyPlanRowMapper, subject.getName());
    }
}
