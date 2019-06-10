package com.mozhumz.zuul.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.hyj.util.common.CommonUtil;
import com.hyj.util.param.CheckParamsUtil;
import com.mozhumz.zuul.config.GsonConfig;
import com.mozhumz.zuul.constant.CommonConstant;
import com.mozhumz.zuul.model.dto.SessionUser;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huyuanjia
 * @date 2019/5/6 20:37
 */
@Component
@Slf4j
public class SessionUtil {
    public static RedisTemplate redisTemplate;
    public static Gson gson = getGson();


    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
//        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
//            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//                    throws JsonParseException {
//                return new Date(json.getAsJsonPrimitive().getAsLong());
//            }
//        });
        builder.registerTypeAdapter(Date.class, new GsonConfig()).setDateFormat("yyyy-MM-dd HH:mm:ss");
        return builder.create();
    }

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate){
        SessionUtil.redisTemplate=redisTemplate;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }

    /**
     * 获取登录用户
     * @return
     */
    public static SessionUser getLoginUser(){
        String token= (String) getSession().getAttribute(CommonConstant.token);
        if(CheckParamsUtil.check(token)){
        }
        String json= (String) redisTemplate.opsForValue().get(CommonConstant.globalSessionUser);
        System.out.println(json);
        SessionUser map= gson.fromJson(json,new TypeToken<SessionUser>(){}.getType());
        System.out.println(map);
        Map userDto= JSON.parseObject(json, Map.class);
        System.out.println(userDto);
        return new SessionUser();
    }

    public static void setSessionUser(Long sessionSeconds,SessionUser userDto){
//        Duration duration = Duration.ofSeconds(sessionSeconds);
//        userDto=new SessionUser();
////        userDto.setId(1L);
////        userDto.setUsername("hyj");
        userDto.setCreateDate(null);
        userDto.setUpdateDate(null);
        redisTemplate.opsForValue().set(CommonConstant.globalSessionUser ,
                gson.toJson(userDto));
        getLoginUser();
    }

}
