package com.softvan.dto;

import com.softvan.enums.StatusEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Getter
public final class PatientDto {

    private final Integer patientId;


    private final   String firstName;

    private final   String lastName;

    private  final  String email;

    private final  Boolean hasAllergy;


    private  final  Boolean hasBloodPressure;

    private final  LocalDate dateOfBirth;

    private final  LocalDateTime createdOn;

    private final  LocalDateTime updatedOn;

    private  final StatusEnum statusEnum;

//    public PatientDto(){
//
//    };

    public PatientDto(Integer patientId,String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDateTime createdOn, LocalDateTime updatedOn, StatusEnum statusEnum, Boolean hasAllergy, Boolean hasBloodPressure) {
        this.patientId=patientId;

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hasAllergy = hasAllergy;
        this.dateOfBirth = dateOfBirth;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statusEnum = statusEnum;
        this.hasBloodPressure = hasBloodPressure;
    }
}
