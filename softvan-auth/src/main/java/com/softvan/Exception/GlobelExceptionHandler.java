package com.softvan.Exception;

import com.softvan.dto.response.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobelExceptionHandler {

    public static final String USERNAME_OR_PASSWORD_INCORRECT = "username or password incorrect";
    public static final String NO_PERMISION = "No permision";
    public static final String JWT_TOKEN_HAS_BEEN_EXPIRED = "JWT Token has been Expired";

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<Object> handleAuthenticationException(Exception ex){
        return new ApiResponse<>(Arrays.asList(USERNAME_OR_PASSWORD_INCORRECT),HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ApiResponse<Object> handleExpiredJwtException(ExpiredJwtException ex){
        return new ApiResponse<>(Arrays.asList(JWT_TOKEN_HAS_BEEN_EXPIRED),HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<Object> handle(AccessDeniedException ex){
        //String errorMessage = ex.getMessage();
        return new ApiResponse<>(Arrays.asList(NO_PERMISION),HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(JwtException.class)
    public ApiResponse<Object> handleJwtException(JwtException ex){
        //List<String> finalerror = new ArrayList<>();
        //if(ex instanceof ExpiredJwtException){
        // finalerror.add("jwt token has been exprired");
        //}
        //else{
        //    finalerror.add(ex.getMessage());
        //}
        String  errorMessage = ex.getMessage();
        return new ApiResponse<>(Arrays.asList(errorMessage),HttpStatus.UNAUTHORIZED.value());
    }

}
