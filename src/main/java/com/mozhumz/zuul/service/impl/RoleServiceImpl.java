package com.mozhumz.zuul.service.impl;

import com.mozhumz.zuul.model.entity.Role;
import com.mozhumz.zuul.mapper.IRoleMapper;
import com.mozhumz.zuul.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, Role> implements IRoleService {

}
