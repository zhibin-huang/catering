package me.catering.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import me.catering.demo.domain.JacksonView;

import javax.persistence.*;

@Entity
@Table(name = "Food")
public class Food {

    @JsonView(JacksonView.FoodView.class)
    private int foodId;

    @JsonView(JacksonView.FoodView.class)
    private String foodTitle;

    private int price;

    private int stock;

    @JsonView(JacksonView.FoodView.class)
    private String coverFilename;

    private String description;

    private boolean forSale;

    private Shop shopByShopId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id", nullable = false)
    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Basic
    @Column(name = "food_title", nullable = false, length = 255)
    public String getFoodTitle() {
        return foodTitle;
    }
    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "cover_filename", nullable = false, length = 200)
    public String getCoverFilename() {
        return coverFilename;
    }
    public void setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 1000)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "for_sale", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isForSale() {
        return forSale;
    }
    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "shop_id", nullable = false)
    public Shop getShopByShopId() {
        return shopByShopId;
    }
    public void setShopByShopId(Shop shopByShopId) {
        this.shopByShopId = shopByShopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (foodId != food.foodId) return false;
        if (price != food.price) return false;
        if (stock != food.stock) return false;
        if (forSale != food.forSale) return false;
        if (foodTitle != null ? !foodTitle.equals(food.foodTitle) : food.foodTitle != null) return false;
        if (coverFilename != null ? !coverFilename.equals(food.coverFilename) : food.coverFilename != null)
            return false;
        if (description != null ? !description.equals(food.description) : food.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = foodId;
        result = 31 * result + (foodTitle != null ? foodTitle.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + stock;
        result = 31 * result + (coverFilename != null ? coverFilename.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (forSale ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Food: " + getFoodId() + ", " + getFoodTitle() ;
    }
}
