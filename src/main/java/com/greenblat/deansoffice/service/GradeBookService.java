package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.GradeBookRequest;
import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.repository.GradeBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.*;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor
public class GradeBookService {

    private final GradeBookRepository gradeBookRepository;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public void saveGradeBook(GradeBookRequest request) {
        var student = studentService.getStudent(request.studentId());
        var subject = subjectService.getSubject(request.subjectName());

        GradeBook gradeBook = GradeBook.builder()
                .student(student)
                .subject(subject)
                .grade(request.grade())
                .build();

        gradeBookRepository.insertGrade(gradeBook);
    }

    public void updateGradeBook(GradeBookRequest request) {
        var gradeBook = GradeBook.builder()
                .id(request.id())
                .student(studentService.getStudent(request.id()))
                .subject(subjectService.getSubject(request.subjectName()))
                .grade(request.grade())
                .build();
        gradeBookRepository.updateGrade(gradeBook);
    }

    public GradeBook getGradeBookForStudent(Long studentId) {
        return gradeBookRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Grade Book not found"));
    }

}
