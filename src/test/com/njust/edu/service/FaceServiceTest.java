package com.njust.edu.service;


import com.baidu.aip.util.Base64Util;
import com.njust.edu.Tools.FaceUtils;
import com.njust.edu.Tools.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static org.junit.Assert.*;

public class FaceServiceTest {
    private ApplicationContext applicationContext;
    //private FaceUtils faceUtils;
    @Autowired
    private FaceService faceService;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        // 导入需要测试的
        faceService = applicationContext.getBean(FaceService.class);
    }
    @Test
    public void detectFace() throws IOException {
        String filePath="F:\\IDEAspace\\restaurant\\src\\main\\webapp\\WEB-INF\\image\\img02.jpg";
        byte[] imagebyte = FileUtil.readFileByBytes(filePath);
        String image= Base64Util.encode(imagebyte);
        FaceUtils.detectFace(image,"BASE64");


    }

}