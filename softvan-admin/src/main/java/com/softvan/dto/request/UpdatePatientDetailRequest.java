package com.softvan.dto.request;

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
    @NotEmpty(message = "First Name Must Not Be Empty")
    private String lastName;
    @NotEmpty(message = "First Name Must Not Be Empty")

    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    private LocalDate dateOfBirth;

    @NotNull(message = "status should not be null")
    private StatusEnum staus;
}