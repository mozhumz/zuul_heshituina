package com.mozhumz.zuul.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hyj.util.param.CheckParamsUtil;
import com.mozhumz.zuul.enums.ErrorCode;
import com.mozhumz.zuul.mapper.ITokenMapper;
import com.mozhumz.zuul.model.dto.UserDto;
import com.mozhumz.zuul.model.entity.Token;
import com.mozhumz.zuul.model.entity.User;
import com.mozhumz.zuul.mapper.IUserMapper;
import com.mozhumz.zuul.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mozhumz.zuul.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import top.lshaci.framework.common.exception.BaseException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {
    @Resource
    private IUserMapper userMapper;
    @Resource
    private ITokenMapper tokenMapper;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public UserDto login(User user) {
        //账号密码检查
        if (user == null || !CheckParamsUtil.check(user.getUsername(), user.getPassword())) {
            throw new BaseException(ErrorCode.LOGIN_ERR.desc);
        }
        Wrapper wrapper = new QueryWrapper(user);
        User user1 = userMapper.selectOne(wrapper);
        if (user1 == null
                || !user1.getPassword().equals(MD5Util.md5(user.getPassword(), MD5Util.DEFAULT_KEY))) {
            throw new BaseException(ErrorCode.LOGIN_ERR.desc);
        }
        //存储token
        String tokenStr= UUID.randomUUID().toString();
        Token token=new Token();
        token.setUsername(user.getUsername());
        token.setToken(tokenStr);
        token.setCreateDate(new Date());
        token.setUpdateDate(new Date());
        try {

            tokenMapper.insert(token);
        }catch (DuplicateKeyException e){
            Token token2=new Token();
            token2.setUsername(user.getUsername());
            Wrapper wrapper2=new UpdateWrapper(token2);
            token.setCreateDate(null);
            tokenMapper.update(token,wrapper2);
        }

        //组装数据
        UserDto userDto= new UserDto();
        BeanUtils.copyProperties(user1,userDto);
        userDto.setToken(tokenStr);

        return userDto;
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public UserDto addUser(User user) {
        return null;
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    @Override
    public UserDto getLoginUser() {
        return null;
    }
}
