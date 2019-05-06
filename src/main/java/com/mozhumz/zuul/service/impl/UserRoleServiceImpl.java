package com.mozhumz.zuul.service.impl;

import com.mozhumz.zuul.model.entity.UserRole;
import com.mozhumz.zuul.mapper.IUserRoleMapper;
import com.mozhumz.zuul.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<IUserRoleMapper, UserRole> implements IUserRoleService {

}
