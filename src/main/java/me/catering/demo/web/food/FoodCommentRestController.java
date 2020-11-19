package me.catering.demo.web.food;

import com.fasterxml.jackson.annotation.JsonView;
import me.catering.demo.domain.JacksonView;
import me.catering.demo.domain.entity.FoodComment;
import me.catering.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food/comment")
public class FoodCommentRestController {

    final private FoodService foodService;

    @Autowired
    public FoodCommentRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @JsonView(JacksonView.Comment_FoodView.class)
    @GetMapping("/{foodId}")
    public List<FoodComment> getCommentsByFoodId(@PathVariable int foodId) {
        return foodService.getCommentsByFoodId(foodId);
    }

}
