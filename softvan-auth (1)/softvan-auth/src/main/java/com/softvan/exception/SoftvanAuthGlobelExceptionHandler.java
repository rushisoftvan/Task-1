package com.softvan.exception;

import com.softvan.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class SoftvanAuthGlobelExceptionHandler {
    private static final String EXCEPTION_MESSAGE = "Exception occured : ";


    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccountLockedException.class)
    public ApiResponse handleUserLokedException(AccountLockedException accountLockedException){
        String message = accountLockedException.getMessage();
        return new ApiResponse(Arrays.asList(message),HttpStatus.UNAUTHORIZED.value());
    }

    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialException.class)
    public ApiResponse handleInvalidCredentialException(InvalidCredentialException ex){
        String message = ex.getMessage();
        return new ApiResponse(Arrays.asList(message),HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> finalErrors = new ArrayList<>();
        List<ObjectError> errors = ex.getAllErrors();
        for (ObjectError error : errors) {
            String message = error.getDefaultMessage();
            finalErrors.add(message);
        }
        log.error(EXCEPTION_MESSAGE , ex);
        return new ApiResponse(finalErrors, HttpStatus.BAD_REQUEST.value());
    }
}
