package com.njust.edu.entity;

/**
 * 菜单主键类 复合主键为food_id和restaurant_id
 */
public class MenuKey {
    private Integer foodId;

    private Integer restaurantId;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}