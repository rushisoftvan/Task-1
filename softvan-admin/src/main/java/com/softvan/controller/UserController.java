package com.softvan.controller;

import com.softvan.dto.request.UserRegisterRequest;
import com.softvan.dto.response.ApiResponse;
import com.softvan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

     private final UserService userService;

    @PostMapping("/register")
    public ApiResponse registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        String registerdUserName = this.userService.registerUser(userRegisterRequest);
        return new ApiResponse(registerdUserName,HttpStatus.CREATED.value());
    }


}
