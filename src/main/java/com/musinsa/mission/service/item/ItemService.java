package com.musinsa.mission.service.item;

import com.musinsa.mission.dto.item.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.ItemsMinAndMixByCategoryResponseDTO;

public interface ItemService {

    ItemsByEachCategoryMinPriceResponseDTO findAllByEachCategoryMinPrice();

    ItemSimpleResponseDTO findCheapestBrandSumOfAllCategory();

    ItemsMinAndMixByCategoryResponseDTO findByCategoryLowestAndHighest(String categoryName);
}
