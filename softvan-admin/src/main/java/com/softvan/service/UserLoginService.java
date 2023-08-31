package com.softvan.service;

import com.softvan.Repository.UserRepsitory;
import com.softvan.dto.request.UserLoginRequest;


import com.softvan.entity.UserEntity;
import com.softvan.exception.CustomException;
import com.softvan.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService
{

    
    private final UserRepsitory userRepsitory;
       
    private final PasswordEncoder passwordEncoder;

     private final JwtTokenProvider jwtTokenProvider;


  public String loginUser(UserLoginRequest userLoginRequest)  {

      UserEntity user = this.userRepsitory.getUserByUsername(userLoginRequest.getEmail()).orElseThrow(() -> new CustomException("User Not Found For This Username", HttpStatus.BAD_REQUEST));
      String role = user.getRole().getName();
      if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
          throw new CustomException("password is not correct",HttpStatus.BAD_REQUEST);
      }


          return  this.jwtTokenProvider.createToken(user.getEmail(),user.getId(),role);

 }
}
