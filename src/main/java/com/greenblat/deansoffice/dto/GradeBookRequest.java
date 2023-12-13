package com.greenblat.deansoffice.dto;

import lombok.Builder;

@Builder
public record GradeBookRequest(Long id,
                               Long studentId,
                               String subjectName,
                               Short grade) {
}
