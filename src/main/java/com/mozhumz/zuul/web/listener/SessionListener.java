package com.mozhumz.zuul.web.listener;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author huyuanjia
 * @date 2019/5/5 15:06
 */
@Component
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //TODO session失效后 退出各个web应用
        String token= (String) se.getSession().getAttribute("token");
        System.out.println(token);
    }
}
