package com.project.search.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ShopCreateReq {

    @NotBlank(message = "Store name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    private Integer pricePerMan;

    @NotNull(message = "Latitude cannot be null")
    private BigDecimal latitude;

    @NotNull(message = "Longitude cannot be null")
    private BigDecimal longitude;

    @NotNull(message = "Category Id cannot be null")
    private Integer categoryId;

    private String tags;

    @NotBlank(message = "Start time of operation cannot be null")
    private String startTime;

    @NotBlank(message = "End time of operation cannot be null")
    private String endTime;

    @NotBlank(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Seller Id cannot be null")
    private Integer sellerId;

    @NotBlank(message = "Icon url cannot be null")
    private String iconUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPricePerMan() {
        return pricePerMan;
    }

    public void setPricePerMan(Integer pricePerMan) {
        this.pricePerMan = pricePerMan;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
