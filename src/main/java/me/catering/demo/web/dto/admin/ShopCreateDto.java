package me.catering.demo.web.dto.admin;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShopCreateDto {
    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String city;

    @NotNull
    @Size(max = 255)
    private String district;

    @NotNull
    @Size(max = 255)
    private String landmark;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    private float starRate;

    @NotNull
    @Size(max = 255)
    private String foodTag;

    @NotNull
    @Size(max = 255)
    private String coverFilename;

    @NotNull
    @Size(max = 255)
    private String description;

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getStarRate() {
        return starRate;
    }

    public void setStarRate(float starRate) {
        this.starRate = starRate;
    }

    public String getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
    }

    public String getCoverFilename() {
        return coverFilename;
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

    public void setTitle(String title) {
        this.title = title;
    }
}
