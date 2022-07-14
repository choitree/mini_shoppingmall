package com.musinsa.mission.dto.item;

import com.musinsa.mission.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSimpleResponseDTO {

    private String brandName;
    private Integer itemPrice;

    public static ItemSimpleResponseDTO from(Item item) {
        return ItemSimpleResponseDTO.builder()
                .brandName(item.getBrand().getName())
                .itemPrice(item.getPrice())
                .build();
    }
}

