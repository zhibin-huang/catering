package me.catering.demo.service;

import me.catering.demo.domain.entity.Food;
import me.catering.demo.domain.entity.FoodComment;
import me.catering.demo.web.dto.FoodCommentDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface FoodService {

    Food getFoodById(int food_id);

    Iterable<Food> getAllFoodsByShopId(int shopId);

    Iterable<Food> getSellingFoodsByShopId(int shopId);

    Page<Food> getSellingFoodsByShopId(int shopId, int page, int pageSize);

    Page<Food> getSearchResult(String keyword, int page, int pageSize);

    void addFood(Food food);

    void updateFood(Food food);

    void deleteFood(int food_id);

    void updateAvatarFilename(String avatarFilename, int food_id);

    String getAvatarFilenameByFoodId(int food_id);

    // about food comments
    List<FoodComment> getCommentsByFoodId(int foodId);

    List<FoodComment> getCommentsByUsername(String username);

    FoodComment getCommentByFoodIdAndUsername(int foodId, String username);

    boolean updateFoodComment(FoodCommentDto foodCommentDto, String username);

    boolean deleteFoodComment(String username, int foodId);

}

