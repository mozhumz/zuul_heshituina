package com.mozhumz.zuul.service.impl;

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
 * @since 2019-04-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

}
