package com.bookstorereactive.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFound extends RuntimeException {
    public BookNotFound(String message) {
        super(message);
    }
}
