package com.musinsa.mission.dto.item.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class ItemsMinAndMixByCategoryResponseDTO {

    @JsonProperty("최소")
    private final ItemSimpleResponseDTO minItem;

    @JsonProperty("최대")
    private final ItemSimpleResponseDTO maxItem;

    public static ItemsMinAndMixByCategoryResponseDTO convertListToItemsMinAndMixByCategoryResponseDTO(List<ItemSimpleResponseDTO> itemSimpleResponseDTOS) {
        return ItemsMinAndMixByCategoryResponseDTO.builder()
                .minItem(itemSimpleResponseDTOS.get(0))
                .maxItem(itemSimpleResponseDTOS.get(itemSimpleResponseDTOS.size() - 1))
                .build();
    }
}
