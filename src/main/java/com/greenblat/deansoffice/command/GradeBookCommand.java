package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.dto.GradeBookRequest;
import com.greenblat.deansoffice.service.GradeBookService;
import com.greenblat.deansoffice.validation.NotBlank;
import com.greenblat.deansoffice.validation.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class GradeBookCommand {

    private final GradeBookService gradeBookService;

    @ShellMethod(key = "save-grade-book", value = "save new grade book")
    @ShellMethodAvailability("isUserAdmin")
    public String save(@ShellOption @Positive Long studentId,
                       @ShellOption @NotBlank String subjectName,
                       @ShellOption Short grade) {
        var gradeBookRequest = GradeBookRequest.builder()
                .studentId(studentId)
                .subjectName(subjectName)
                .grade(grade)
                .build();
        gradeBookService.saveGradeBook(gradeBookRequest);
        return "Grade book saved";
    }

    @ShellMethod(key = "update-grade-book", value = "update existing grade book")
    @ShellMethodAvailability("isUserAdmin")
    public String update(@ShellOption @Positive Long gradeBookId,
                         @ShellOption @Positive Long studentId,
                         @ShellOption(defaultValue = "") String subjectName,
                         @ShellOption(defaultValue = "") Short grade) {
        var gradeBookRequest = new GradeBookRequest(gradeBookId, studentId, subjectName, grade);
        gradeBookService.updateGradeBook(gradeBookRequest);
        return "Grade book updated";
    }
}
