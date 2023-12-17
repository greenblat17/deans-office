package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.relation.RelationNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldReturnCountByFormEducation() {
        var formEducation = FormEducation.DAYTIME;
        int expectedCount = 5;
        when(studentRepository.countByFormEducation(formEducation)).thenReturn(expectedCount);

        int actualCount = studentService.countByFormEducation(formEducation);

        assertEquals(expectedCount, actualCount);
        verify(studentRepository).countByFormEducation(formEducation);
    }

    @Test
    void saveStudent() {
        var student = new Student();

        studentService.saveStudent(student);

        verify(studentRepository).insertStudent(student);
    }

    @Test
    void updateStudent() {
        Student existingStudent = Student.builder()
                .id(1L)
                .groupNumber(1300)
                .formEducation(FormEducation.EVENING)
                .universityYearAdmission(2023)
                .build();

        Student updatedStudent = Student.builder()
                .id(1L)
                .groupNumber(1301)
                .formEducation(FormEducation.EVENING)
                .universityYearAdmission(2023)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));

        studentService.updateStudent(updatedStudent);

        assertEquals(updatedStudent.getGroupNumber(), existingStudent.getGroupNumber());
        assertEquals(updatedStudent.getFormEducation(), existingStudent.getFormEducation());
        assertEquals(updatedStudent.getUniversityYearAdmission(), existingStudent.getUniversityYearAdmission());
        verify(studentRepository).updateStudent(existingStudent);
    }

    @Test
    void getStudentIfExists() {
        Long studentId = 1L;
        Student student = new Student();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student result = studentService.getStudent(studentId);

        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void throwExceptionIfStudentNotExists() {
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudent(studentId));
    }

}