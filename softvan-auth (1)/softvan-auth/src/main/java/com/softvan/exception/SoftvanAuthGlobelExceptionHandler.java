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
    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse handleProductNotFoundException(UserNotFoundException ex) {
        String error = ex.getMessage();
        log.error(EXCEPTION_MESSAGE , ex);
        return new ApiResponse(Arrays.asList(error), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserLokedException.class)
    public ApiResponse handleUserLokedException(UserLokedException userLokedException){
        String message = userLokedException.getMessage();
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
