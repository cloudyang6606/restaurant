package com.njust.edu.dao;

import com.njust.edu.entity.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class RestaurantMapperTest {
    private ApplicationContext applicationContext;
    @Resource
    private RestaurantMapper mapper;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        // 导入需要测试的
        mapper = applicationContext.getBean(RestaurantMapper.class);
    }

    @Test
    public void insert(){
        Restaurant restaurant=new Restaurant();
        restaurant.setRestaurantId(2);
        restaurant.setName("理工大炒饭");
        restaurant.setAddress("南京理工大学");
        restaurant.setDescription("好吃的炒饭");
        int a=mapper.insert(restaurant);
        System.out.println(a);
        assert(a==1);
    }
    @Test
    public void update(){
        Restaurant restaurant=new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setName("理工大黄焖鸡");
        restaurant.setAddress("南京理工大学");
        restaurant.setDescription("黄焖鸡米饭");
        int a=mapper.updateByPrimaryKeySelective(restaurant);
        System.out.println(a);
        assert(a==1);
    }

    @Test
    public void delete(){
        int id=2;
        int a=mapper.deleteByPrimaryKey(id);
        assert (a==1);
    }

    @Test
    public void selectAll(){
        List<Restaurant> list=mapper.selectAll();
        for(Restaurant restaurant:list){
            System.out.println(restaurant.getName()+" "+restaurant.getAddress());
        }
        assert(list.size()>0);
    }

}