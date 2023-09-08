package com.softvan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class PatientAddRequest {

    @NotEmpty(message = "First Name Must Not Be Empty")
    private String firstName;

    @NotEmpty(message = "First Name Must Not Be Empty")
    private String lastName;

    @NotEmpty(message = "First Name Must Not Be Empty")
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotNull(message="hasAllergy should not be null")
    private Boolean hasAllergy;

    @NotNull(message="hasBloodPressure should not be null")
    private Boolean hasBloodPressure;

    @NotNull(message= "Date Of Birth Should not be null")
    private LocalDate dateOfBirth;

}
