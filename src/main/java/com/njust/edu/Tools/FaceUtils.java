package com.njust.edu.Tools;

import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.HashMap;

public class FaceUtils {

    //private FaceStatic faceStatic =new FaceStatic();
    private static AipFace client=FaceStatic.getClient();



    /**
     * 人脸检测
     * @param image Base64的图片 类型为String
     * @param imageType
     */
    public static void detectFace(String image, String imageType) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "1");  //最多处理人脸的数目，默认值为1(仅检测图片中面积最大的那个人脸) 最大值10
        options.put("face_type", "LIVE");  // LIVE表示生活照,IDCARD表示身份证芯片照
        options.put("liveness_control", "NONE");//活体检测控制 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率) NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率) 默认NONE

        //String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        //String imageType = "BASE64";

        // 人脸检测
        JSONObject res =client.detect(image, imageType, options);
        /*
        res.face_list中的每一个face_token为人脸唯一标识
         */
        System.out.println(res.toString(2));
        if(res.getInt("error_code") == 0) System.out.println("success");

    }
    public static JSONObject searchFace(String image) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1");
        options.put("match_threshold", "80");  //匹配阈值（设置阈值后，score低于此阈值的用户信息将不会返回） 最大100 最小0 默认80,此阈值设置得越高，检索速度将会越快，推荐使用默认阈值80
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        //options.put("user_id", userId);      //指定userId可更快搜索
        options.put("max_user_num", "1");     //查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回50个。

        //String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupIdList = "1";

        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupIdList, options);
        System.out.println(res.toString(2));
        if(res.getString("error_msg")!="SUCCESS")
            return null;
        else {
            JSONArray userList = res.getJSONArray("user_list");
            JSONObject matchUser = userList.getJSONObject(0);
            return matchUser;
        }

    }
    public static JSONObject addUserFace(String image, String userId, String userName) { //人脸注册
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        System.out.println(userId+userName);
        options.put("user_info", userName);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "NONE");
        options.put("action_type", "REPLACE"); //操作方式 APPEND: 当user_id在库中已经存在时，对此user_id重复注册时，新注册的图片默认会追加到该user_id下,REPLACE : 当对此user_id重复注册时,则会用新图替换库中该user_id下所有图片,默认使用APPEND

        //String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupId = "group1";
        //String userId = "user1";

        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        System.out.println(res.toString(2));
        return res;

    }
    public static String updateFace(String userId,String image,String userName) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", userName);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "NONE");
        options.put("action_type", "REPLACE");

        //String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupId = "group1";


        // 人脸更新
        JSONObject res = client.updateUser(image, imageType, groupId, userId, options);
        System.out.println(res.toString(2));
        if(res.getString("error_msg") != "SUCCESS") return "fail"; else return "success";

    }


}
