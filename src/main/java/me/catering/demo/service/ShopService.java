package me.catering.demo.service;

import me.catering.demo.domain.entity.Shop;
import org.springframework.data.domain.Page;



public interface ShopService {

    Shop getShopById(int shopId);

    Iterable<Shop> getAllShops();

    Page<Shop> getAllShops(int page, int pageSize);

    Page<Shop> getSearchResult(String keyword, int page, int pageSize);

    void addShop(Shop shop);

    void updateShop(Shop shop);

    void deleteShop(int shopId);

    void updateAvatarFilename(String avatarFilename, int shop_id);

    String getAvatarFilenameByShopId(int shop_id);

}

