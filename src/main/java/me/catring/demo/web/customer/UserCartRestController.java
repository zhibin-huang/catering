package me.catring.demo.web.customer;

import me.catring.demo.domain.entity.CartItem;
import me.catring.demo.service.CartService;
import me.catring.demo.web.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/user/cart")
public class UserCartRestController {
    private final CartService cartService;

    @Autowired
    public UserCartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    //以下RESTful API均需有效的用户登录信息principal
    //获取购物车所有的项目
    @GetMapping
    public List<CartItem> getAllItems(Principal principal) {
        return cartService.getAllItems(principal.getName());
    }

    //获取购物车的大小
    @GetMapping("/size")
    public int getCartSize(Principal principal) {
        return cartService.getCartSize(principal.getName());
    }

    //向购物车添加商品，商品item是经过数据验证的DTO对象，验证结果由result提供，下面相关的接口也类似
    @PostMapping
    public boolean addItem(@Valid CartItemDto item, BindingResult result, Principal principal) {
        return !result.hasErrors() && cartService.addItem(item, principal.getName());
    }

    //更新购物车中的商品项
    @PutMapping
    public boolean updateItem(@Valid CartItemDto item, BindingResult result, Principal principal) {
        return !result.hasErrors() && cartService.updateItem(item, principal.getName());
    }

    //删除购物车中的商品项
    @DeleteMapping("/{cartItemId}")
    public boolean deleteItem(@PathVariable int cartItemId) {
        return cartService.deleteItem(cartItemId);
    }
}
