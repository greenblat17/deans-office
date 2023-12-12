package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.model.Subject;

import java.util.List;

public interface StudyPlanRepository {

    List<StudyPlan> selectBySubject(Subject subject);

}
