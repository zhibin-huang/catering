package me.catering.demo.domain.dao;

import me.catering.demo.domain.entity.Food;
import me.catering.demo.domain.entity.CartItem;
import me.catering.demo.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    List<CartItem> findAllByUserByUserId(User userByUserId);

    CartItem findByUserByUserIdAndFoodByFoodId(User userByUserId, Food foodByFoodId);

    int countByUserByUserId(User userByUserId);

    void deleteByUserByUserId(User userByUserId);

    CartItem findByCartItemId(int cartItemId);
}
