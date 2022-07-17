package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Brand;
import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.dto.item.request.ItemRequestDTO;
import com.musinsa.mission.dto.item.request.ItemUpdateRequestDTO;
import com.musinsa.mission.repository.BrandRepository;
import com.musinsa.mission.repository.CategoryRepository;
import com.musinsa.mission.repository.item.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class RestItemServiceTest {

    @Autowired
    ItemServiceImpl itemService;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("상품 생성 테스트")
    @Transactional
    void createItem() {

        Category socks = Category.builder()
                .name("socks")
                .build();

        Brand c = Brand.builder()
                .name("C")
                .build();

        categoryRepository.save(socks);
        brandRepository.save(c);

        Item createdItem = itemService.createItem(ItemRequestDTO.builder()
                .categoryName(socks.getName())
                .brandName(c.getName())
                .price(1000)
                .build()
        );

        assertThat(createdItem.getPrice()).isNotEqualTo(1200);
        assertThat(createdItem.getPrice()).isEqualTo(1000);
        assertThat(createdItem.getBrand().getName()).isEqualTo("C");
    }

    @Test
    @DisplayName("상품 수정 테스트")
    @Transactional
    void updateItem() {

        final Category socks = Category.builder()
                .name("socks")
                .build();

        final Brand c = Brand.builder()
                .name("C")
                .build();

        brandRepository.save(c);
        categoryRepository.save(socks);

        Item createdItem = itemService.createItem(ItemRequestDTO.builder()
                .categoryName(socks.getName())
                .brandName(c.getName())
                .price(1000)
                .build()
        );

        Item updatedItem = itemService.updateItem(createdItem.getId(), ItemUpdateRequestDTO.builder()
                .name("[sale]brand new socks")
                .price(1000)
                .build());

        assertThat(updatedItem.getPrice()).isNotEqualTo(1200);
        assertThat(updatedItem.getPrice()).isEqualTo(1000);
        assertThat(updatedItem.getBrand().getName()).isEqualTo("C");
        assertThat(updatedItem.getName()).isNotNull();
        assertThat(updatedItem.getName()).contains("[sale]");
    }

    @DisplayName("상품 삭제 테스트")
    @Test
    @Transactional
    void deleteItem() {

        final Category socks = Category.builder()
                .name("socks")
                .build();

        final Brand c = Brand.builder()
                .name("C")
                .build();

        brandRepository.save(c);
        categoryRepository.save(socks);

        Item createdItem = itemService.createItem(ItemRequestDTO.builder()
                .categoryName(socks.getName())
                .brandName(c.getName())
                .price(1000)
                .build()
        );

        itemRepository.save(createdItem);

        assertTrue(itemRepository.findById(createdItem.getId()).isPresent());

        itemService.deleteItem(createdItem.getId());

        assertThrows(NoSuchElementException.class,
                () -> itemRepository.findById(createdItem.getId()).get());
    }
}
