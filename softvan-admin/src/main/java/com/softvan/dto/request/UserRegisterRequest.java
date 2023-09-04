package com.softvan.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRegisterRequest {

   @NotEmpty(message="first name should not be empty")
    private String firstname;

   @NotEmpty(message = "lastName should not be empty")
    private String lastName;

    @NotEmpty(message = "email must not be empty")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email is invalid")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

        @NotNull(message = "Role Id should not be null")
        private Integer roleId;
}

