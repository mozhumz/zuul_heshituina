package com.mozhumz.zuul;

import com.mozhumz.zuul.constant.CommonConstant;
import com.mozhumz.zuul.model.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author huyuanjia
 * @date 2019/5/8 9:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        UserDto userDto=new UserDto();
        userDto.setToken("ss");
        Duration duration=Duration.ofMinutes(1);
        redisTemplate.opsForValue().set(CommonConstant.sessionUser+"hyj",userDto,duration);

        System.out.println(redisTemplate.opsForValue().get(CommonConstant.sessionUser+"hyj"));
    }

}
