package com.softvan.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String firstname;
    private String lastName;

    private String email;

    private String password;

    private Integer roleId;
}
