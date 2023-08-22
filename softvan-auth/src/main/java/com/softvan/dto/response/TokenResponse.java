package com.softvan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private String JwtToken;

    public TokenResponse(String jwtToken){
        this.JwtToken=jwtToken;
    }
}
