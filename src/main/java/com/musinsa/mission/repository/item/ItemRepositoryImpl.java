package com.musinsa.mission.repository.item;

import com.musinsa.mission.domain.Category;
import com.musinsa.mission.domain.Item;
import com.querydsl.core.Tuple;
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

    /**
     *
     * 카테고리별 최소값과 제품의 브랜드를 추출
     * querydsl은 from 절에 서브쿼리 사용 불가능
    select i.category_id, i.brand_id, i.price
    from(
        select category_id, min(price) as minprice
        from item
        group by category_id
    ) as j
    inner join item as i on i.category_id = j.category_id and i.price = j.minprice;
     */

    public List<Item> findAllByEachCategoryMinPrice() {
        List<Tuple> results =  queryFactory
                .select(item.category, item.brand, item.price)
                .from(queryFactory
                        .select(item.category, item.price.min())
                        .from(item)
                        .groupBy(item.category))
                .innerJoin(item.as("i1"), item.as("i2"))
                .on(item.category.eq("i2.category")
                        .and(item.price).eq("i2.minprice"))
                .fetch();
    }
}
