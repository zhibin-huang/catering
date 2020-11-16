package me.catring.demo.domain.dao;

import me.catring.demo.domain.entity.Food;
import me.catring.demo.domain.entity.CartItem;
import me.catring.demo.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    List<CartItem> findAllByUserByUserId(User userByUserId);

    CartItem findByUserByUserIdAndFoodByFoodId(User userByUserId, Food foodByFoodId);

    int countByUserByUserId(User userByUserId);

    void deleteByUserByUserId(User userByUserId);

    CartItem findByCartItemId(int cartItemId);
}
