package com.greenblat.deansoffice.dto;

import com.greenblat.deansoffice.model.ReportingForm;

public record StudyPlanResponse(Integer hours,
                                ReportingForm reportingForm) {
}
