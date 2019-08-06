package com.njust.edu.controller;

import com.alibaba.fastjson.JSON;
import com.njust.edu.entity.Master;
import com.njust.edu.entity.ReData;

import com.njust.edu.service.MasterService;
import com.njust.edu.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/master")
public class MasterController extends BaseController{
    private static final Logger log= LoggerFactory.getLogger(MasterController.class);

    @Resource
    private MasterService masterService;


    @RequestMapping("/register")
    @ResponseBody
    public String register( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置utf-8
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ReData data=new ReData();

        String masterName = request.getParameter("masterName");
        String masterPwd = request.getParameter("masterPwd");
        Master master=new Master();
        master.setMasterName(masterName);
        master.setMasterPwd(masterPwd);
        int rs=masterService.addMaster(master);  //注册用户信息

        if(rs>0){
            data.setSuccess(true);
            data.setMessage("注册成功");

        }else{
            data.setSuccess(false);
            data.setMessage("注册失败，请重新检查信息");
        }

        return JSON.toJSONString(data);


    }

    @RequestMapping("/masterLogin")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String masterName = request.getParameter("masterName");
        String masterPwd = request.getParameter("masterPwd");
        log.info("店主"+masterName+"登录");

        Master master=masterService.findByName(masterName);
        if(master == null){
            String script="<script>alert('用户名不存在');location.href='login.html'</script>";
            response.getWriter().println(script);

        }else{
            if(master.getMasterPwd().equals(masterPwd) ){
                HttpSession session=getRequest().getSession();
                session.setAttribute("masterName",masterName);
                session.setAttribute("masterId",master.getMasterId());
                //Restaurant restaurant=restaurantService.findByMaster(master.getMasterId());
                //session.setAttribute("restaurantName",restaurant.getName());
                String script="<script>location.href='../menuMain'</script>";
                response.getWriter().println(script);
            }else{
                String script="<script>alert('用户名或密码错误');location.href='login.html'</script>";
                response.getWriter().println(script);

            }
        }
    }

    @RequestMapping("/logout")
    public void logout(){
        HttpSession session=getRequest().getSession();
        session.removeAttribute("masterName");
        session.removeAttribute("masterId");
        session.removeAttribute("restaurantId");
        session.invalidate();
        String script="<script>location.href='./login.html'</script>";

    }



}
