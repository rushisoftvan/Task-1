package com.softvan.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PatientCreateRequest {

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

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }


}
