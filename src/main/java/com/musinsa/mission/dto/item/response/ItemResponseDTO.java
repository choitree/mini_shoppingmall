package com.musinsa.mission.dto.item.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.mission.domain.Item;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"brandName"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemResponseDTO {

    @JsonIgnore
    private Long itemId;

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("가격")
    private Integer itemPrice;

    private String itemName;

    public ItemResponseDTO(String categoryName, String brandName, Integer itemPrice) {
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.itemPrice = itemPrice;
    }

    public static ItemResponseDTO entityToDto(Item item) {
        return ItemResponseDTO.builder()
                .categoryName(item.getCategory().getName())
                .brandName(item.getBrand().getName())
                .itemPrice(item.getPrice())
                .build();
    }
}
