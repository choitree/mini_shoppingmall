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
public class ItemResponseDTO {

    private String categoryName;
    private String brandName;
    private Integer itemPrice;

    public static ItemResponseDTO from(Item item) {
        return ItemResponseDTO.builder()
                .brandName(item.getBrand().getName())
                .itemPrice(item.getPrice())
                .categoryName(item.getCategory().getName())
                .build();
    }
}
