package me.catering.demo.service;

import me.catering.demo.domain.entity.CartItem;
import me.catering.demo.web.dto.CartItemDto;

import java.util.List;


public interface CartService {

    List<CartItem> getAllItems(String username);

    int getCartSize(String username);

    boolean addItem(CartItemDto cartItemDto, String issuedBy);

    boolean updateItem(CartItemDto cartItemDto, String issuedBy);

    boolean deleteItem(int cartItemId);
}
