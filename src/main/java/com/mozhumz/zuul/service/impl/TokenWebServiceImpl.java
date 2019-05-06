package com.mozhumz.zuul.service.impl;

import com.mozhumz.zuul.model.entity.TokenWeb;
import com.mozhumz.zuul.mapper.ITokenWebMapper;
import com.mozhumz.zuul.service.ITokenWebService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认证中心-web应用-用户退出地址 服务实现类
 * </p>
 *
 * @author lshaci
 * @since 2019-05-06
 */
@Service
public class TokenWebServiceImpl extends ServiceImpl<ITokenWebMapper, TokenWeb> implements ITokenWebService {

}
