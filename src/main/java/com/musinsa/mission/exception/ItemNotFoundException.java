package com.musinsa.mission.exception;

import com.musinsa.mission.util.ErrorCode;
import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public ItemNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND.getMessage());
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ItemNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}