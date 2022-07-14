package com.musinsa.mission.service.item;

import com.musinsa.mission.dto.item.ItemResponseDTO;

import java.util.List;

public interface ItemService {

    List<ItemResponseDTO> findByCategoryLowestAndHighest(String categoryName);
}
