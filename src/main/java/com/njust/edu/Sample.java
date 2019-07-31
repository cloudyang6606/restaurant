package com.njust.edu;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import com.baidu.aip.util.ImageUtil;
import com.njust.edu.Tools.FaceUtils;
import com.njust.edu.Tools.FileUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "16752632";
    public static final String API_KEY = "9D3lGNrFGV3yizUAQXNW0nyP";
    public static final String SECRET_KEY = "qSsLGdIkFfCuhgvxKSi3OSQI7oQ0ASPE";

    public static void main(String[] args) {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        FaceUtils faceUtils=new FaceUtils();

        String filePath="F:\\IDEAspace\\restaurant\\src\\main\\webapp\\WEB-INF\\image\\img01.jpg";



        try {
            byte[] imagebyte = FileUtil.readFileByBytes(filePath);
            String image=Base64Util.encode(imagebyte);
            faceUtils.detectFace(image,"BASE64");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        //String imageType = "BASE64";


    }
}