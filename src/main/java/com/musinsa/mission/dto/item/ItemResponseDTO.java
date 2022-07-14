package com.musinsa.mission.dto.item;

import com.musinsa.mission.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class ItemResponseDTO {

    private final String brandName;
    private final Integer itemPrice;

    public static ItemResponseDTO from(Item item) {
        return ItemResponseDTO.builder()
                .brandName(item.getBrand().getName())
                .itemPrice(item.getPrice())
                .build();
    }
}
