package com.softvan.exception;

import com.softvan.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class AdminGlobelExceptionHandler {

    private static final String EXCEPTION_MESSAGE = "Exception occured : ";

    @ExceptionHandler(value={MethodArgumentNotValidException.class, ConstraintViolationException.class})
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

    @ExceptionHandler(CustomException.class)
    public ApiResponse handleCustomeException(CustomException customException){
        String errorMessae = customException.getMessage();
       return new ApiResponse(Arrays.asList(errorMessae),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }




}
