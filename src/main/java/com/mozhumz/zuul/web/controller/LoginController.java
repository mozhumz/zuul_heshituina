package com.mozhumz.zuul.web.controller;

import com.hyj.util.exception.BaseException;
import com.hyj.util.param.CheckParamsUtil;
import com.hyj.util.web.JsonResponse;
import com.mozhumz.zuul.constant.CommonConstant;
import com.mozhumz.zuul.enums.ErrorCode;
import com.mozhumz.zuul.feign.IUsermanageFeign;
import com.mozhumz.zuul.model.dto.*;
import com.mozhumz.zuul.model.entity.Role;
import com.mozhumz.zuul.service.IRoleService;
import com.mozhumz.zuul.service.IUserService;
import com.mozhumz.zuul.utils.EmailUtil;
import com.mozhumz.zuul.utils.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    @Resource
    private IRoleService roleService;
    @Resource
    private IUsermanageFeign usermanageFeign;

    @Value("${login.url}")
    private String loginUrl;

    @Value("${session-redis.timeout}")
    private Long sessionSeconds;


    @ApiOperation(value = "登录(前端已表单形式提交参数)")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResponse<SessionUser> login(@RequestBody LoginDto user) {
        SessionUser userDto = userService.login(user);
        //登录成功 设置session 前端跳转到webUrl
        HttpSession session = request.getSession();
        session.setAttribute(CommonConstant.token, userDto.getToken());
        SessionUtil.setSessionUser(sessionSeconds, userDto);

        return JsonResponse.success(userDto);
    }

    @ApiOperation(value = "多角色时-设置角色")
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    public JsonResponse<SessionUser> setRole(@RequestBody Role role) {
        if (role == null || !CheckParamsUtil.check(role.getId() + "")) {
            throw new BaseException(ErrorCode.PARAM_ERR.desc);
        }
        SessionUser userDto = SessionUtil.getLoginUser();
        Role role1 = roleService.getById(role.getId());
        if (role1 == null) {
            throw new BaseException(ErrorCode.ROLE_NOT_EXIST_ERR.desc);
        }
        userDto.setRole(role1);
        SessionUtil.setSessionUser(sessionSeconds, userDto);

        return JsonResponse.success(userDto);
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public JsonResponse<String> logOut() throws IOException {
        request.getSession().invalidate();
        System.out.println("out-ok");
        response.sendRedirect(loginUrl);
        return JsonResponse.success(null);
    }

    @ApiOperation(value = "登录检查-获取token")
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public void checkLogin(String webUrl) throws IOException {
        String token = (String) request.getSession().getAttribute(CommonConstant.token);
        if (CheckParamsUtil.check(token)) {
            //重定向到应用
            response.sendRedirect(webUrl + "?" + CommonConstant.token + "=" + token);
        } else {
            //重定向到登录页
            response.sendRedirect(loginUrl + "?webUrl=" + webUrl);
        }
    }

    @ApiOperation(value = "token校验")
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public JsonResponse checkToken(@RequestBody CheckTokenDto checkTokenDto) {

        return JsonResponse.success(userService.checkToken(checkTokenDto));
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public JsonResponse<String> changePwd(@RequestBody ChangePwdDto changePwdDto) {

        return usermanageFeign.changePwd(changePwdDto);
    }

    @ApiOperation(value = "发送邮箱验证码")
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public JsonResponse sendCode(@RequestBody SendEmailDto sendEmailDto) {
        userService.checkEmail(sendEmailDto.getUsername(), sendEmailDto.getReceiveEmail());
        sendEmailDto.setKey(CommonConstant.userCode + sendEmailDto.getUsername());
        sendEmailDto.setTitle(CommonConstant.emailCodeTitle);
        sendEmailDto.setContent(EmailUtil.getEmailCode(6));

        return usermanageFeign.sendEmailCode(sendEmailDto);
    }

}
