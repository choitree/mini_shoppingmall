package com.musinsa.mission.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSimpleResponseDTO {

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("금액")
    private Integer itemPrice;
}

