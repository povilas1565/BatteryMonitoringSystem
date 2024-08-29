package com.smartBattery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BatteryException.class)
    public ResponseEntity<MyErrorDetails> batteryNotFound(BatteryException e, WebRequest req) {

        MyErrorDetails err = new MyErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BatteryDataException.class)
    public ResponseEntity<MyErrorDetails> batteryDataNotFound(BatteryDataException e, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();

        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> exceptionHandler(Exception e, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();

        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

}
