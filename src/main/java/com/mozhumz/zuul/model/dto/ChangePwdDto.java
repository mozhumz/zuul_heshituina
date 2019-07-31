package com.mozhumz.zuul.model.dto;

import com.hyj.util.anno.IsNeed;
import lombok.Data;

@Data
public class ChangePwdDto {
    @IsNeed(flag = true)
    private String newPwd;
    @IsNeed(flag = true)
    private String code;
    /**
     * 1 登录密码 2 操作密码
     */
    @IsNeed(flag = true)
    private Integer type;

    /**
     * 是否是登录用户
     */
    private boolean isLogin=true;

    private String username;
    private String email;

    public boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean login) {
        isLogin = login;
    }
}
