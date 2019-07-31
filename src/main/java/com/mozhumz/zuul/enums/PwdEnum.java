package com.mozhumz.zuul.enums;

public enum PwdEnum {
    login(1,"登录密码"),
    balance(2,"扣款密码"),

    ;

    public Integer code;
    public String desc;

    PwdEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
