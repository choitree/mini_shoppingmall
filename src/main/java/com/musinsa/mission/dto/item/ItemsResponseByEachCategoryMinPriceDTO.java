package com.musinsa.mission.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class ItemsResponseByEachCategoryMinPriceDTO {

    private final List<ItemResponseDTO> itemResponseDTOS;

    private final Integer totalPrice;

    public static ItemsResponseByEachCategoryMinPriceDTO from(List<ItemResponseDTO> itemResponseDTOS) {
        Integer totalPrice = itemResponseDTOS.stream()
                .mapToInt(itemResponseDTO -> itemResponseDTO.getItemPrice())
                .sum();

        return ItemsResponseByEachCategoryMinPriceDTO.builder()
                .itemResponseDTOS(itemResponseDTOS)
                .totalPrice(totalPrice)
                .build();
    }
}
