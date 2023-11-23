package com.softvan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public  class ApiResponse<T> {

    private  T data;
    private   Integer code;
    private  List<String> errors;


    public ApiResponse(){

    }

    public ApiResponse(T data , Integer code) {
        this.data=data;
        this.code=code;
    }
    public ApiResponse(List<String>errors,Integer code){
        this.errors = errors;
        this.code=code;
    }


    public static ApiResponse from(Object data, Integer code){
        return new ApiResponse(data,code);
    }

    public static ApiResponse from(List<String> errors, Integer code){
        return new ApiResponse(errors,code);
    }

}
