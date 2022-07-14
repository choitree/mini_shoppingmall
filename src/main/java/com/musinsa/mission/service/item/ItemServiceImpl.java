package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.dto.item.ItemResponseDTO;
import com.musinsa.mission.exception.CategoryNotFoundException;
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
    public List<ItemResponseDTO> findByCategoryLowestAndHighest(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new CategoryNotFoundException());
        List<Item> items = itemRepository.findByCategoryLowestAndHighest(category);
        return items.stream()
                .map(item -> ItemResponseDTO.from(item))
                .collect(Collectors.toList());
    }

}