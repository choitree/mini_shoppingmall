package com.musinsa.mission.repository.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.dto.item.ItemSimpleResponseDTO;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> findAllByEachCategoryMinPrice();

    ItemSimpleResponseDTO findCheapestBrandSumOfAllCategory();

    List<Item> findByCategoryLowestAndHighest(Category category);
}
