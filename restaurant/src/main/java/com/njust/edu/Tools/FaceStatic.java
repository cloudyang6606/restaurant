package com.njust.edu.Tools;

import com.baidu.aip.face.AipFace;

/**
 * 获取ACCESS_TOKEN,单例使用
 */
public class FaceStatic {
    public static final String APP_ID = "16752632";
    public static final String API_KEY = "9D3lGNrFGV3yizUAQXNW0nyP";
    public static final String SECRET_KEY = "qSsLGdIkFfCuhgvxKSi3OSQI7oQ0ASPE";
    //private volatile static FaceStatic instance;
    private static AipFace client;
    /*private FaceStatic() {
        // 初始化一个AipFace
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数


    }
    public static synchronized FaceStatic getInstance(){
        if(instance == null){
            synchronized (FaceStatic.class){
                if (instance ==null){
                    instance=new FaceStatic();
                }
            }
        }
        return instance;
    }*/
    public static AipFace getClient(){  // 双重加锁单例使用？我也不知道是不是成功实现了
        if(client==null){
            synchronized (AipFace.class) {
                if(client==null){
                    client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
                    client.setConnectionTimeoutInMillis(2000);
                    client.setSocketTimeoutInMillis(60000);
                }
            }
        }
        return client;
    }


}
