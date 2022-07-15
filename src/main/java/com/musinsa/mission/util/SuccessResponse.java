package com.musinsa.mission.util;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {

    private int status;
    private String message;

    public SuccessResponse(SuccessCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
    }
}
