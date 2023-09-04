package com.softvan.service;

import com.softvan.Repository.UserRepsitory;
import com.softvan.dto.request.LoginRequestDto;
import com.softvan.entity.UserEntity;
import com.softvan.exception.UserLokedException;
import com.softvan.exception.UserNotFoundException;
import com.softvan.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final String PASSWORD_IS_NOT_CORRECT = "password is not correct";
    public static final String USER_IS_LOKED_YOU_CANT_TO_LOGIN = "User Is Loked You Cant to login ";

    private final PasswordEncoder passwordEncoder;

    private final UserRepsitory userRepsitory;

    private final JwtTokenProvider jwtTokenProvider;

    //@Transactional
    public String createJwtToken(LoginRequestDto loginRequestDto){
         String token =null;
       UserEntity user = this.userRepsitory.getUserByUsername(loginRequestDto.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found For This Username"));
        String role = user.getRole().getName();
       if(!user.isLoginLockStatus()){
           if(user.getFailedAttempt()==3){
               user.setLoginLockStatus(true);
               user.setLockTime(LocalDateTime.now());
               this.userRepsitory.save(user);
                 throw new UserLokedException(USER_IS_LOKED_YOU_CANT_TO_LOGIN);
           }
           if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
                 user.setFailedAttempt(user.getFailedAttempt()+1);
                 this.userRepsitory.save(user);
               throw new UserNotFoundException(PASSWORD_IS_NOT_CORRECT);
           }


            token = this.jwtTokenProvider.createToken(user.getEmail(), user.getId(), role);
           user.setLogincount(user.getLogincount()+1);
           this.userRepsitory.save(user);

       }
       return token;
      // user.setLoginLockStatus(true);

    }



}
