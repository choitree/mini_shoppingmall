package com.musinsa.mission.service.item;

import com.musinsa.mission.dto.item.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.ItemsResponseByEachCategoryMinPriceDTO;

import java.util.List;

public interface ItemService {

    ItemsResponseByEachCategoryMinPriceDTO findAllByEachCategoryMinPrice();

    List<ItemSimpleResponseDTO> findByCategoryLowestAndHighest(String categoryName);
}
