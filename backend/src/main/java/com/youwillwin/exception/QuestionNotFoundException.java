package com.youwillwin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long id) {
        super("Question not found: " + id);
    }
}
