package com.musinsa.mission.dto.item.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"brandName"})
public class ItemResponseDTO {

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("가격")
    private Integer itemPrice;
}
