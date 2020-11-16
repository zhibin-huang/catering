package me.catring.demo.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class CartItemDto {
    @NotNull
    @Min(1)
    private int foodId;

    @NotNull
    @Min(1)
    private int quantity;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
