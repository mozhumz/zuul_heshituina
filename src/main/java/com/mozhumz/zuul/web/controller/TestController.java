package com.mozhumz.zuul.web.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mozhumz.zuul.mapper.ITokenMapper;
import com.mozhumz.zuul.model.entity.Token;
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
    @Resource
    ITokenMapper tokenMapper;



    @ApiOperation(value = "添加")
    @RequestMapping(value = "/testAdd", method = RequestMethod.POST)
    public Object testAdd() {
        Token token=new Token();
        token.setToken("2");
        token.setCreateDate(null);

        Token token2=new Token();
        token2.setUsername("1");
        Wrapper wrapper=new UpdateWrapper(token2);
        tokenMapper.update(token,wrapper);

        return JsonResponse.success("jj");
    }


}
