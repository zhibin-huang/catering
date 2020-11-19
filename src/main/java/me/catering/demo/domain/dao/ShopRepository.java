package me.catering.demo.domain.dao;

import me.catering.demo.domain.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ShopRepository extends PagingAndSortingRepository<Shop, Integer> {

    Page<Shop> findByTitleContaining(String keyword, Pageable pageable);

    Page<Shop> findAll( Pageable pageable);

    Shop findByShopId(int shopId);
}
