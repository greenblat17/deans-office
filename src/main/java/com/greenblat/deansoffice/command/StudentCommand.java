package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.service.StudentService;
import com.greenblat.deansoffice.validation.CheckFormEducation;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class StudentCommand {

    private final StudentService studentService;

    @ShellMethod(key = "count-student", value = "show count of student by form education")
    public Integer showCountStudent(@ShellOption @CheckFormEducation String education) {
        return studentService.countByFormEducation(FormEducation.valueOf(education));
    }

}
