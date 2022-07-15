package com.musinsa.mission.controller;

import com.musinsa.mission.dto.item.request.ItemRequestDTO;
import com.musinsa.mission.dto.item.request.ItemUpdateRequestDTO;
import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsByEachCategoryMinPriceResponseDTO;
import com.musinsa.mission.dto.item.response.ItemsMinAndMixByCategoryResponseDTO;
import com.musinsa.mission.service.item.ItemService;
import com.musinsa.mission.util.SuccessCode;
import com.musinsa.mission.util.SuccessResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("item")
@Controller
public class ItemController {

    private final ItemService itemService;
    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("/minCase")
    @ApiOperation(value = "카테고리별 최저가 품목 조회", notes = "전체 카테고리에서 최저가 1개 품목을 조회하고, 전체 최저가 합산액을 보여준다.")
    public ResponseEntity<ItemsByEachCategoryMinPriceResponseDTO> findAllByEachCategoryMinPrice() {
        logger.info("각 카테고리에서 최저값으로 추려서 리스트로 조회");
        return ResponseEntity.ok(itemService.findAllByEachCategoryMinPrice());
    }

    @GetMapping("/cheapestBrand")
    @ApiOperation(value = "한 브랜드의 합산액이 최저가인 브랜드 및 금액 조회", notes = "한개의 브랜드의 전체 카테고리에서 최저가 1개 품목의 합산액 중 최저가의 브랜드와 합산액을 보여준다.")
    public ResponseEntity<ItemSimpleResponseDTO> findCheapestBrandSumOfAllCategory() {
        logger.info("모든 카테고리의 합산액이 최저가인 브랜드와 합산액 조회");
        return ResponseEntity.ok(itemService.findCheapestBrandSumOfAllCategory());
    }

    @GetMapping("/category")
    @ApiImplicitParam(name = "name", value = "카테고리 이름")
    @ApiOperation(value = "카테고리별 최소/최대 금액 및 브랜드 조회", notes = "카테고리에 따라서 최저가와 브랜드, 최고가와 브랜드의 정보를 보여준다.")
    public ResponseEntity<ItemsMinAndMixByCategoryResponseDTO> findByCategoryLowestAndHighest(@RequestParam String name) {
        logger.info("카테고리별 최소, 최대값 조회");
        return ResponseEntity.ok(itemService.findByCategoryLowestAndHighest(name));
    }

    @PostMapping("")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "name", value = "상품명", dataType = "string"),
                    @ApiImplicitParam(name = "price", value = "상품 가격", required = true, dataType = "integer"),
                    @ApiImplicitParam(name = "brandName", value = "브랜드 이름", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "categoryName", value = "카테고리 이름", required = true, dataType = "string")
            })
    @ApiOperation(value = "카테고리별 최소/최대 금액 및 브랜드 조회", notes = "카테고리에 따라서 최저가와 브랜드, 최고가와 브랜드의 정보를 보여준다.")
    public ResponseEntity<SuccessResponse> createItem(@Validated @RequestBody ItemRequestDTO itemRequestDTO) {
        logger.info("상품 추가");
        itemService.createItem(itemRequestDTO);
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateItem(@PathVariable Long id,
                                                      @Validated @RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO) {
        logger.info("상품 수정");
        itemService.updateItem(id, itemUpdateRequestDTO);
        return ResponseEntity.ok(new SuccessResponse(SuccessCode.MODIFIED));
    }
}
