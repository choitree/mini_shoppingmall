package com.musinsa.mission.domain;

import com.musinsa.mission.dto.item.request.ItemRequestDTO;
import com.musinsa.mission.dto.item.request.ItemUpdateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_item_brand"))
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_item_category"))
    private Category category;

    public Item(Integer price, Brand brand, Category category) {
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

    public static Item createItem(ItemRequestDTO itemRequestDTO, Brand brand, Category category) {
        return Item.builder()
                .name(itemRequestDTO.getName())
                .price(itemRequestDTO.getPrice())
                .brand(brand)
                .category(category)
                .build();
    }

    public void updateItem(ItemUpdateRequestDTO itemUpdateRequestDTO) {
        this.name = itemUpdateRequestDTO.getName();
        this.price = itemUpdateRequestDTO.getPrice();
    }
}
