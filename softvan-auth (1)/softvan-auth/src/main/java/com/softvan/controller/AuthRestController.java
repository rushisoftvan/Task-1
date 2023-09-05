package com.softvan.controller;

import com.softvan.dto.request.LoginRequestDto;
import com.softvan.dto.response.ApiResponse;
import com.softvan.service.AuthService;
import com.softvan.service.DefaultAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String jwtToken = this.authService.authenticate(loginRequestDto);
            return new ApiResponse(jwtToken, HttpStatus.OK.value());
    }
    @GetMapping("/m1")
    public String m1(){
        return "rushikesh";
    }
}
