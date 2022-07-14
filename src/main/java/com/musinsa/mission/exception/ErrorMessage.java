package com.musinsa.mission.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    CATEGORY_NOT_FOUND("해당 카테고리를 찾을 수 없습니다."),
    BRAND_NOT_FOUND("해당 브랜드를 찾을 수 없습니다."),
    ITEM_NOT_FOUND("해당 상품을 찾을 수 없습니다."),
    ITEM_NOT_CREATE("해당 상품이 생성되지 않았습니다."),
    ITEM_NOT_CHANGE("해당 상품의 정보를 변경할 수 없습니다.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
