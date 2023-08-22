package com.softvan.controller;

import com.softvan.dto.UserLoginRequest;
import com.softvan.dto.response.ApiResponse;
import com.softvan.dto.response.TokenResponse;
import com.softvan.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

        @GetMapping("/t1")
    public void m1(){
       log.info("sachin");
    }


    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        log.info("email {}",userLoginRequest.getEmail());
        log.info("password",userLoginRequest.getEmail());
       log.info("login");

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        this.authenticationManager.authenticate(authentication);

        String accessToken = this.jwtUtil.generateToken(userLoginRequest.getEmail());
        log.info("accessToken {}",accessToken);

        TokenResponse tokenResponse = new TokenResponse(accessToken);
        return new ApiResponse(tokenResponse, HttpStatus.OK.value());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/demo")
    public void demo(){
      log.info("demo");
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/check")
    public void check(){
       log.info("check");
    }
}
