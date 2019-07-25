package com.njust.edu.entity;

public class Restaurant {
    private Integer restaurantId;

    private String name;

    private String address;

    private String description;

    public Restaurant(){}

    public Restaurant(Integer restaurantId, String name, String address, String description) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}