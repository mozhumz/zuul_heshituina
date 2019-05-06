package com.mozhumz.zuul.service.impl;

import com.mozhumz.zuul.model.entity.Token;
import com.mozhumz.zuul.mapper.ITokenMapper;
import com.mozhumz.zuul.service.ITokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认证中心-web应用-用户登录成功的令牌 服务实现类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
@Service
public class TokenServiceImpl extends ServiceImpl<ITokenMapper, Token> implements ITokenService {

}
