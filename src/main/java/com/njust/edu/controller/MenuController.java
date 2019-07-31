package com.njust.edu.controller;

import com.njust.edu.entity.Menu;
import com.njust.edu.entity.MenuKey;
import com.njust.edu.entity.Restaurant;
import com.njust.edu.service.MenuService;
import com.njust.edu.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MenuController {
    @Resource
    private MenuService menuService;
    @Resource
    private RestaurantService restaurantService;

    @RequestMapping("/menuMain")
    public ModelAndView menuMain(){
        ModelAndView mv=new ModelAndView();
        int masterId=1;

        Restaurant restaurant=restaurantService.findByMaster(masterId);
        List<Menu> list=menuService.findFoodByRestaurant(restaurant.getRestaurantId());
        mv.addObject(list);
        mv.getModel().put("restaurantName",restaurant.getName());
        mv.setViewName("menu");

        return mv;
    }

    @RequestMapping("/addFood")
    @ResponseBody
    public String addFood(int foodId,int restaurantId,String name,double price,double discount,String description ,String type){
        Menu menu=new Menu();
        menu.setFoodId(foodId);
        menu.setRestaurantId(restaurantId);
        menu.setName(name);
        menu.setPrice(price);
        menu.setDiscount(discount);
        menu.setDescription(description);
        menu.setType(type);
        int rs=menuService.addFood(menu);
        String message="success";
        if(rs==-1) message="restaurant does not exist";
        else{
            if(rs==0) message="fail";
        }
        return message;
    }
    @RequestMapping
    @ResponseBody
    public String updateFood(int foodId,int restaurantId,String name,double price,double discount,String description ,String type){

        int rs=menuService.updateFood(foodId,restaurantId,name, price, discount, description , type);
        return "success";
    }
    @RequestMapping("/deleteFood")
    @ResponseBody
    public String deleteFood(int restaurantId,int foodId){
        MenuKey menuKey=new MenuKey();
        menuKey.setFoodId(foodId);
        menuKey.setRestaurantId(restaurantId);
        int rs=menuService.deleteFood(menuKey);
        return "success";
    }

}
