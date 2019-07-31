package com.njust.edu.service;

import com.njust.edu.Tools.FaceUtils;
import com.njust.edu.dao.UserMapper;
import com.njust.edu.entity.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FaceService {
    @Resource
    UserMapper userMapper;



    public  void register(String userName, String userPwd, String image) {
        User user=new User();
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        user.setGroupId("1");
        userMapper.insertSelective(user);
        user=userMapper.selectByUserName(userName);  //如果userId是通过代码生成，可以直接addUserFace(image,userId,username)
        String userId=user.getUserId().toString();
        JSONObject res=FaceUtils.addUserFace(image,userId,userName);


    }

    public String login(String image){
        JSONObject rs=FaceUtils.searchFace(image);
        if(rs != null){
            double score=rs.getDouble("score");
            String userId=rs.getString("user_id");
            if(score > 80) {
                return userId;
            }

        }
        return "fail";

    }

    /**
     * 更新人脸库中的脸，成功返回"success" 失败返回"fail"
     * @param image
     * @param userName
     * @return
     */
    public String updateFace(String image,String userName){
        User user=new User();
        user=userMapper.selectByUserName(userName);
        String userId=user.getUserId().toString();
        String updateMsg=FaceUtils.updateFace(userId,image,userName);
        return updateMsg;

    }

    public String detectFace(String image) {
        FaceUtils.detectFace(image,"BASE64");
        return "success";
    }
}
