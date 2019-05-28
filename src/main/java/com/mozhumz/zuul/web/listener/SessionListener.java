package com.mozhumz.zuul.web.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hyj.util.param.CheckParamsUtil;
import com.mozhumz.zuul.constant.CommonConstant;
import com.mozhumz.zuul.mapper.ITokenMapper;
import com.mozhumz.zuul.mapper.ITokenWebMapper;
import com.mozhumz.zuul.model.entity.Token;
import com.mozhumz.zuul.model.entity.TokenWeb;
import com.mozhumz.zuul.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huyuanjia
 * @date 2019/5/5 15:06
 */
@Component
@Slf4j
public class SessionListener implements HttpSessionListener {
    @Resource
    private ITokenMapper tokenMapper;
    @Resource
    private ITokenWebMapper tokenWebMapper;
    @Value("${login.url}")
    private String loginUrl;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("sessionDestroyed");
        log.info(""+tokenMapper);
        // session失效后 退出各个web应用
        String tokenStr = (String) se.getSession().getAttribute(CommonConstant.token);
        if (CheckParamsUtil.check(tokenStr)) {
            //修改t_token state=2
            Token param = new Token();
            param.setToken(tokenStr);
            QueryWrapper<Token> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("token",tokenStr);
            Token token = tokenMapper.selectOne(queryWrapper);
            if (token == null) {
                return;
            }
            tokenMapper.deleteById(token.getId());

            //处理t_token_web记录
            Map<String, Object> map = new HashMap<>();
            map.put("tokenId", token.getId());
            List<TokenWeb> list = tokenWebMapper.selectByMap(map);
            if(!CollectionUtils.isEmpty(list)){
                for (TokenWeb tokenWeb : list) {
                    //调用每个web应用的退出接口
                    HttpUtil.logOutWeb(tokenWeb.getOutUrl(),tokenWeb.getSessionId());
                    //删除记录
                    tokenWebMapper.deleteById(tokenWeb.getId());
                }
            }

        }
//        try {
//            response.sendRedirect(loginUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
