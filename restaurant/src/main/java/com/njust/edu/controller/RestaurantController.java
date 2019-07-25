package com.njust.edu.controller;

import com.njust.edu.entity.Restaurant;
import com.njust.edu.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestaurantController {
    private static final Logger log= LoggerFactory.getLogger(RestaurantController.class);
    @Resource
    private RestaurantService restaurantService;

    @RequestMapping("/restaurant")
    public ModelAndView restaurantList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        List<Restaurant> restaurantList=restaurantService.restaurantList();


        return mv;
    }

    @RequestMapping("/addRestaurant")
    public String addRestaurant(int id,String name,String address,String description){
        Map<String,Object> map=new HashMap<>();
        String error;
        String message;
        Restaurant restaurant = new Restaurant(id,name,address,description);
        //以下try-catch部分代码写的极蠢
        try {
            map=restaurantService.addRestaurant(restaurant);
            error=map.get("error_code").toString();
            message=map.get("msg").toString();
            if(error == "101") {
                    log.error(message);
                    return error;
            }

        }catch (Exception e){
            e.printStackTrace();
            return "unknown error";
        }
        return message;

    }

    @RequestMapping("/deleteRestaurant")
    public String deleteRestaurant(int id){
        String message="fail";
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(id);
        try{
            restaurantService.deleteRestaurant(id);
            message="success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping("/updateRestaurant")
    public String updateRestaurant(int id,String name,String address,String description){
        String message="fail";
        Restaurant restaurant=new Restaurant(id,name,address,description);
        try{
            restaurantService.updateRestaurant(restaurant);
            message="success";
        }catch (Exception e){
            e.printStackTrace();

        }
        return message;
    }

}
