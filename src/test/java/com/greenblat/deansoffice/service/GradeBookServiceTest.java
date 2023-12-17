package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.GradeBookRequest;
import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.GradeBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeBookServiceTest {

    @Mock
    private GradeBookRepository gradeBookRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private GradeBookService gradeBookService;

    @Test
    void saveGradeBook() {
        GradeBookRequest request = new GradeBookRequest(1L, 1L, "Math", (short) 5);

        Student student = new Student(1L, "Zhur", "Alex", "Alex", 2023, FormEducation.EVENING, 1300);
        when(studentService.getStudent(request.studentId())).thenReturn(student);

        Subject subject = new Subject(1L,  "Math", "AM");
        when(subjectService.getSubject(request.subjectName())).thenReturn(subject);

        gradeBookService.saveGradeBook(request);

        verify(gradeBookRepository).insertGrade(any(GradeBook.class));
    }

    @Test
    void updateGradeBook() {
        GradeBookRequest request = new GradeBookRequest(1L, 1L, "Physics", (short) 5);

        Student student = new Student(1L, "Zhur", "Alex", "Alex", 2023, FormEducation.EVENING, 1300);
        when(studentService.getStudent(request.id())).thenReturn(student);

        Subject subject = new Subject(1L,  "Physics", "PH");
        when(subjectService.getSubject(request.subjectName())).thenReturn(subject);

        gradeBookService.updateGradeBook(request);

        verify(gradeBookRepository).updateGrade(any(GradeBook.class));
    }
}