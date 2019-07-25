package com.njust.edu.service;

import com.njust.edu.dao.MenuMapper;
import com.njust.edu.dao.RestaurantMapper;
import com.njust.edu.entity.Menu;
import com.njust.edu.entity.MenuKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RestaurantMapper restaurantMapper;

    public Menu findFoodByKey(MenuKey menukey){
        return menuMapper.selectByPrimaryKey(menukey);
    }

    public List<Menu> findFoodByRestaurant(int restaurantId){
        List<Menu> list=menuMapper.selectByRestaurant(restaurantId);
        return list;
    }

    public int addFood(Menu menu){
        if(restaurantMapper.selectByPrimaryKey(menu.getRestaurantId()) ==null){
            return -1;
        }

        return menuMapper.insert(menu);
    }

    public int deleteFood(MenuKey menuKey){
        return menuMapper.deleteByPrimaryKey(menuKey);
    }

    public int updateFood(int foodId,int restaurantId,String name,double price,double discount,String description,String type){
        Menu menu=new Menu();
        menu.setFoodId(foodId);
        menu.setRestaurantId(restaurantId);
        menu.setName(name);
        menu.setPrice(price);
        menu.setDiscount(discount);
        menu.setDescription(description);
        menu.setType(type);
        return menuMapper.updateByPrimaryKeySelective(menu);

    }



}
