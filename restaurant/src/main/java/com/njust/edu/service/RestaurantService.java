package com.njust.edu.service;

import com.njust.edu.dao.RestaurantMapper;
import com.njust.edu.entity.Restaurant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {
    @Resource
    private RestaurantMapper restaurantMapper;

    public int addRestaurantSelect(Restaurant restaurant){
        return restaurantMapper.insertSelective(restaurant);

    }

    public Restaurant findRestaurant(int id) {
         return restaurantMapper.selectByPrimaryKey(id);
    }
    public Restaurant findByMaster(int masterId){
        return restaurantMapper.selectByMaster(masterId);
    }

    public Map<String,Object> addRestaurant(Restaurant restaurant){
        Map<String,Object> map=new HashMap<>();
        if(restaurantMapper.selectByPrimaryKey(restaurant.getRestaurantId()) ==null){
            map.put("error_code",101);
            map.put("msg","餐厅id已存在，请重新输入");
        }else{
            restaurantMapper.insert(restaurant);
            map.put("error_code",0);
            map.put("msg","餐厅添加成功");
        }
        return map;

    }
    public int deleteRestaurant(int id){
        return restaurantMapper.deleteByPrimaryKey(id);

    }
    public int updateRestaurant(Restaurant restaurant){
        return restaurantMapper.updateByPrimaryKeySelective(restaurant);
    }

    public List<Restaurant> restaurantList(){
        List<Restaurant> list=restaurantMapper.selectAll();
        return list;
    }



}
