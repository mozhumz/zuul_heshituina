package com.mozhumz.zuul.web.controller;

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
@Api(value = "测试相关接口", description = "测试相关接口")
@RequestMapping("/api/demo")
public class TestController {
    @Resource
    private HttpServletRequest request;



    @ApiOperation(value = "添加")
    @RequestMapping(value = "/testAdd", method = RequestMethod.POST)
    public Object testAdd() {

        return JsonResponse.success("jj");
    }


}
