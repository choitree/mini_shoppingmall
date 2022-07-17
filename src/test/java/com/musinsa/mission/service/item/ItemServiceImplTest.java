package com.musinsa.mission.service.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.dto.item.response.ItemResponseDTO;
import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsMinAndMixByCategoryResponseDTO;
import com.musinsa.mission.repository.BrandRepository;
import com.musinsa.mission.repository.CategoryRepository;
import com.musinsa.mission.repository.item.ItemCustomRepository;
import com.musinsa.mission.repository.item.ItemRepositorySupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
class ItemServiceImplTest {

    @Autowired
    ItemServiceImpl itemService;

    @MockBean
    ItemRepositorySupport itemRepositorySupport;
    @MockBean
    ItemCustomRepository itemCustomRepository;
    @MockBean
    BrandRepository brandRepository;
    @MockBean
    CategoryRepository categoryRepository;

    @AfterEach
    void afterEach() {
        brandRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("카테고리별 최저가 아이템 정보와 합산액 테스트")
    void findAllByEachCategoryMinPrice() {
        when(itemRepositorySupport.findAllByEachCategoryMinPrice())
                .thenReturn(List.of(
                        new ItemResponseDTO("shoes", "A", 1000),
                        new ItemResponseDTO("pants", "B", 13000)
                ));

        ItemsByEachCategoryMinPriceResponseDTO serviceResult = itemService.findAllByEachCategoryMinPrice();

        assertThat(serviceResult.getTotalPrice()).isEqualTo(14000);
        assertThat(serviceResult.getItemResponseDTOS().get(0).getItemPrice()).isEqualTo(1000);
        assertThat(serviceResult.getItemResponseDTOS().get(1).getBrandName()).isEqualTo("B");
        assertThat(serviceResult.getItemResponseDTOS().get(1).getCategoryName()).isEqualTo("pants");
    }

    @Test
    @DisplayName("브랜드별 합산액 중 최저가인 브랜드명과 최저가 테스트")
    void findCheapestBrandSumOfAllCategory() {
        ItemSimpleResponseDTO repositoryResult = ItemSimpleResponseDTO.builder()
                .brandName("B")
                .itemPrice(15000)
                .build();

        when(itemCustomRepository.findCheapestBrandSumOfAllCategory())
                .thenReturn(repositoryResult);

        ItemSimpleResponseDTO serviceResult = itemService.findCheapestBrandSumOfAllCategory();

        assertThat(serviceResult.getBrandName()).isEqualTo("B");
        assertThat(serviceResult.getItemPrice()).isEqualTo(15000);
    }

    @Test
    @DisplayName("카테고리의 최저가/최고가 상품 테스트")
    void findByCategoryLowestAndHighest() {
        Category pants = new Category("pants");

        when(categoryRepository.findByName("pants"))
                .thenReturn(Optional.of(pants));
        when(itemRepositorySupport.findByCategoryLowestAndHighest(pants))
                .thenReturn(List.of(
                        new ItemSimpleResponseDTO("B", 13000),
                        new ItemSimpleResponseDTO("A", 15000)
                ));

        ItemsMinAndMixByCategoryResponseDTO serviceResult = itemService.findByCategoryLowestAndHighest("pants");

        assertThat(serviceResult.getMinItem().getBrandName()).isEqualTo("B");
        assertThat(serviceResult.getMinItem().getItemPrice()).isEqualTo(13000);
        assertThat(serviceResult.getMaxItem().getBrandName()).isEqualTo("A");
        assertThat(serviceResult.getMaxItem().getItemPrice()).isEqualTo(15000);
    }
}