package me.catring.demo.domain.dao;

import me.catring.demo.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    @Query(value = "SELECT sum(quantity) FROM OrderItem", nativeQuery = true)
    int getSumOfQuantity();

    @Query(value = "SELECT sum(quantity * OrderItem.original_unit_price) FROM OrderItem", nativeQuery = true)
    int getSumOfQuantityTimesOriginalUnitPrice();
}
