package com.musinsa.mission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super(ErrorMessage.CATEGORY_NOT_FOUND.getErrorMessage());
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
