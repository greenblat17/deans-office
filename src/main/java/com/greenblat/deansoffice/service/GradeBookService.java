package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.GradeBookRequest;
import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.GradeBook;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.GradeBookRepository;
import com.greenblat.deansoffice.repository.StudentRepository;
import com.greenblat.deansoffice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.*;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor
public class GradeBookService {

    private final GradeBookRepository gradeBookRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public void saveGradeBook(GradeBookRequest request) {
        var student = getStudent(request.studentId());
        var subject = getSubject(request.subjectName());

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
                .student(getStudent(request.studentId()))
                .subject(getSubject(request.subjectName()))
                .grade(request.grade())
                .build();
        gradeBookRepository.updateGrade(gradeBook);
    }

    private Subject getSubject(String subjectName) {
        return subjectRepository.findByName(subjectName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Subject with name [%s] not found", subjectName)
                ));
    }

    private Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Student with id [%s] not found", studentId)));
    }

}
