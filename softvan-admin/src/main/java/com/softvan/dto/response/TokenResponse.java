package com.softvan.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter

public class TokenResponse {

    private String token;

    public TokenResponse (String token){
        this.token=token;
    }

}
