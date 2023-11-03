package com.softvan.controller;

import com.softvan.dto.request.LoginRequestDto;
import com.softvan.dto.request.UserRegisterRequest;
import com.softvan.dto.response.ApiResponse;
import com.softvan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.hibernate.loader.Loader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping( "/auth")

//@CrossOrigin("http://localhost:3000/")
public class UserController {

    private final UserService userService;



    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        String registerdUserName = this.userService.registerUser(userRegisterRequest);
        return new ResponseEntity<>(new ApiResponse(registerdUserName,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String jwtToken = this.userService.loginUser(loginRequestDto);
        return new ResponseEntity<>(new ApiResponse(jwtToken,HttpStatus.OK.value()),HttpStatus.OK);
    }




}
