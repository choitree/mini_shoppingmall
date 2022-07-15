package com.musinsa.mission.repository.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.domain.QItem;
import com.musinsa.mission.dto.item.response.ItemResponseDTO;
import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.musinsa.mission.domain.QItem.item;

@Repository
public class ItemRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ItemRepositorySupport(JPAQueryFactory queryFactory) {
        super(Item.class);
        this.queryFactory = queryFactory;
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDTO> findAllByEachCategoryMinPrice() {
        QItem item1 = new QItem("item1");

        List<ItemResponseDTO> itemResponseDTOS = queryFactory
                .select(Projections.constructor(ItemResponseDTO.class,
                        item.category.name.as("categoryName"),
                        item.brand.name.as("brandName"),
                        item.price.as("itemPrice")
                ))
                .from(item)
                .where(item.price.eq(
                        JPAExpressions
                                .select(item1.price.min())
                                .from(item1)
                                .where(item1.category.name.eq(item.category.name))
                ))
                .orderBy(item.category.id.asc())
                .fetch();
        return itemResponseDTOS;
    }

    @Transactional(readOnly = true)
    public List<ItemSimpleResponseDTO> findByCategoryLowestAndHighest(Category category) {
        return queryFactory.select(Projections.constructor(ItemSimpleResponseDTO.class,
                        item.brand.name,
                        item.price
                ))
                .from(item)
                .where((item.price.eq(
                                JPAExpressions
                                        .select(item.price.min())
                                        .from(item)
                                        .where(item.category.eq(category))
                        )
                        .or(
                                item.price.eq(
                                        JPAExpressions
                                                .select(item.price.max())
                                                .from(item)
                                                .where(item.category.eq(category))
                                )))
                        .and(item.category.eq(category)))
                .orderBy(item.price.asc())
                .fetch();
    }
}
