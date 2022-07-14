package com.musinsa.mission.controller;

import com.musinsa.mission.dto.item.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.ItemsResponseByEachCategoryMinPriceDTO;
import com.musinsa.mission.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("item")
@Controller
public class ItemController {

    private final ItemService itemService;
    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("/minCase")
    public ResponseEntity<ItemsResponseByEachCategoryMinPriceDTO> findAllByEachCategoryMinPrice() {
        logger.info("각 카테고리에서 최저값으로 추려서 리스트로 조회");
        return ResponseEntity.ok(itemService.findAllByEachCategoryMinPrice());
    }

    @GetMapping("/cheapestBrand")
    public ResponseEntity<ItemSimpleResponseDTO> findCheapestBrandSumOfAllCategory() {
        logger.info("모든 카테고리의 합산액이 최저가인 브랜드와 합산액 조회");
        return ResponseEntity.ok(itemService.findCheapestBrandSumOfAllCategory());
    }


    @GetMapping("/category")
    public ResponseEntity<List<ItemSimpleResponseDTO>> findByCategoryLowestAndHighest(@RequestParam String name) {
        logger.info("카테고리별 최소, 최대값 조회");
        return ResponseEntity.ok(itemService.findByCategoryLowestAndHighest(name));
    }
}
