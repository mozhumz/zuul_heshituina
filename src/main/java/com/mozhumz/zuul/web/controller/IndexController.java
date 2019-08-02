package com.mozhumz.zuul.web.controller;

import com.hyj.util.web.JsonResponse;
import com.mozhumz.zuul.mapper.ITokenMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
public class IndexController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    @Value("${login.url}")
    private String loginUrl;




    @ApiOperation(value = "域名跳转")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void testAdd() {
        try {
            response.sendRedirect(loginUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
