package com.softvan.service;

import com.softvan.Repository.UserRepsitory;
import com.softvan.dto.request.LoginRequestDto;
import com.softvan.entity.UserEntity;
import com.softvan.exception.AccountLockedException;
import com.softvan.exception.InvalidCredentialException;
import com.softvan.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {


    public static final String USERNAME_IS_INCORRECT = "Username is incorrect";
    public static final String SORRY_YOUR_ACCOUNT_IS_LOCKED = "Sorry, Your account is locked !!";
    private static final String PASSWORD_IS_NOT_CORRECT = "password is not correct";
    //public static final String USER_IS_LOKED_YOU_CANT_TO_LOGIN = "User Is Loked You Cant to login ";

    private final PasswordEncoder passwordEncoder;

    private final UserRepsitory userRepsitory;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public String authenticate(LoginRequestDto loginRequestDto) {
        UserEntity userEntity = this.userRepsitory.getUserByUsername(loginRequestDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialException(USERNAME_IS_INCORRECT));
        if (userEntity.isLocked()){
            if (isLockTimeExpired(userEntity)){
                resetLock(userEntity);
            } else {
                throw new AccountLockedException(SORRY_YOUR_ACCOUNT_IS_LOCKED);
            }
        } else {
            if (isFailedAttemptWindowExpired(userEntity)){
                resetLock(userEntity);
            }
        }
        checkFailedAttemptExceed(userEntity);
        String role = userEntity.getRole().getName();
        String token;
        if (isPasswordCorrect(loginRequestDto, userEntity)) {
            token = this.jwtTokenProvider.createToken(userEntity.getEmail(), userEntity.getId(), role);
            userEntity.setLoginCount(userEntity.getLoginCount()+1);
            userEntity.setFailedAttempt(0);
            userEntity.setFailedAttemptWindowTime(null);
            this.userRepsitory.save(userEntity);
        } else {
            if (userEntity.getFailedAttempt() == 0){
                userEntity.setFailedAttemptWindowTime(LocalDateTime.now().plusMinutes(5));
            }
            userEntity.setFailedAttempt(userEntity.getFailedAttempt()+1);
            this.userRepsitory.save(userEntity);
            throw new InvalidCredentialException(PASSWORD_IS_NOT_CORRECT);
        }
        return token;
    }

    private boolean isPasswordCorrect(LoginRequestDto loginRequestDto, UserEntity userEntity) {
        return passwordEncoder.matches(loginRequestDto.getPassword(), userEntity.getPassword());
    }

    private static boolean isFailedAttemptWindowExpired(UserEntity userEntity) {
        if (userEntity.getFailedAttemptWindowTime() != null){
            return userEntity.getFailedAttemptWindowTime().isBefore(LocalDateTime.now());
        }
        return false;
    }

    private void checkFailedAttemptExceed(UserEntity userEntity) {
        if(userEntity.getFailedAttempt()==3){
            userEntity.setLocked(true);
            userEntity.setLockTime(LocalDateTime.now().plusMinutes(1));
            this.userRepsitory.save(userEntity);
            throw new AccountLockedException("Your account is locked, please try after 1 minutes");
        }
    }

    private void resetLock(UserEntity userEntity) {
        userEntity.setLocked(false);
        userEntity.setLockTime(null);
        userEntity.setFailedAttempt(0);
        userEntity.setFailedAttemptWindowTime(null);
    }

    private boolean isLockTimeExpired(UserEntity user) {
        return user.getLockTime().isBefore(LocalDateTime.now());
    }
}
