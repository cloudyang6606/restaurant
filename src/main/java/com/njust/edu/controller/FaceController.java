package com.njust.edu.controller;

import com.baidu.aip.util.Base64Util;
import com.njust.edu.service.FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class FaceController {
    private static final Logger log= LoggerFactory.getLogger(FaceController.class);
    @Resource
    private FaceService faceService;


    /**
     * 注册
     * @param userName
     * @param userPwd 密码
     * @param image 用户注册用图片
     * @return
     */
    @RequestMapping("/RegisterFace")
    public ModelAndView RegisterFace(String userName,String userPwd,String image){
        ModelAndView modelAndView=new ModelAndView();


        String msg;
        try{
            faceService.register(userName,userPwd,image);
            msg="success";
        }catch(Exception e){
            msg="注册失败，请重新尝试";

        }
        modelAndView.setViewName("login");
        modelAndView.addObject("message",msg);
        return modelAndView;
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/LoginFace")
    @ResponseBody
    public String LoginFace(HttpServletRequest request, HttpServletResponse response){

        String userId;
        String image=request.getParameter("image");
        userId=faceService.login(image);
        if(userId.equals("fail")){
            return "";
        }
        return userId;
    }

    /**
     * 更改人脸库中的脸
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/UpdateFace")
    public String UpdateFace(HttpServletRequest request, HttpServletResponse response){
        String image=request.getParameter("image");
        String userName=request.getParameter("userId");
        String msg=faceService.updateFace(image,userName);
        return msg;

    }

    /**
     * 检测图片是否包含人脸
     * @param image
     * @return
     */
    @RequestMapping("/DetectFace")
    public String DetectFace(String image){
        String message="fail";
        message=faceService.detectFace(image);


        return message;
    }


}
