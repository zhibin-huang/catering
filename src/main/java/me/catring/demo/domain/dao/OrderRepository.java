package me.catring.demo.domain.dao;

import me.catring.demo.domain.entity.Order;
import me.catring.demo.domain.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByUserByUserId(User user, Sort sort);

    @Query(value = "SELECT sum(total_price) FROM `Order`", nativeQuery = true)
    int getSumOfTotalPrice();
}
