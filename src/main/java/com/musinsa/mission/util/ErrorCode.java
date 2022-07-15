package com.musinsa.mission.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INTER_SERVER_ERROR(500, "INTER SERVER ERROR"),

    CATEGORY_NOT_FOUND(404, "해당 카테고리를 찾을 수 없습니다."),
    BRAND_NOT_FOUND(404, "해당 브랜드를 찾을 수 없습니다."),
    ITEM_NOT_FOUND(404, "해당 상품을 찾을 수 없습니다."),
    ITEM_NOT_CREATE(400, "해당 상품이 생성되지 않았습니다."),
    ITEM_NOT_CHANGE(400, "해당 상품의 정보를 변경할 수 없습니다."),
    ITEM_EXIST(409, "생성하려는 상품이 이미 존재합니다.");

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
