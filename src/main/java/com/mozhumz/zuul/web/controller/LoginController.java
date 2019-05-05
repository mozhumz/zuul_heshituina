package com.mozhumz.zuul.web.controller;

import com.mozhumz.zuul.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.lshaci.framework.web.model.JsonResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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



    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResponse<String> login(@RequestBody User user) {
        //TODO
        request.getSession().setAttribute("token","ss");
        return JsonResponse.success("jj");
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public JsonResponse<String> logOut(@RequestBody User user) {
        request.getSession().invalidate();
        return JsonResponse.success(null);
    }


}
