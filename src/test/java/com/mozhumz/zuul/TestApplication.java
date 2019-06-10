package com.mozhumz.zuul;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mozhumz.zuul.constant.CommonConstant;
import com.mozhumz.zuul.mapper.IRoleMapper;
import com.mozhumz.zuul.model.dto.SessionUser;
import com.mozhumz.zuul.model.entity.Role;
import com.mozhumz.zuul.utils.SessionUtil;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;

/**
 * @author huyuanjia
 * @date 2019/5/8 9:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private IRoleMapper roleMapper;

    @Test
    public void testUpdate(){
        SessionUser userDto=new SessionUser();
        userDto.setToken("ss");
        userDto.setUpdateDate(new Date());
//        Duration duration=Duration.ofMinutes(1);
        redisTemplate.opsForValue().set(CommonConstant.globalSessionUser ,
                SessionUtil.gson.toJson(userDto));

        String json= (String) redisTemplate.opsForValue().get(CommonConstant.globalSessionUser);
        System.out.println(json);
        SessionUser userDto2=  SessionUtil.gson.fromJson(json,SessionUser.class);
        System.out.println(userDto2);
//        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("id",1);
//        Role role=roleMapper.selectOne(queryWrapper);
//        UpdateWrapper updateWrapper=new UpdateWrapper();
//        updateWrapper.eq("id",1);
//        updateWrapper.set("createDate",new Date());
//        roleMapper.update(role,updateWrapper);
    }

}
