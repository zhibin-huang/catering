package me.catring.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import me.catring.demo.domain.JacksonView;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "Shop")
public class Shop {

    @JsonView(JacksonView.ShopView.class)
    private int shopId;

    @JsonView(JacksonView.ShopView.class)
    private String title;

    @JsonView(JacksonView.ShopView.class)
    private String city;

    @JsonView(JacksonView.ShopView.class)
    private String district;

    @JsonView(JacksonView.ShopView.class)
    private String landmark;

    @JsonView(JacksonView.ShopView.class)
    private String address;

    @JsonView(JacksonView.ShopView.class)
    private float starRate;

    @JsonView(JacksonView.ShopView.class)
    private String coverFilename;

    private String description;

    @JsonView(JacksonView.ShopView.class)
    private String foodTag;

    @JsonView(JacksonView.OrderView.class)
    private Collection<Food> foodsByShopId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id", nullable = false)
    public int getShopId() {
        return shopId;
    }
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }


    @Basic
    @Column(name = "shop_title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }
    public void setTitle(String shopTitle) {
        this.title = shopTitle;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 255)
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Basic
    @Column(name = "district", nullable = false, length = 255)
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "landmark", nullable = false, length = 255)
    public String getLandmark() {
        return landmark;
    }
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Basic
    @Column(name = "location_detail", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }
    public void setAddress(String locationDetail) {
        this.address = locationDetail;
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
    @Column(name = "star_rate", nullable = false)
    public float getStarRate() {
        return starRate;
    }
    public void setStarRate(float starRate) {
        this.starRate = starRate;
    }

    @Basic
    @Column(name = "food_tag", nullable = false)
    public String getFoodTag() {
        return foodTag;
    }
    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (shopId != shop.shopId) return false;
        if (starRate != shop.starRate) return false;
        if (title != null ? !title.equals(shop.title) : shop.title != null) return false;
        if (city != null ? !city.equals(shop.city) : shop.city != null) return false;
        if (district != null ? !district.equals(shop.district) : shop.district != null) return false;
        if (landmark != null ? !landmark.equals(shop.landmark) : shop.landmark != null) return false;
        if (address != null ? !address.equals(shop.address) : shop.address != null) return false;
        if (coverFilename != null ? !coverFilename.equals(shop.coverFilename) : shop.coverFilename != null)
            return false;
        if (description != null ? !description.equals(shop.description) : shop.description != null) return false;
        if (foodTag != null ? !foodTag.equals(shop.foodTag) : shop.foodTag != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = shopId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (coverFilename != null ? coverFilename.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "shop: " + getShopId() + ", " + getTitle();
    }
}
