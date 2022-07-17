package com.musinsa.mission.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.mission.dto.item.request.ItemRequestDTO;
import com.musinsa.mission.dto.item.request.ItemUpdateRequestDTO;
import com.musinsa.mission.dto.item.response.ItemResponseDTO;
import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsMinAndMixByCategoryResponseDTO;
import com.musinsa.mission.service.item.ItemServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @MockBean
    ItemServiceImpl itemService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("카테고리별 최저가 뽑아서 리스트, 총액 검사")
    void findAllByEachCategoryMinPrice() throws Exception {
        ItemsByEachCategoryMinPriceResponseDTO result = ItemsByEachCategoryMinPriceResponseDTO.from(
                List.of(
                        new ItemResponseDTO("상의", "C", 10000),
                        new ItemResponseDTO("아우터", "E", 5000),
                        new ItemResponseDTO("액세서리", "F", 1900)
                )
        );

        given(itemService.findAllByEachCategoryMinPrice())
                .willReturn(result);

        mockMvc.perform(get("/item/minCase"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.최저가리스트.[0].카테고리").value(equalTo("상의")))
                .andExpect(jsonPath("$.최저가리스트.[0].브랜드").value(equalTo("C")))
                .andExpect(jsonPath("$.최저가리스트.[0].가격").value(equalTo(10000)))
                .andExpect(jsonPath("총합").value(equalTo(16900)));
    }

    @Test
    @DisplayName("한 브랜드의 합산액이 최저가인 브랜드 및 금액 테스트")
    void findCheapestBrandSumOfAllCategory() throws Exception {
        ItemSimpleResponseDTO result = new ItemSimpleResponseDTO("A", 55000);

        given(itemService.findCheapestBrandSumOfAllCategory())
                .willReturn(result);

        mockMvc.perform(get("/item/cheapestBrand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("브랜드").value(equalTo("A")))
                .andExpect(jsonPath("금액").value(equalTo(55000)));
    }

    @Test
    @DisplayName("카테고리별 최소/최대 금액, 브랜드 테스트")
    void findByCategoryLowestAndHighest() throws Exception {
        ItemsMinAndMixByCategoryResponseDTO result = ItemsMinAndMixByCategoryResponseDTO
                .convertListToItemsMinAndMixByCategoryResponseDTO(List.of(
                        new ItemSimpleResponseDTO("A", 100),
                        new ItemSimpleResponseDTO("B", 500)
                ));

        given(itemService.findByCategoryLowestAndHighest("top"))
                .willReturn(result);

        mockMvc.perform(get("/item/category")
                        .param("name", "top"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.최소.브랜드").value(equalTo("A")))
                .andExpect(jsonPath("$.최소.금액").value(equalTo(100)))
                .andExpect(jsonPath("$.최대.브랜드").value(equalTo("B")))
                .andExpect(jsonPath("$.최대.금액").value(equalTo(500)));
    }

    @Test
    @DisplayName("상품 생성 테스트")
    void createItem() throws Exception {
        ItemRequestDTO request = ItemRequestDTO.builder()
                .brandName("A")
                .categoryName("상의")
                .price(1000)
                .build();

        mockMvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(equalTo("저장되었습니다.")))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void updateItem() throws Exception {
        ItemUpdateRequestDTO request = ItemUpdateRequestDTO.builder()
                .name("A브랜드의 상의 세일")
                .price(500)
                .build();

        mockMvc.perform(put("/item/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(equalTo("수정되었습니다.")))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteItem() throws Exception {
        mockMvc.perform(delete("/item/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(equalTo("삭제되었습니다.")))
                .andDo(print());
    }
}
