package com.musinsa.mission.repository.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.musinsa.mission.domain.QItem.item;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<Item> findByCategoryLowestAndHighest(Category category) {
        return queryFactory.selectFrom(item)
                .where((item.price.eq(
                                queryFactory.select(item.price.min())
                                        .from(item)
                                        .where(item.category.eq(category))
                        )
                        .or(
                                item.price.eq(
                                        queryFactory.select(item.price.max())
                                                .from(item)
                                                .where(item.category.eq(category))
                                )))
                        .and(item.category.eq(category)))
                .fetch();
    }
}
