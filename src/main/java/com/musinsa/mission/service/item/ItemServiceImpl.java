package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.dto.item.ItemResponseDTO;
import com.musinsa.mission.dto.item.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.ItemsResponseByEachCategoryMinPriceDTO;
import com.musinsa.mission.exception.CategoryNotFoundException;
import com.musinsa.mission.exception.config.ErrorCode;
import com.musinsa.mission.repository.CategoryRepository;
import com.musinsa.mission.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ItemsResponseByEachCategoryMinPriceDTO findAllByEachCategoryMinPrice() {
        List<Item> items = itemRepository.findAllByEachCategoryMinPrice();
        items = items.stream()
                .distinct()
                .collect(Collectors.toList());
        List<ItemResponseDTO> itemResponseDTOS = items.stream()
                .map(item -> ItemResponseDTO.from(item))
                .collect(Collectors.toList());
        return ItemsResponseByEachCategoryMinPriceDTO.from(itemResponseDTOS);
    }

    @Override
    public ItemSimpleResponseDTO findCheapestBrandSumOfAllCategory() {
        return itemRepository.findCheapestBrandSumOfAllCategory();
    }

    @Override
    public List<ItemSimpleResponseDTO> findByCategoryLowestAndHighest(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND, "조회하려는 카테고리가 존재하지 않습니다."));
        List<Item> items = itemRepository.findByCategoryLowestAndHighest(category);
        return items.stream()
                .map(item -> ItemSimpleResponseDTO.from(item))
                .collect(Collectors.toList());
    }
}
