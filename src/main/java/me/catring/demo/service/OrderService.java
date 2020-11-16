package me.catring.demo.service;

import me.catring.demo.domain.entity.Order;


import java.util.List;


public interface OrderService {

    List<Order> getOrderByUsername(String username);

    boolean execute(String deliverTo, String username);
}
