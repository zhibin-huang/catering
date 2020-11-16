package me.catring.demo.service.impl;

import me.catring.demo.domain.dao.ShopRepository;
import me.catring.demo.domain.dao.UserRepository;
import me.catring.demo.domain.entity.Food;
import me.catring.demo.domain.entity.Shop;
import me.catring.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    private final UserRepository userRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, UserRepository userRepository) {
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
    }

    // about shops
    public Shop getShopById(int shop_id) {
        return shopRepository.findById(shop_id).get();
    }

    public Iterable<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Page<Shop> getSearchResult(String keyword, int page, int pageSize) {
        return shopRepository.findByTitleContaining(keyword, PageRequest.of(page, pageSize, Sort.by("shopId")));
    }

    public Page<Shop> getAllShops( int page, int pageSize) {
        return shopRepository.findAll( PageRequest.of(page, pageSize, Sort.by("shopId")));
    }

    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    public void updateShop(Shop shop) {
        shopRepository.save(shop);
    }

    public void deleteShop(int shop_id) {
        shopRepository.delete(getShopById(shop_id));
    }

    public void updateAvatarFilename(String avatarFilename, int shop_id) {
        Shop shop = shopRepository.findByShopId(shop_id);
        shop.setCoverFilename(avatarFilename);
        shopRepository.save(shop);
    }

    public String getAvatarFilenameByShopId(int shop_id) {
        return shopRepository.findByShopId(shop_id).getCoverFilename();
    }

}
