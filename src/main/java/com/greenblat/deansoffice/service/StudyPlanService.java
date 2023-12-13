package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.command.StudyPlanCommand;
import com.greenblat.deansoffice.dto.StudyPlanRequest;
import com.greenblat.deansoffice.dto.StudyPlanResponse;
import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.ReportingForm;
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

    public void saveStudyPlan(StudyPlanRequest request) {
        var studyPlan = StudyPlan.builder()
                .subject(subjectService.getSubject(request.subjectName()))
                .semester(request.semester())
                .hours(request.hours())
                .reportingForm(ReportingForm.valueOf(request.reportingForm()))
                .build();
        studyPlanRepository.addStudyPlan(studyPlan);
    }

    public void updateStudyPlan(StudyPlanRequest request) {
        var studyPlan = new StudyPlan(
                request.id(),
                subjectService.getSubject(request.subjectName()),
                request.semester(),
                request.hours(),
                ReportingForm.valueOf(request.reportingForm())
        );
        studyPlanRepository.updateStudyPlan(studyPlan);
    }

    public StudyPlan getStudyPlan(Long id) {
        return studyPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Study Plan with id [%s] not found", id)
                ));
    }

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
