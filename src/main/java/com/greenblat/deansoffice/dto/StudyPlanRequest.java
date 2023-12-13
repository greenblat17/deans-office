package com.greenblat.deansoffice.dto;

import lombok.Builder;

@Builder
public record StudyPlanRequest(Long id,
                               String subjectName,
                               Integer semester,
                               Integer hours,
                               String reportingForm) {
}
