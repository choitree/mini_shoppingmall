package com.musinsa.mission.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class ItemsByEachCategoryMinPriceResponseDTO {

    @JsonProperty("카테고리별 최저가 아이템")
    private final List<ItemResponseDTO> itemResponseDTOS;

    @JsonProperty("총합")
    private final Integer totalPrice;

    public static ItemsByEachCategoryMinPriceResponseDTO from(List<ItemResponseDTO> itemResponseDTOS) {
        Integer totalPrice = itemResponseDTOS.stream()
                .mapToInt(itemResponseDTO -> itemResponseDTO.getItemPrice())
                .sum();

        return ItemsByEachCategoryMinPriceResponseDTO.builder()
                .itemResponseDTOS(itemResponseDTOS)
                .totalPrice(totalPrice)
                .build();
    }
}
