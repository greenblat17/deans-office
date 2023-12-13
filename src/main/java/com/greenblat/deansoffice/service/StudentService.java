package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.FormEducation;
import com.greenblat.deansoffice.model.Student;
import com.greenblat.deansoffice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Integer countByFormEducation(FormEducation formEducation) {
        return studentRepository.countByFormEducation(formEducation);
    }

    public void saveStudent(Student student) {
        studentRepository.insertStudent(student);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Student with id [%s] not found", id)));
    }
}
