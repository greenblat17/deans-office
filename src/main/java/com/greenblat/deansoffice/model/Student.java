package com.greenblat.deansoffice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;

    private String firstName;

    private String surname;

    private Integer universityYearAdmission;

    @Enumerated(value = EnumType.STRING)
    private FormEducation formEducation;

    private Integer groupNumber;

}
