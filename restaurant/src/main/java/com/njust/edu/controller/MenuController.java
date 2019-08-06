package com.njust.edu.controller;

import com.alibaba.fastjson.JSON;
import com.njust.edu.entity.Menu;
import com.njust.edu.entity.MenuKey;
import com.njust.edu.entity.ReData;
import com.njust.edu.entity.Restaurant;
import com.njust.edu.service.MenuService;
import com.njust.edu.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController extends BaseController{
    private static final Logger log= LoggerFactory.getLogger(MenuController.class);
    @Resource
    private MenuService menuService;
    @Resource
    private RestaurantService restaurantService;

    @RequestMapping("/menuMain")
    public ModelAndView menuMain(){
        ModelAndView mv=new ModelAndView();
        int masterId=-1;

        HttpSession session =getRequest().getSession();
        try{
            if(session.getAttribute("masterId") !=null){
                masterId= (int) session.getAttribute("masterId");
            }

            if(masterId == 0){

            }
            Restaurant restaurant=restaurantService.findByMaster(masterId);
            session.setAttribute("restaurantId",restaurant.getRestaurantId());
            if(restaurant ==null){
                mv.setViewName("error");

            }
            List<Menu> list=menuService.findFoodByRestaurant(restaurant.getRestaurantId());
            mv.addObject(list);
            mv.getModel().put("restaurantName",restaurant.getName());
            mv.setViewName("menu");
        }catch (Exception e){
            log.error(e.getMessage());
            mv.setViewName("error");
        }

        return mv;
    }

    @RequestMapping("/addFood")
    @ResponseBody
    public String addFood(int foodId,String name,double price,double discount,String description ,String type){
        HttpSession session=getRequest().getSession();
        int restaurantId= (int) session.getAttribute("restaurantId");

        Menu menu=new Menu();
        menu.setFoodId(foodId);
        menu.setRestaurantId(restaurantId);
        menu.setName(name);
        menu.setPrice(price);
        menu.setDiscount(discount);
        menu.setDescription(description);
        menu.setType(type);
        int rs=menuService.addFood(menu);
        ReData data=new ReData();
        data.setMessage("success");
        if(rs==-1) {
            data.setMessage("restaurant does not exist");
            return JSON.toJSONString(data);
        }
        else{
            if(rs==0) {
                data.setMessage("fail");
                return JSON.toJSONString(data);
            }

        }
        data.setSuccess(true);
        return JSON.toJSONString(data);
    }

    @RequestMapping("/updateFood")
    @ResponseBody
    public String updateFood(int foodId,int restaurantId,String name,double price,double discount,String description ,String type){

        int rs=menuService.updateFood(foodId,restaurantId,name, price, discount, description , type);
        ReData data=new ReData();
        data.setSuccess(true);
        return JSON.toJSONString(data);
    }
    @RequestMapping("/deleteFood")
    @ResponseBody
    public String deleteFood(int restaurantId,int foodId){
        MenuKey menuKey=new MenuKey();
        menuKey.setFoodId(foodId);
        menuKey.setRestaurantId(restaurantId);
        int rs=menuService.deleteFood(menuKey);
        ReData data=new ReData();
        if(rs > 0)  data.setSuccess(true);
        return JSON.toJSONString(data);
    }

}
