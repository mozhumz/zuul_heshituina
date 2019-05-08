package com.mozhumz.zuul.web.controller;

import com.hyj.util.param.CheckParamsUtil;
import com.mozhumz.zuul.constant.CommonConstant;
import com.mozhumz.zuul.model.dto.CheckTokenDto;
import com.mozhumz.zuul.model.dto.UserDto;
import com.mozhumz.zuul.model.entity.User;
import com.mozhumz.zuul.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.lshaci.framework.web.model.JsonResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;

/**
 * @author huyuanjia
 * @date 2019/4/2 16:55
 */
@RestController
@Api(value = "登录相关接口", description = "登录相关接口")
@RequestMapping("/api/login")
public class LoginController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private IUserService userService;

    @Value("${login.url}")
    private String loginUrl;

    @Value("${session-redis.timeout}")
    private Long sessionSeconds;



    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResponse<UserDto> login(@RequestBody User user) {
        UserDto userDto=userService.login(user);
        //登录成功 设置session
        HttpSession session=request.getSession();
        session.setAttribute(CommonConstant.token,userDto.getToken());
        Duration duration= Duration.ofSeconds(sessionSeconds);
        redisTemplate.opsForValue().set(CommonConstant.sessionUser+userDto.getToken(),userDto,duration);

        return JsonResponse.success(userDto);
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public JsonResponse<String> logOut() {
        request.getSession().invalidate();
        return JsonResponse.success(null);
    }

    @ApiOperation(value = "登录检查-获取token 进行重定向")
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public void checkLogin(String webUrl) throws IOException {
        String token= (String) request.getSession().getAttribute(CommonConstant.token);
        if(CheckParamsUtil.check(token)){
            //重定向到应用
            response.sendRedirect(webUrl+"?"+CommonConstant.token+"="+token);
        }else {
            //重定向到登录页
            response.sendRedirect(loginUrl);
        }
    }

    @ApiOperation(value = "token校验")
    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    public void checkToken(CheckTokenDto checkTokenDto) throws IOException {
        if(userService.checkToken(checkTokenDto)){
            //重定向到应用
            response.sendRedirect(checkTokenDto.getWebUrl());
        }else {
            //重定向到登录页
            response.sendRedirect(loginUrl);
        }

    }


}
