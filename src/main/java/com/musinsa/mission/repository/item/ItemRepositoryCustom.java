package com.musinsa.mission.repository.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> findByCategoryLowestAndHighest(Category category);
}
