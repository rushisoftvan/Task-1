package com.softvan.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {

    private T data ;
    private Integer statusCode;
    private List<String> errors;

    public ApiResponse(T data, Integer status) {
        this.data = data;
        this.statusCode = status;
    }

    public ApiResponse(List<String> errors, Integer status) {
        this.errors = errors;
        this.statusCode = status;
    }
}
