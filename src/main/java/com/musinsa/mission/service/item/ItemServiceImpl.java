package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.dto.item.ItemResponseDTO;
import com.musinsa.mission.dto.item.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.ItemsMinAndMixByCategoryResponseDTO;
import com.musinsa.mission.exception.CategoryNotFoundException;
import com.musinsa.mission.exception.config.ErrorCode;
import com.musinsa.mission.repository.CategoryRepository;
import com.musinsa.mission.repository.item.ItemRepository;
import com.musinsa.mission.repository.item.ItemRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositorySupport itemRepositorySupport;
    private final CategoryRepository categoryRepository;

    @Override
    public ItemsByEachCategoryMinPriceResponseDTO findAllByEachCategoryMinPrice() {
        List<ItemResponseDTO> itemResponseDTOS = itemRepositorySupport.findAllByEachCategoryMinPrice();
        itemResponseDTOS = itemResponseDTOS.stream()
                .distinct()
                .collect(Collectors.toList());
        return ItemsByEachCategoryMinPriceResponseDTO.from(itemResponseDTOS);
    }

    @Override
    public ItemSimpleResponseDTO findCheapestBrandSumOfAllCategory() {
        return itemRepositorySupport.findCheapestBrandSumOfAllCategory();
    }

    @Override
    public ItemsMinAndMixByCategoryResponseDTO findByCategoryLowestAndHighest(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND, "조회하려는 카테고리가 존재하지 않습니다."));
        List<ItemSimpleResponseDTO> itemSimpleResponseDTOS = itemRepositorySupport.findByCategoryLowestAndHighest(category);
        return ItemsMinAndMixByCategoryResponseDTO.convertListToItemsMinAndMixByCategoryResponseDTO(itemSimpleResponseDTOS);
    }
}
