package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.dto.StudyPlanResponse;
import com.greenblat.deansoffice.service.StudyPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class StudyPlanCommand {

    private final StudyPlanService studyPlanService;

    public List<StudyPlanResponse> showStudyPlanWithSubject(@ShellOption String subjectName) {
        return studyPlanService.getStudyPlanBySubject(subjectName);
    }
}
