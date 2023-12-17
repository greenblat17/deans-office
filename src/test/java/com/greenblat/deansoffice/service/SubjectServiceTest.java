package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.Subject;
import com.greenblat.deansoffice.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void getSubject() {
        String subjectName = "Math";
        var expectedSubject = Subject.builder()
                .name(subjectName)
                .build();
        when(subjectRepository.findByName(subjectName)).thenReturn(Optional.of(expectedSubject));

        Subject actualSubject = subjectService.getSubject(subjectName);

        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void throwExceptionWhenSubjectNotExists() {
        String subjectName = "Physics";
        when(subjectRepository.findByName(subjectName)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subjectService.getSubject(subjectName));
    }

}