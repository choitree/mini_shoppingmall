package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Item;
import com.musinsa.mission.dto.item.request.ItemRequestDTO;
import com.musinsa.mission.dto.item.request.ItemUpdateRequestDTO;
import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsMinAndMixByCategoryResponseDTO;

public interface ItemService {

    ItemsByEachCategoryMinPriceResponseDTO findAllByEachCategoryMinPrice();

    ItemSimpleResponseDTO findCheapestBrandSumOfAllCategory();

    ItemsMinAndMixByCategoryResponseDTO findByCategoryLowestAndHighest(String categoryName);

    Item createItem(ItemRequestDTO itemRequestDTO);

    Item updateItem(Long id, ItemUpdateRequestDTO itemUpdateRequestDTO);

    void deleteItem(Long id);
}
