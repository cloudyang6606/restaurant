package com.njust.edu.controller;

import com.alibaba.fastjson.JSON;
import com.njust.edu.entity.ReData;
import com.njust.edu.entity.Restaurant;
import com.njust.edu.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        mv.setViewName("restaurantIndex");
        List<Restaurant> restaurantList=restaurantService.restaurantList();
        mv.addObject(restaurantList);
        return mv;
    }

    @RequestMapping("/addRestaurant")
    @ResponseBody
    public String addRestaurant(int id,String name,String address,String description,int masterId){
        Map<String,Object> map=new HashMap<>();
        String error;
        ReData data=new ReData();
        Restaurant restaurant = new Restaurant(id,name,address,description,masterId);
        //以下try-catch部分代码写的极蠢
        try {
            map=restaurantService.addRestaurant(restaurant);
            error=map.get("error_code").toString();
            //message=map.get("msg").toString();

            if(error == "101") {//id重复
                    log.error("RestaurantID重复");
                    data.setMessage(error);
                    return JSON.toJSONString(data);
            }
            data.setSuccess(true);
            data.setMessage("0");

        }catch (Exception e){
            e.printStackTrace();
            data.setMessage("100");
        }
        return JSON.toJSONString(data);

    }

    @RequestMapping(value="/deleteRestaurant" ,produces ="text/html;charset=UTF-8")
    @ResponseBody
    public String deleteRestaurant(@RequestParam(value="restaurantId")int id){
        log.info("删除餐厅编号为"+id);

        ReData data=new ReData();

        /*Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(id);*/
        try{
            restaurantService.deleteRestaurant(id);
            //message="success";
            data.setSuccess(true);
            data.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();

        }
        return JSON.toJSONString(data);
    }

    @RequestMapping("/updateRestaurant")
    @ResponseBody
    public String updateRestaurant(@RequestParam(value="restaurantId")int id,String name,String address,String description,int masterId){

        Restaurant restaurant=new Restaurant(id,name,address,description,masterId);
        ReData data=new ReData();
        data.setMessage("fail");
        log.info("修改 restaurant"+id+"信息修改为"+name+" "+address+" "+description+" "+masterId);
        try{
            restaurantService.updateRestaurant(restaurant);
            data.setSuccess(true);
            data.setMessage("update success");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            data.setMessage("update fail");
        }
        System.out.println(JSON.toJSONString(data));
        return JSON.toJSONString(data);
    }

}
