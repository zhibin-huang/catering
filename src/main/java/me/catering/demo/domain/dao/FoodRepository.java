package me.catering.demo.domain.dao;

import me.catering.demo.domain.entity.Food;
import me.catering.demo.domain.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FoodRepository extends PagingAndSortingRepository<Food, Integer> {
    Page<Food> findAllByForSaleIsTrueAndShopByShopId(Shop shopByShopId, Pageable pageable);

    Page<Food> findByFoodTitleContaining(String keyword, Pageable pageable);

    Page<Food> findAllByShopByShopId(Shop shopByShopId, Pageable pageable);

    Iterable<Food> findAllByShopByShopId(Shop shopByShopId);

    Food findByFoodId(int foodId);
}
