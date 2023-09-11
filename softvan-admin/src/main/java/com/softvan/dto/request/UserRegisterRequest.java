package com.softvan.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRegisterRequest {

   @NotEmpty(message="first name should not be empty")
    private String firstName;

   @NotEmpty(message = "lastName should not be empty")
    private String lastName;

    @NotEmpty(message = "email must not be empty")
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotNull(message = "Role Id should not be null")
    private Integer roleId;
}


