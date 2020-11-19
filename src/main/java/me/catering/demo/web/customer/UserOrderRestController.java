package me.catering.demo.web.customer;

import com.fasterxml.jackson.annotation.JsonView;
import me.catering.demo.domain.JacksonView;
import me.catering.demo.domain.entity.Order;
import me.catering.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/user/order")
public class UserOrderRestController {

    private final OrderService orderService;

    @Autowired
    public UserOrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @JsonView(JacksonView.Order_FoodView.class)
    public List<Order> getAllOrders(Principal principal) {
        return orderService.getOrderByUsername(principal.getName());
    }

    @PostMapping
    public boolean addOrderFromCart(@RequestParam String deliverTo, Principal principal) {
        if (deliverTo.length() == 0) {
            return false;
        }

        try {
            return orderService.execute(deliverTo, principal.getName());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
