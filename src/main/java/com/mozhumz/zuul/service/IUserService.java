package com.mozhumz.zuul.service;

import com.mozhumz.zuul.model.dto.*;
import com.mozhumz.zuul.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
public interface IUserService extends IService<User> {
    SessionUser login(LoginDto user);


    SessionUser getLoginUser();

    boolean checkToken(CheckTokenDto checkTokenDto);

    void checkEmail(String username,String email);




}
