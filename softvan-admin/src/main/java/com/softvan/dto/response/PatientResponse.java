package com.softvan.dto.response;

import com.softvan.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PatientResponse {

    private String firstName;

    private String lastName;

    private String email;

    private Boolean hasAllergy;

    private Boolean hasBloodPressure;

    private LocalDate dateOfBirth;

    private LocalDateTime CreatedOn;

    private LocalDateTime UpdatedOn;

    private StatusEnum statusEnum;

}
