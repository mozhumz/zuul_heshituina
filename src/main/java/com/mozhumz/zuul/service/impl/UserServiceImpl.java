package com.mozhumz.zuul.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyj.util.exception.BaseException;
import com.hyj.util.param.CheckParamsUtil;
import com.mozhumz.zuul.enums.ErrorCode;
import com.mozhumz.zuul.enums.PwdEnum;
import com.mozhumz.zuul.mapper.*;
import com.mozhumz.zuul.model.dto.ChangePwdDto;
import com.mozhumz.zuul.model.dto.CheckTokenDto;
import com.mozhumz.zuul.model.dto.LoginDto;
import com.mozhumz.zuul.model.dto.SessionUser;
import com.mozhumz.zuul.model.entity.*;
import com.mozhumz.zuul.model.qo.UserRoleQo;
import com.mozhumz.zuul.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mozhumz.zuul.utils.MD5Util;
import com.mozhumz.zuul.utils.SessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
    @Resource
    private ITokenWebMapper tokenWebMapper;
    @Resource
    private IRoleMapper roleMapper;
    @Resource
    private IUserRoleMapper userRoleMapper;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public SessionUser login(LoginDto user) {
        //账号密码检查
        if (user == null || !CheckParamsUtil.check(user.getUsername(), user.getPassword())) {
            throw new BaseException(ErrorCode.LOGIN_ERR.desc, ErrorCode.LOGIN_ERR.code);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", user.getUsername());
        User user1 = userMapper.selectOne(wrapper);
        if (user1 == null
                || !MD5Util.checkPwd(user.getPassword(), user1.getPassword())) {
            throw new BaseException(ErrorCode.LOGIN_ERR.desc, ErrorCode.LOGIN_ERR.code);
        }
        if (user1.getState() != 1) {
            throw new BaseException(ErrorCode.USER_FREEZE_ERR.desc);
        }
        //存储token
        String tokenStr = UUID.randomUUID().toString();
        Token token = new Token();
        token.setToken(tokenStr);
        token.setCreateDate(new Date());
        token.setUpdateDate(new Date());
        try {

            tokenMapper.insert(token);
        } catch (DuplicateKeyException e) {
//            UpdateWrapper<Token> wrapper2=new UpdateWrapper<>();
//            wrapper2.eq("userId",user1.getId());
//            token.setCreateDate(null);
//            token.setState(1);
//            tokenMapper.update(token,wrapper2);

        }

        //组装数据
        SessionUser userDto = new SessionUser();
        BeanUtils.copyProperties(user1, userDto);
        userDto.setToken(tokenStr);
        userDto.setPassword(null);
        //判断用户是否有多种角色
        UserRoleQo userRoleQo = new UserRoleQo();
        userRoleQo.setUserId(userDto.getId());
        List<Role> roleList = userMapper.findUserRoleList(userRoleQo);
        if (CollectionUtils.isEmpty(roleList)) {
            throw new BaseException(ErrorCode.ROLE_INIT_ERR.desc);
        }
        userDto.setRoleList(roleList);
        if (roleList.size() == 1) {
            //只有一个角色 直接设置到session
            userDto.setRole(roleList.get(0));
        }

        return userDto;
    }


    /**
     * 获取登录用户
     *
     * @return
     */
    @Override
    public SessionUser getLoginUser() {
        return null;
    }

    /**
     * 校验token
     *
     * @return
     */
    @Override
    public boolean checkToken(CheckTokenDto checkTokenDto) {
        if (!CheckParamsUtil.check(checkTokenDto.getToken())) {
            return false;
        }

        QueryWrapper<Token> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", checkTokenDto.getToken());
        queryWrapper.eq("state", 1);
        Token token = tokenMapper.selectOne(queryWrapper);
        boolean flag = (token != null);
        if (flag) {
            //校验通过 注册应用地址 t_token_web
            TokenWeb tokenWeb = new TokenWeb();
            tokenWeb.setTokenId(token.getId());
            tokenWeb.setOutUrl(checkTokenDto.getOutUrl());
            tokenWeb.setSessionId(checkTokenDto.getSessionId());
            tokenWeb.setCreateDate(new Date());
            tokenWeb.setUpdateDate(new Date());
            tokenWebMapper.insert(tokenWeb);
        }

        return flag;
    }

    @Override
    public void checkEmail(String username, String email) {
        if (!CheckParamsUtil.check(username, email)) {
            throw new BaseException(ErrorCode.PARAM_ERR.desc);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !email.equals(user.getEmail())) {
            throw new BaseException(ErrorCode.USER_EMAIL_ERR.desc);
        }
    }


}
