package me.catring.demo.service.impl;

import me.catring.demo.domain.entity.Food;
import me.catring.demo.domain.entity.CartItem;
import me.catring.demo.domain.dao.FoodRepository;
import me.catring.demo.domain.dao.CartItemRepository;
import me.catring.demo.domain.dao.UserRepository;
import me.catring.demo.domain.entity.User;
import me.catring.demo.service.CartService;
import me.catring.demo.web.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    // ***Repository是数据访问层对象，提供相应的数据操作方法
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;
    @Autowired
    public CartServiceImpl(UserRepository userRepository, CartItemRepository cartItemRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.foodRepository = foodRepository;
    }

    //获取用户购物车的所有项
    public List<CartItem> getAllItems(String username) {
        User user = userRepository.findByUsername(username);
        return cartItemRepository.findAllByUserByUserId(user);
    }

    //获取
    public int getCartSize(String username) {
        User user = userRepository.findByUsername(username);
        return cartItemRepository.countByUserByUserId(user);
    }

    public boolean addItem(CartItemDto cartItemDto, String issuedBy) {
        User user = userRepository.findByUsername(issuedBy);
        Food food = foodRepository.findById(cartItemDto.getFoodId()).get();
        if (food == null) {
            return false;
        }
        // 检查书是否已在购物车，如果在则只增加数量，否则添加新项
        CartItem cartItem = cartItemRepository.findByUserByUserIdAndFoodByFoodId(user, food);
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setFoodByFoodId(food);
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setUserByUserId(user);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        }
        cartItemRepository.save(cartItem);
        return true;
    }

    public boolean updateItem(CartItemDto cartItemDto, String issuedBy) {
        User user = userRepository.findByUsername(issuedBy);
        Food food = foodRepository.findById(cartItemDto.getFoodId()).get();
        if (food == null) {
            return false;
        }
        // 更新商品书的数量
        CartItem cartItem = cartItemRepository.findByUserByUserIdAndFoodByFoodId(user, food);
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItemRepository.save(cartItem);
        return true;
    }

    public boolean deleteItem(int cartItemId) {
        //检查书是否存在，存在则删除
        CartItem cartItem = cartItemRepository.findByCartItemId(cartItemId);
        if (cartItem == null) {
            return false;
        }
        cartItemRepository.delete(cartItem);
        return true;
    }
}
