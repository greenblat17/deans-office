package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject getSubject(String subjectName) {
        return subjectRepository.findByName(subjectName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Subject with name [%s] not found", subjectName)
                ));
    }
}
