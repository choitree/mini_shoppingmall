package com.musinsa.mission.dto.item.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
public class ItemUpdateRequestDTO {

    private String name;

    @Min(value = 0, message = "가격은 0원 이상입니다.")
    private Integer price;
}
