package com.musinsa.mission.exception;

import com.musinsa.mission.util.ErrorCode;
import lombok.Getter;

@Getter
public class BrandNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public BrandNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND.getMessage());
    }

    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BrandNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}