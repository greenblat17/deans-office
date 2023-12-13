package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.dto.GradeBookRequest;
import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.service.GradeBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class GradeBookCommand {

    private final GradeBookService gradeBookService;

    @ShellMethod(key = "save-grade-book", value = "save new grade book")
    public String save(@ShellOption Long studentId,
                       @ShellOption String subjectName,
                       @ShellOption Short grade) {
        var gradeBookRequest = GradeBookRequest.builder()
                .studentId(studentId)
                .subjectName(subjectName)
                .grade(grade)
                .build();
        gradeBookService.saveGradeBook(gradeBookRequest);
        return "Grade book saved";
    }

    public String update(@ShellOption Long gradeBookId,
                         @ShellOption Long studentId,
                         @ShellOption(defaultValue = "") String subjectName,
                         @ShellOption(defaultValue = "") Short grade) {
        var gradeBookRequest = new GradeBookRequest(gradeBookId, studentId, subjectName, grade);
        gradeBookService.updateGradeBook(gradeBookRequest);
        return "Grade book updated";
    }
}
