package com.bookstorereactive.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotValidated extends RuntimeException {
    public BookNotValidated(String message) {
        super(message);
    }
}
