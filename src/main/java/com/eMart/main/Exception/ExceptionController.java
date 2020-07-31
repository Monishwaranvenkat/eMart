package com.eMart.main.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ExceptionHandler.class,SQLException.class})
    public ResponseEntity<Object> exception(ExceptionHandler exception) {
        System.out.println(exception.getMessage());
        ErrorResponse errorResponse=new ErrorResponse(exception.getMessage(), BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}
