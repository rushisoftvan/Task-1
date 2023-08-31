package com.softvan.controller;

import com.softvan.dto.request.UserLoginRequest;

import com.softvan.dto.response.ApiResponse;
import com.softvan.dto.response.TokenResponse;
import com.softvan.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

     private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ApiResponse loginUser(@RequestBody UserLoginRequest userLoginRequest){
        String token = this.userLoginService.loginUser(userLoginRequest);
        TokenResponse tokenResponse = new TokenResponse(token);
        return new ApiResponse(tokenResponse, HttpStatus.OK.value());
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/name")
    public String name(){
      return "rushikesh";
    }
}
