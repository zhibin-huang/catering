package me.catring.demo.web.customer;

import com.fasterxml.jackson.annotation.JsonView;
import me.catring.demo.domain.JacksonView;
import me.catring.demo.domain.entity.FoodComment;
import me.catring.demo.service.FoodService;
import me.catring.demo.web.dto.FoodCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/user/comment")
public class UserCommentRestController {
    final private FoodService foodService;

    @Autowired
    public UserCommentRestController(FoodService foodService) {
        this.foodService = foodService;
    }

   // 获取用户的所有评价
    @JsonView(JacksonView.Comment_FoodView.class)
    @GetMapping()
    public List<FoodComment> getCommentByUserId(Principal principal) {
        return foodService.getCommentsByUsername(principal.getName());
    }
    //获取用户对某本书的评价
    @JsonView(JacksonView.Comment_FoodView.class)
    @GetMapping("/{foodId}")
    public FoodComment getCommentByUserIdAndFoodId(@PathVariable int foodId, Principal principal) {
        return foodService.getCommentByFoodIdAndUsername(foodId, principal.getName());
    }
    //用户提交评价
    @PutMapping()
    public boolean updateComment(@Valid FoodCommentDto comment, BindingResult result,Principal principal) {
        return !result.hasErrors() && foodService.updateFoodComment(comment,principal.getName());
    }
    //用户删除评价
    @DeleteMapping("/{foodId}")
    boolean deleteComment(@PathVariable int foodId, Principal principal) {
        return foodService.deleteFoodComment(principal.getName(), foodId);
    }
}
