package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.dto.StudyPlanRequest;
import com.greenblat.deansoffice.dto.StudyPlanResponse;
import com.greenblat.deansoffice.service.StudyPlanService;
import com.greenblat.deansoffice.validation.NotBlank;
import com.greenblat.deansoffice.validation.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class StudyPlanCommand extends SecureCommand {

    private final StudyPlanService studyPlanService;

    @ShellMethod(key = "subject-study-plan", value = "get stuwy plan with current subject")
    @ShellMethodAvailability("isUserSignedIn")
    public List<StudyPlanResponse> showStudyPlanWithSubject(@ShellOption @NotBlank String subjectName) {
        return studyPlanService.getStudyPlanBySubject(subjectName);
    }

    @ShellMethod(key = "save-plan", value = "save new study plan to database")
    @ShellMethodAvailability("isUserAdmin")
    public String save(@ShellOption @NotBlank String subjectName,
                       @ShellOption Integer semester,
                       @ShellOption @Positive Integer hours,
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
    @ShellMethodAvailability("isUserAdmin")
    public String update(@ShellOption @Positive Long id,
                         @ShellOption @NotBlank String subjectName,
                         @ShellOption Integer semester,
                         @ShellOption @Positive Integer hours,
                         @ShellOption @NotBlank String reportingForm) {
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
