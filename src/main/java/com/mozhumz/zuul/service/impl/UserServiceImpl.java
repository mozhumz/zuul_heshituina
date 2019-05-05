package com.mozhumz.zuul.service.impl;

import com.mozhumz.zuul.model.dto.UserDto;
import com.mozhumz.zuul.model.entity.User;
import com.mozhumz.zuul.mapper.IUserMapper;
import com.mozhumz.zuul.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    @Override
    public UserDto login(User user) {
        return null;
    }

    @Override
    public UserDto getLoginUser() {
        return null;
    }
}
