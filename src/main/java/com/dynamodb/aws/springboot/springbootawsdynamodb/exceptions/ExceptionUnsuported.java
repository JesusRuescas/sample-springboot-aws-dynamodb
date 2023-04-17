package com.dynamodb.aws.springboot.springbootawsdynamodb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionUnsuported extends NullPointerException {
    private static final long serialVersionUID = 1L;

    public ExceptionUnsuported(String exception) {
        super(exception);
    }
}