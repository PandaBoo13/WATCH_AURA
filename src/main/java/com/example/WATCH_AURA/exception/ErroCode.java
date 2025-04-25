package com.example.WATCH_AURA.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErroCode {
    USER_EXISTED(1001,"User existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1002,"Login unsuccessfully", HttpStatus.UNAUTHORIZED),
    USER_NOT_EXISTS(1003,"User not exists", HttpStatus.NOT_FOUND)
    ;


    ErroCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode=httpStatusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}