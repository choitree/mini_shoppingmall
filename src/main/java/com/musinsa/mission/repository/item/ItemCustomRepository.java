package com.musinsa.mission.repository.item;

import com.musinsa.mission.dto.item.response.ItemSimpleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
@Repository
public class ItemCustomRepository {

    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public ItemSimpleResponseDTO findCheapestBrandSumOfAllCategory() {

        String sql = "select brand_name as brandName, sum(min) as itemPrice " +
                "from(" +
                "select min(item.price) as min, brand.name as brand_name, item.category_id " +
                "from item " +
                "join brand on brand.id = item.brand_id " +
                "group by brand.name, item.category_id " +
                ") as i2 " +
                "group by brand_name " +
                "order by sum(min) asc limit 1;";

        Query nativeQuery = entityManager.createNativeQuery(sql);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        return jpaResultMapper.uniqueResult(nativeQuery, ItemSimpleResponseDTO.class);
    }
}
