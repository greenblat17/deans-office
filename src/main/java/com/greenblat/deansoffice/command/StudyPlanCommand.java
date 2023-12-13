package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.dto.StudyPlanRequest;
import com.greenblat.deansoffice.dto.StudyPlanResponse;
import com.greenblat.deansoffice.service.StudyPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class StudyPlanCommand {

    private final StudyPlanService studyPlanService;

    public List<StudyPlanResponse> showStudyPlanWithSubject(@ShellOption String subjectName) {
        return studyPlanService.getStudyPlanBySubject(subjectName);
    }

    @ShellMethod(key = "save-plan", value = "save new study plan to database")
    public String save(@ShellOption String subjectName,
                       @ShellOption Integer semester,
                       @ShellOption Integer hours,
                       @ShellOption String reportingForm) {
        var studyPlanRequest = StudyPlanRequest.builder()
                .subjectName(subjectName)
                .semester(semester)
                .hours(hours)
                .reportingForm(reportingForm)
                .build();
        studyPlanService.saveStudyPlan(studyPlanRequest);
        return "Study Plan was saved";
    }

    @ShellMethod(key = "update-plan", value = "update existing study plan")
    public String update(@ShellOption Long id,
                         @ShellOption String subjectName,
                         @ShellOption Integer semester,
                         @ShellOption Integer hours,
                         @ShellOption String reportingForm) {
        var studyPlanRequest = StudyPlanRequest.builder()
                .id(id)
                .subjectName(subjectName)
                .semester(semester)
                .hours(hours)
                .reportingForm(reportingForm)
                .build();
        studyPlanService.updateStudyPlan(studyPlanRequest);
        return "Study Plan was updated";
    }

}
