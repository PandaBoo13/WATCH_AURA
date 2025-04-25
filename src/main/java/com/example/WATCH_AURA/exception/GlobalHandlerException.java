package com.example.WATCH_AURA.exception;

import com.example.WATCH_AURA.dto.respone.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespone> handlingRuntimeException(AppException exception){
        ErroCode erroCode= exception.getErroCode();
        ApiRespone apiRespone= new ApiRespone();
        apiRespone.setSuccess(false);
        apiRespone.setMessage(erroCode.getMessage());
        return ResponseEntity
                .status(erroCode.getHttpStatusCode())
                .body(apiRespone);
    }

//
//    @ExceptionHandler(value = AccessDeniedException.class)
//
}
