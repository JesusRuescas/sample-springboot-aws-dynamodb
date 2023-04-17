package com.dynamodb.aws.springboot.springbootawsdynamodb.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dynamodb.aws.springboot.springbootawsdynamodb.exceptions.ExceptionResponse;
import com.dynamodb.aws.springboot.springbootawsdynamodb.exceptions.ExceptionUnsuported;

@ControllerAdvice
@RestController
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionUnsuported.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(ExceptionUnsuported ex,
            WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
