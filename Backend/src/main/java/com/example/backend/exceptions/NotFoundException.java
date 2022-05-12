package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ServerException {

    public NotFoundException() {
        super("Not Found (404)");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
