package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.StudyPlanRequest;
import com.greenblat.deansoffice.dto.StudyPlanResponse;
import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.ReportingForm;
import com.greenblat.deansoffice.model.StudyPlan;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.StudyPlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyPlanServiceTest {

    @Mock
    private StudyPlanRepository studyPlanRepository;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private StudyPlanService studyPlanService;

    @Test
    void saveStudyPlan() {
        var request = StudyPlanRequest.builder()
                .subjectName("Subject")
                .hours(4)
                .semester(1)
                .reportingForm(ReportingForm.EXAM.name())
                .build();
        var subject = buildSubject();
        when(subjectService.getSubject(request.subjectName())).thenReturn(subject);

        studyPlanService.saveStudyPlan(request);

        var expectedStudyPlan = buildStudyPlan(subject);

        verify(studyPlanRepository).addStudyPlan(expectedStudyPlan);
    }

    @Test
    void updateStudyPlan() {
        var request = StudyPlanRequest.builder()
                .subjectName("Subject")
                .hours(4)
                .semester(1)
                .reportingForm(ReportingForm.EXAM.name())
                .build();
        var subject = buildSubject();
        when(subjectService.getSubject(request.subjectName())).thenReturn(subject);

        studyPlanService.updateStudyPlan(request);

        var expectedStudyPlan = buildStudyPlan(subject);

        verify(studyPlanRepository).updateStudyPlan(expectedStudyPlan);
    }

    @Test
    void getStudyPlanIfExists() {
        Long id = 1L;
        var expectedStudyPlan = new StudyPlan();
        when(studyPlanRepository.findById(id)).thenReturn(Optional.of(expectedStudyPlan));

        var actualStudyPlan = studyPlanService.getStudyPlan(id);

        assertEquals(expectedStudyPlan, actualStudyPlan);
    }

    @Test
    void shouldThrowExceptionIfStudyPlanNotExists() {
        Long id = 1L;
        when(studyPlanRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studyPlanService.getStudyPlan(id));
    }

    @Test
    void getStudyPlanBySubjectName() {
        String subjectName = "Subject";
        Subject subject = new Subject(1L, subjectName, "Description");
        var studyPlans = Collections.singletonList(buildStudyPlan(subject));

        when(subjectService.getSubject(subjectName)).thenReturn(subject);
        when(studyPlanRepository.selectBySubject(subject)).thenReturn(studyPlans);

        var studyPlanResponses = studyPlanService.getStudyPlanBySubject(subjectName);

        assertEquals(1, studyPlanResponses.size());
        assertEquals(4, studyPlanResponses.get(0).hours());
        assertEquals(ReportingForm.EXAM, studyPlanResponses.get(0).reportingForm());
    }

    private StudyPlan buildStudyPlan(Subject subject) {
        return StudyPlan.builder()
                .subject(subject)
                .semester(1)
                .hours(4)
                .reportingForm(ReportingForm.EXAM)
                .build();
    }

    private static Subject buildSubject() {
        return new Subject(1L, "Subject", "Department");
    }
}