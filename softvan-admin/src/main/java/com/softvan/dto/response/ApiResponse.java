package com.softvan.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private T data;
    private Integer code;

    public ApiResponse(T data,Integer code){
        this.data=data;
        this.code=code;
    }
}
