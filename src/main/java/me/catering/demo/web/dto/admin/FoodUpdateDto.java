package me.catering.demo.web.dto.admin;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class FoodUpdateDto {

    @NotNull
    @Min(1)
    private int foodId;

    @NotNull
    @Size(max = 255)
    private String foodTitle;

    @NotNull
    @Min(1)
    private int price;

    @NotNull
    @Min(1)
    private int stock;

    @NotNull
    @Size(max = 255)
    private String coverFilename;

    @NotNull
    @Size(min = 1)
    private String description;

    private String forSale;

    @NotNull
    @Min(1)
    private int shopId;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCoverFilename() {
        return this.coverFilename = coverFilename.length() > 0 ? coverFilename : "default.jpg";
    }

    public void setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForSale() {
        return forSale;
    }

    public void setForSale(String forSale) {
        this.forSale = forSale;
    }

    public boolean isForSale() {
        return forSale != null;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
