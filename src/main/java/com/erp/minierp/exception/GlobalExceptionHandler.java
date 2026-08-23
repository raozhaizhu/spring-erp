package com.erp.minierp.exception;

import com.erp.minierp.common.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult<Void> handleRuntimeException(RuntimeException e){
        return AjaxResult.error(500, e.getMessage());
    }
}
