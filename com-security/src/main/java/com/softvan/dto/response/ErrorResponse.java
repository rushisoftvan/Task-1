package com.softvan.dto.response;


import java.util.Objects;

public final class ErrorResponse {
    private final String error;
    private final Integer code;

    public ErrorResponse(String error, Integer code) {
        this.error = error;
        this.code = code;
    }

    public String error() {
        return error;
    }

    public Integer code() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ErrorResponse) obj;
        return Objects.equals(this.error, that.error) &&
                Objects.equals(this.code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, code);
    }

    @Override
    public String toString() {
        return "ErrorResponse[" +
                "error=" + error + ", " +
                "code=" + code + ']';
    }

}
