package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.service.StudentService;
import com.greenblat.deansoffice.validation.CheckFormEducation;
import com.greenblat.deansoffice.validation.NotBlank;
import com.greenblat.deansoffice.validation.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class StudentCommand {

    private final StudentService studentService;

    @ShellMethod(key = "count-student", value = "show count of student by form education")
    @ShellMethodAvailability("isUserSignedIn")
    public Integer showCountStudent(@ShellOption @CheckFormEducation String education) {
        return studentService.countByFormEducation(FormEducation.valueOf(education));
    }

    @ShellMethod(key = "save-student", value = "save new student to database")
    @ShellMethodAvailability("isUserAdmin")
    public String save(@ShellOption @NotBlank String lastName,
                       @ShellOption @NotBlank String firstName,
                       @ShellOption @NotBlank String surname,
                       @ShellOption @Positive Integer yearAdmission,
                       @ShellOption @CheckFormEducation String education,
                       @ShellOption @Positive Integer groupNumber) {
        var student = Student.builder()
                .lastName(lastName)
                .firstName(firstName)
                .surname(surname)
                .universityYearAdmission(yearAdmission)
                .formEducation(FormEducation.valueOf(education))
                .groupNumber(groupNumber)
                .build();
        studentService.saveStudent(student);
        return "Student was saved";
    }

    @ShellMethod(key = "update-student", value = "update existing student")
    @ShellMethodAvailability("isUserAdmin")
    public String update(@ShellOption @Positive Long id,
                         @ShellOption(defaultValue = "") String lastName,
                         @ShellOption(defaultValue = "") String firstName,
                         @ShellOption(defaultValue = "") String surname,
                         @ShellOption(defaultValue = "") Integer yearAdmission,
                         @ShellOption @CheckFormEducation String education,
                         @ShellOption(defaultValue = "") Integer groupNumber) {
        var student = Student.builder()
                .id(id)
                .lastName(lastName)
                .firstName(firstName)
                .surname(surname)
                .universityYearAdmission(yearAdmission)
                .formEducation(FormEducation.valueOf(education))
                .groupNumber(groupNumber)
                .build();
        studentService.updateStudent(student);
        return "Student was saved";
    }

}
