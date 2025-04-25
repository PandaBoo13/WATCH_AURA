package com.example.WATCH_AURA.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AppException extends  RuntimeException{
    private ErroCode erroCode;
}
