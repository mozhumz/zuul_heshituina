package com.mozhumz.zuul.mapper;

import com.mozhumz.zuul.model.entity.Role;
import com.mozhumz.zuul.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mozhumz.zuul.model.qo.UserRoleQo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
public interface IUserMapper extends BaseMapper<User> {
    List<Role> findUserRoleList(UserRoleQo userRoleQo);
}
