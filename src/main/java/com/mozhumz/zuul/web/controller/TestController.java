package com.mozhumz.zuul.web.controller;

import com.hyj.util.web.JsonResponse;
import com.mozhumz.zuul.mapper.ITokenMapper;
import com.mozhumz.zuul.utils.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huyuanjia
 * @date 2019/4/2 16:55
 */
@RestController
@Api(value = "测试相关接口", description = "测试相关接口")
@RequestMapping("/api/demo")
public class TestController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    @Resource
    ITokenMapper tokenMapper;




    @ApiOperation(value = "添加")
    @RequestMapping(value = "/testAdd", method = RequestMethod.GET)
    public Object testAdd() {
//        Token token=new Token();
//        token.setToken("2");
//        token.setCreateDate(null);
//
//        Token token2=new Token();
//        Wrapper wrapper=new UpdateWrapper(token2);
//        tokenMapper.update(token,wrapper);
//        Duration duration=Duration.ofMinutes(1);
        try {
            response.sendRedirect("http://127.0.0.1:8080/hstn/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return JsonResponse.success("jj");
    }

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/testAdd2", method = RequestMethod.POST)
    public Object testAdd2() {
//        Token token=new Token();
//        token.setToken("2");
//        token.setCreateDate(null);
//
//        Token token2=new Token();
//        Wrapper wrapper=new UpdateWrapper(token2);
//        tokenMapper.update(token,wrapper);
//        Duration duration=Duration.ofMinutes(1);
//        SessionUtil.redisTemplate.opsForValue().set("hyj0508-2","ooo",duration);

        System.out.println(request.getSession().getAttribute("hyj0508"));

        return JsonResponse.success("jj");
    }


}
