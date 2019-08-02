package com.mozhumz.zuul.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author huyuanjia
 * @date 2019/5/5 15:28
 */
@Slf4j
public class HttpUtil {

    /**
     * 根据sessionId退出web应用
     * @param outUrl
     * @param sessionId
     */
    public static void logOutWeb(String outUrl,String sessionId){
        try {
            URL url=new URL(outUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.addRequestProperty("Cookie","JSESSIONID="+sessionId);
            conn.connect();
            conn.getInputStream();
            conn.disconnect();
        } catch (Exception e) {
            log.info("logOutWeb-err:"+e);
        }
    }
}
