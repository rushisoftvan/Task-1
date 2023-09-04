package com.softvan.exception;

import com.softvan.dto.response.ApiResponse;
import com.softvan.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class AdminGlobelExceptionHandler {

    private static final String EXCEPTION_MESSAGE = "Exception occured : ";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> finalErrors = new ArrayList<>();
        List<ObjectError> errors = ex.getAllErrors();
        for (ObjectError error : errors) {
            String message = error.getDefaultMessage();
            finalErrors.add(message);
        }
        log.error(EXCEPTION_MESSAGE , ex);
        return new ApiResponse<>(finalErrors, HttpStatus.BAD_REQUEST.value());
    }



}
