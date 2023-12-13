package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.StudyPlanResponse;
import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.repository.StudyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyPlanService {

    private final StudyPlanRepository studyPlanRepository;
    private final SubjectService subjectService;

    public List<StudyPlanResponse> getStudyPlanBySubject(String subjectName) {
        var subject = subjectService.getSubject(subjectName);
        return studyPlanRepository.selectBySubject(subject)
                .stream()
                .map(this::buildResponse)
                .toList();
    }

    private StudyPlanResponse buildResponse(StudyPlan studyPlan) {
        return new StudyPlanResponse(
                studyPlan.getHours(),
                studyPlan.getReportingForm()
        );
    }
}
