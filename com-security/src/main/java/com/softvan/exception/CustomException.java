package com.softvan.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class CustomException extends RuntimeException{

    private  String msg;
    private  HttpStatus status;
    public CustomException(String msg, HttpStatus status){
       super(msg);
        this.status=status;
    }


}
