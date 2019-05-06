package com.mozhumz.zuul.service;

import com.mozhumz.zuul.model.dto.UserDto;
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
    UserDto login(User user);

    UserDto addUser(User user);

    UserDto getLoginUser();


}
