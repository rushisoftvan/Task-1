package com.softvan.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public final class ApiResponse<T> {

    private final T data;
    private  final Integer code;

    public ApiResponse(T data, Integer code) {
        this.data = data;
        this.code = code;
    }
}
