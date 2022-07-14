package com.musinsa.mission.exception;

import com.musinsa.mission.exception.config.ErrorCode;
import lombok.Getter;

@Getter
public class CategoryNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public CategoryNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND.getMessage());
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CategoryNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
