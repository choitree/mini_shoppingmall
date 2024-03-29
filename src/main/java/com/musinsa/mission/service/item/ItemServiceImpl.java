package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Brand;
import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.dto.item.request.ItemRequestDTO;
import com.musinsa.mission.dto.item.request.ItemUpdateRequestDTO;
import com.musinsa.mission.dto.item.response.ItemResponseDTO;
import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsMinAndMixByCategoryResponseDTO;
import com.musinsa.mission.exception.BrandNotFoundException;
import com.musinsa.mission.exception.CategoryNotFoundException;
import com.musinsa.mission.exception.ItemNotFoundException;
import com.musinsa.mission.repository.BrandRepository;
import com.musinsa.mission.repository.CategoryRepository;
import com.musinsa.mission.repository.item.ItemCustomRepository;
import com.musinsa.mission.repository.item.ItemRepository;
import com.musinsa.mission.repository.item.ItemRepositorySupport;
import com.musinsa.mission.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositorySupport itemRepositorySupport;
    private final ItemCustomRepository itemCustomRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private Category getCategory(String categoryName, String errorMessage) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND, errorMessage));
    }

    private Item getItem(Long id, String errorMessage) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND, errorMessage));
    }

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
        return itemCustomRepository.findCheapestBrandSumOfAllCategory();
    }

    @Override
    public ItemsMinAndMixByCategoryResponseDTO findByCategoryLowestAndHighest(String categoryName) {
        Category category = getCategory(categoryName, "조회하려는 카테고리가 존재하지 않습니다.");
        List<ItemSimpleResponseDTO> itemSimpleResponseDTOS = itemRepositorySupport.findByCategoryLowestAndHighest(category);
        return ItemsMinAndMixByCategoryResponseDTO.convertListToItemsMinAndMixByCategoryResponseDTO(itemSimpleResponseDTOS);
    }

    @Override
    public Item createItem(ItemRequestDTO itemRequestDTO) {
        Category category = getCategory(itemRequestDTO.getCategoryName(), "등록하려는 제품에 해당하는 카테고리가 존재하지 않습니다.");
        Brand brand = brandRepository.findByName(itemRequestDTO.getBrandName())
                .orElseThrow(() -> new BrandNotFoundException(ErrorCode.BRAND_NOT_FOUND, "등록하려는 제품에 해당하는 브랜드가 존재하지 않습니다."));

        Item item = Item.createItem(itemRequestDTO, brand, category);
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Long id, ItemUpdateRequestDTO itemUpdateRequestDTO) {
        Item item = getItem(id, "수정하려는 상품이 존재하지 않습니다.");
        item.updateItem(itemUpdateRequestDTO);
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = getItem(id, "삭제하려는 상품이 존재하지 않습니다.");
        itemRepository.delete(item);
    }
}
