package com.njust.edu.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.util.Base64Util;
import com.njust.edu.entity.ReData;
import com.njust.edu.service.FaceService;
import org.json.JSONObject;
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
@RequestMapping("/user")
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
    @RequestMapping("/registerFace")
    public ModelAndView registerFace(String userName,String userPwd,String image){
        ModelAndView modelAndView=new ModelAndView();
        log.info("注册用户"+userName);


        String msg;
        try{
            faceService.register(userName,userPwd,image);
            msg="success";
        }catch(Exception e){
            msg="注册失败，请重新尝试";

        }
        modelAndView.setViewName("loginFace");
        modelAndView.addObject("message",msg);
        return modelAndView;
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/loginFace")
    @ResponseBody
    public String loginFace(HttpServletRequest request, HttpServletResponse response){
        log.info("用户登录");
        String userId;
        String image=request.getParameter("image");
        userId=faceService.login(image);
        ReData data=new ReData();
        if(userId.equals("fail")){
            data.setMessage("登录失败，请尝试换一种方式登录");
            return JSON.toJSONString(data);
        }
        return "loginSuccess";
    }

    /**
     * 更改人脸库中的脸
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateFace")
    public String updateFace(HttpServletRequest request, HttpServletResponse response){
        ReData data=new ReData();
        String image=request.getParameter("image");
        String userName=request.getParameter("userId");
        data.setMessage(faceService.updateFace(image,userName));
        return JSON.toJSONString(data);

    }

    /**
     * 检测图片是否包含人脸
     * @param image
     * @return
     */
    @RequestMapping("/detectFace")
    @ResponseBody
    public String detectFace(String image){

        ReData data=new ReData();
        data.setMessage(faceService.detectFace(image));

        return JSON.toJSONString(data);
    }


}
