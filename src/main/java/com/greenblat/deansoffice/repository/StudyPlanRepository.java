package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.model.Subject;

import java.util.List;
import java.util.Optional;

public interface StudyPlanRepository {

    List<StudyPlan> selectBySubject(Subject subject);

    void addStudyPlan(StudyPlan studyPlan);

    void updateStudyPlan(StudyPlan studyPlan);

    Optional<StudyPlan> findById(Long id);

}
