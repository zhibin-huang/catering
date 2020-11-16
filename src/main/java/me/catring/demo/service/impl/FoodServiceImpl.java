package me.catring.demo.service.impl;

import me.catring.demo.domain.dao.ShopRepository;
import me.catring.demo.domain.document.UserProfile;
import me.catring.demo.domain.entity.Food;
import me.catring.demo.domain.dao.FoodCommentRepository;
import me.catring.demo.domain.dao.FoodRepository;
import me.catring.demo.domain.dao.UserRepository;
import me.catring.demo.domain.entity.FoodComment;
import me.catring.demo.domain.entity.User;
import me.catring.demo.service.FoodService;
import me.catring.demo.web.dto.FoodCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;


@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    private final FoodCommentRepository foodCommentRepository;

    private final UserRepository userRepository;

    private final ShopRepository shopRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, FoodCommentRepository foodCommentRepository, UserRepository userRepository, ShopRepository shopRepository) {
        this.foodRepository = foodRepository;
        this.foodCommentRepository = foodCommentRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
    }

    // about foods
    public Food getFoodById(int food_id) {
        return foodRepository.findById(food_id).get();
    }

    public Page<Food> getAllFoodsByShopId(int shopId, int page, int pageSize) {
        return foodRepository.findAllByShopByShopId(shopRepository.findById(shopId).get(), PageRequest.of(page, pageSize, Sort.by("foodId")));
    }
    public Iterable<Food> getAllFoodsByShopId(int shopId){
        return foodRepository.findAllByShopByShopId(shopRepository.findById(shopId).get());
    }

    public Iterable<Food> getSellingFoodsByShopId(int shopId) {
        return foodRepository.findAllByShopByShopId(shopRepository.findById(shopId).get());
    }

    public Page<Food> getSellingFoodsByShopId(int shopId, int page, int pageSize) {
        return foodRepository.findAllByShopByShopId(shopRepository.findById(shopId).get(), PageRequest.of(page, pageSize, Sort.by("foodId")));
    }

    public Page<Food> getSearchResult(String keyword, int page, int pageSize) {
        return foodRepository.findByFoodTitleContaining(keyword, PageRequest.of(page, pageSize, Sort.by("foodId")));
    }

    public void addFood(Food food) {
        foodRepository.save(food);
    }

    public void updateFood(Food food) {
        foodRepository.save(food);
    }

    public void deleteFood(int food_id) {
        foodRepository.delete(getFoodById(food_id));
    }

    public String getAvatarFilenameByFoodId(int food_id) {
        return foodRepository.findByFoodId(food_id).getCoverFilename();
    }

    public void updateAvatarFilename(String avatarFilename, int food_id) {
        Food food = foodRepository.findByFoodId(food_id);
        food.setCoverFilename(avatarFilename);
        foodRepository.save(food);
    }
    // 获取某本书的所有评价
    public List<FoodComment> getCommentsByFoodId(int foodId) {
        Food food = foodRepository.findById(foodId).get();
        return foodCommentRepository.findAllByFoodByFoodId(food, Sort.by("foodCommentId"));
    }

    //获取某个用户的所有评价
    public List<FoodComment> getCommentsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return foodCommentRepository.findAllByUserByUserId(user);
    }

    //获取某个用户对某本书的评价
    public FoodComment getCommentByFoodIdAndUsername(int foodId, String username) {
        User user = userRepository.findByUsername(username);
        Food food = foodRepository.findById(foodId).get();
        return foodCommentRepository.findByUserByUserIdAndFoodByFoodId(user, food);
    }

    //更新某条评价
    public boolean updateFoodComment(FoodCommentDto foodCommentDto, String username) {
        User user = userRepository.findByUsername(username);
        Food food = foodRepository.findById(foodCommentDto.getFoodId()).get();
        FoodComment foodComment = foodCommentRepository.findByUserByUserIdAndFoodByFoodId(user, food);

        if (foodComment == null) {
            foodComment = new FoodComment();
            foodComment.setFoodByFoodId(food);
            foodComment.setTitle(foodCommentDto.getTitle());
            foodComment.setUserByUserId(user);
            foodComment.setContent(foodCommentDto.getContent());
            foodComment.setCommentTime(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        } else {
            foodComment.setTitle(foodCommentDto.getTitle());
            foodComment.setContent(foodCommentDto.getContent());
        }

        foodCommentRepository.save(foodComment);

        return true;
    }
    //删除某条评论
    public boolean deleteFoodComment(String username, int foodId) {
        User user = userRepository.findByUsername(username);
        Food food = foodRepository.findById(foodId).get();
        FoodComment foodComment = foodCommentRepository.findByUserByUserIdAndFoodByFoodId(user, food);
        if (foodComment == null) {
            return false;
        }
        foodCommentRepository.delete(foodComment);
        return true;
    }


}
