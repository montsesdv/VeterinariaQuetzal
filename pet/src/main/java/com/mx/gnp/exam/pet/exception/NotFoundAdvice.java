package com.mx.gnp.exam.pet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(OwnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ownerNotFoundHandler(OwnerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SaveNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String saveNotFoundHandler(SaveNotFoundException ex) {
        return ex.getMessage();
    }
}
