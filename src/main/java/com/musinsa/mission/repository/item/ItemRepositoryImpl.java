package com.musinsa.mission.repository.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.musinsa.mission.domain.QItem;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

import java.util.List;

import static com.musinsa.mission.domain.QItem.item;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<Item> findAllByEachCategoryMinPrice() {
        QItem item1 = new QItem("item1");

        List<Item> itemResponseDTOS = queryFactory
                .selectFrom(item)
                .where(item.price.eq(
                        JPAExpressions
                                .select(item1.price.min())
                                .from(item1)
                                .where(item1.category.name.eq(item.category.name))
                ))
                .orderBy(item.category.name.asc())
                .fetch();
        return itemResponseDTOS;
    }

    public List<Item> findByCategoryLowestAndHighest(Category category) {
        List<Item> items = queryFactory.selectFrom(item)
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
                .fetch();
        items.stream().map(Item::getBrand).forEach(Hibernate::initialize);
        return items;
    }
}
