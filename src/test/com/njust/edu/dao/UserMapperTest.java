package com.njust.edu.dao;

import com.njust.edu.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserMapperTest {
    private ApplicationContext applicationContext;
    @Autowired
    private UserMapper mapper;

    @org.junit.Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        // 导入需要测试的
        mapper = applicationContext.getBean(UserMapper.class);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
    @Test
    public void insert() throws Exception{
        User user=new User();
        user.setUserId(2);
        user.setGroupId("1");
        user.setUserName("李四");
        user.setUserPwd("1111");
        int rs=mapper.insert(user);
        System.out.println(rs);
        assert (rs==1);
    }

    @Test
    public void delete() throws  Exception{
        int userId=2;
        int rs=mapper.deleteByPrimaryKey(userId);
        System.out.println(rs);
        assert (rs==1);
    }

    @Test
    public void selectByName() throws Exception{
        String userName="李四";
        User user=mapper.selectByUserName(userName);
        System.out.println(user.getUserId());
        assert (user.getUserId()==2);

    }
}