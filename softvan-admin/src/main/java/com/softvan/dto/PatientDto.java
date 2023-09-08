package com.softvan.dto;

import com.softvan.enums.StatusEnum;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PatientDto {
    private String firstName;

    private String lastName;

    private String email;

    private Boolean hasAllergy;



    private Boolean hasBloodPressure;

    private LocalDate dateOfBirth;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private StatusEnum statusEnum;

    public PatientDto(String firstName,String lastName,String email,LocalDate dateOfBirth,LocalDateTime createdOn,LocalDateTime updatedOn,StatusEnum statusEnum,Boolean hasAllergy, Boolean hasBloodPressure) {
     this.firstName=firstName;
     this.lastName=lastName;
     this.email=email;
     this.hasAllergy=hasAllergy;
     this.dateOfBirth=dateOfBirth;
     this.createdOn=createdOn;
     this.updatedOn=updatedOn;
     this.statusEnum=statusEnum;
     this.hasBloodPressure=hasBloodPressure;
    }
}
