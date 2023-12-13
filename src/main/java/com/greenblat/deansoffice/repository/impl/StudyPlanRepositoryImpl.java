package com.greenblat.deansoffice.repository.impl;

import com.greenblat.deansoffice.repository.mapper.StudyPlanRowMapper;
import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.StudyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void addStudyPlan(StudyPlan studyPlan) {
        var sql = """
                INSERT INTO study_plan (subject_id, semester, hours, reporting_form)
                VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                studyPlan.getSubject().getId(),
                studyPlan.getSemester(),
                studyPlan.getHours(),
                studyPlan.getReportingForm().name()
        );
    }

    @Override
    public void updateStudyPlan(StudyPlan studyPlan) {
        var sql = """
                UPDATE study_plan 
                SET (subject_id, semester, hours, reporting_form) 
                        = (?, ?, ?, ?)
                WHERE id = ?
                """;
        jdbcTemplate.update(
                sql,
                studyPlan.getSubject().getId(),
                studyPlan.getSemester(),
                studyPlan.getHours(),
                studyPlan.getReportingForm().name(),
                studyPlan.getId()
        );
    }

    @Override
    public Optional<StudyPlan> findById(Long id) {
        var sql = """
                SELECT sp.id, sp.subject_id, sp.semester, sp.hours, sp.reporting_form, s.name, s.department
                FROM study_plan sp
                JOIN subject s ON s.id = sp.subject_id
                WHERE s.id = ?
                """;
        return jdbcTemplate.query(sql, studyPlanRowMapper, id)
                .stream()
                .findFirst();
    }
}
