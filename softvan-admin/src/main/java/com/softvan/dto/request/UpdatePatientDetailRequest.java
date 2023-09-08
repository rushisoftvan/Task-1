package com.softvan.dto.request;

import com.softvan.customannotation.StatusEnumTypeConstraint;
import com.softvan.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UpdatePatientDetailRequest {
    @NotEmpty(message = "First Name Must Not Be Empty")
    private String firstName;

    @NotEmpty(message = "Last Name Must Not Be Empty")
    private String lastName;


    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotNull(message="Date Of Birth should not be null")
    private LocalDate dateOfBirth;

    //@NotNull(message = "Status should not be null")
    @StatusEnumTypeConstraint
    private StatusEnum status;

    @NotNull (message="hasAllergy should not be null")
        private Boolean hasAllergy;

    @NotNull(message="hasBloodPressure should not be null")
    private Boolean hasBloodPressure;

}