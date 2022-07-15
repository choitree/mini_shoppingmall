package com.musinsa.mission.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "null")
    private String name;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public static Item CreateItem(ItemRequestDTO itemRequestDTO, Brand brand, Category category) {
        return Item.builder()
                .name(itemRequestDTO.getName())
                .price(itemRequestDTO.getPrice())
                .brand(brand)
                .category(category)
                .build();
    }
}
