package me.catring.demo.domain.dao;

import me.catring.demo.domain.entity.Food;
import me.catring.demo.domain.entity.FoodComment;
import me.catring.demo.domain.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FoodCommentRepository extends CrudRepository<FoodComment, Integer> {
    List<FoodComment> findAllByFoodByFoodId(Food foodByFoodId, Sort sort);

    List<FoodComment> findAllByUserByUserId(User user);

    FoodComment findByUserByUserIdAndFoodByFoodId(User user, Food food);
}
