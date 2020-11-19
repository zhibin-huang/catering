package me.catering.demo.web.admin;

import me.catering.demo.domain.entity.Food;
import me.catering.demo.service.FoodService;
import me.catering.demo.service.ShopService;
import me.catering.demo.web.dto.admin.FoodCreateDto;
import me.catering.demo.web.dto.admin.FoodUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/admin/foods")
public class AdminFoodsController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private ShopService shopService;
    @GetMapping()
    public String listAllFoodsByShopId(@RequestParam(value="shopId")int shopId, Model model) {
        Iterable<Food> foods = foodService.getAllFoodsByShopId(shopId);
        String shopTitle = shopService.getShopById(shopId).getTitle();
        model.addAttribute("foods", foods);
        model.addAttribute("shopTitle", shopTitle);
        return "admin/foods";
    }

    @PostMapping(path = "/add")
    public String addFood(@Valid FoodCreateDto foodCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/foods?shopId="+foodCreateDto.getShopId();
        }

        Food food = new Food();

        food.setFoodTitle(foodCreateDto.getFoodTitle());
        food.setPrice(foodCreateDto.getPrice());
        food.setStock(foodCreateDto.getStock());
        food.setCoverFilename(foodCreateDto.getCoverFilename());
        food.setDescription(foodCreateDto.getDescription());
        food.setForSale(foodCreateDto.isForSale());
        food.setShopByShopId(shopService.getShopById(foodCreateDto.getShopId()));

        foodService.addFood(food);

        return "redirect:/admin/foods?shopId="+foodCreateDto.getShopId();
    }

    @PostMapping(path = "/update")
    public String updateFood(@Valid FoodUpdateDto foodUpdateDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/foods?shopId="+foodUpdateDto.getShopId();
        }

        Food food = foodService.getFoodById(foodUpdateDto.getFoodId());

        food.setFoodTitle(foodUpdateDto.getFoodTitle());
        food.setPrice(foodUpdateDto.getPrice());
        food.setStock(foodUpdateDto.getStock());
        food.setCoverFilename(foodUpdateDto.getCoverFilename());
        food.setDescription(foodUpdateDto.getDescription());
        food.setForSale(foodUpdateDto.isForSale());

        foodService.updateFood(food);

        return "redirect:/admin/foods?shopId="+foodUpdateDto.getShopId();
    }

    @PostMapping(path = "/delete")
    public String deleteFood(@RequestParam int foodId) {
        Food food = foodService.getFoodById(foodId);
        int shopId = food.getShopByShopId().getShopId();
        foodService.deleteFood(foodId);
        return "redirect:/admin/foods?shopId="+shopId;
    }
}
