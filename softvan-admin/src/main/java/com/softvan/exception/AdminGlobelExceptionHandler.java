package com.softvan.exception;

import com.softvan.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminGlobelExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return new ErrorResponse(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND.value());
    }

}
