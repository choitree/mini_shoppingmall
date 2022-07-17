package com.musinsa.mission.dto.item.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSimpleResponseDTO {

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("금액")
    private Integer itemPrice;

    public ItemSimpleResponseDTO(String brandName, BigDecimal itemPrice) {
        this.brandName = brandName;
        this.itemPrice = itemPrice.intValue();
    }
}

