package com.greenblat.deansoffice.mapper;

import com.greenblat.deansoffice.model.ReportingForm;
import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.model.Subject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudyPlanRowMapper implements RowMapper<StudyPlan> {

    @Override
    public StudyPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
        return StudyPlan.builder()
                .id(rs.getLong("id"))
                .subject(buildSubject(rs))
                .semester(rs.getInt("semester"))
                .hours(rs.getInt("hours"))
                .reportingForm(ReportingForm.valueOf(rs.getString("reporting_form")))
                .build();
    }

    private Subject buildSubject(ResultSet rs) throws SQLException {
        return Subject.builder()
                .id(rs.getLong("subject_id"))
                .name(rs.getString("name"))
                .department(rs.getString("department"))
                .build();
    }

}
