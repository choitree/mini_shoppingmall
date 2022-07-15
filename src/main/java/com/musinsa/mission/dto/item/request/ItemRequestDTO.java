package com.musinsa.mission.dto.item.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class ItemRequestDTO {

    private String name;

    @Min(value = 0, message = "가격은 0원 이상입니다.")
    private Integer price;

    @NotBlank(message = "브랜드명을 입력하세요")
    private String brandName;

    @NotBlank(message = "카테고리명을 입력하세요")
    private String categoryName;
}
