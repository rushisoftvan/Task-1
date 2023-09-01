package com.softvan.controller;

import com.softvan.dto.request.UserLoginRequest;

import com.softvan.dto.request.UserRegisterRequest;
import com.softvan.dto.response.ApiResponse;
import com.softvan.dto.response.TokenResponse;
import com.softvan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

     private final UserService userService;

    @PostMapping("/login")
    public ApiResponse loginUser(@RequestBody UserLoginRequest userLoginRequest){
        String token = this.userService.loginUser(userLoginRequest);
        TokenResponse tokenResponse = new TokenResponse(token);
        return new ApiResponse(tokenResponse, HttpStatus.OK.value());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/name")
    public String name(){
      return "rushikesh";
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRegisterRequest userRegisterRequest){
        Integer savedUserId = this.userService.registerUser(userRegisterRequest);
        log.info("Id {}",savedUserId);
    }
}
