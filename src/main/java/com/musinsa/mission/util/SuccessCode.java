package com.musinsa.mission.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuccessCode {

    CREATED(201, "저장되었습니다."),
    MODIFIED(200, "수정되었습니다."),
    DELETED(200, "삭제되었습니다.");


    private int status;
    private String message;
}
