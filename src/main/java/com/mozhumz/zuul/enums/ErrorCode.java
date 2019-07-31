package com.mozhumz.zuul.enums;

/**
 * @author huyuanjia
 * @date 2019/5/6 20:53
 * 错误码
 */
public enum ErrorCode {
    LOGIN_ERR(10001,"账号或密码错误"),
    LOGIN_EXP_ERR(10005,"登录失效，请重新登录"),
    PARAM_ERR(10002,"参数错误"),
    ROLE_ERR(10003,"角色错误"),
    ROLE_INIT_ERR(10004,"您没有初始化角色，请联系管理员添加"),
    ROLE_NOT_EXIST_ERR(10006,"该角色已经删除，请重新选择"),
    USER_FREEZE_ERR(10007,"您已经被冻结，请联系admin管理员解冻"),
    PWD_ERR(10008,"密码错误"),
    SAME_PWD_ERR(10009,"新旧密码不能相同"),
    SYS_EMAIL_NOT_SET_ERR(10010,"系统邮箱未设置，不能发送邮件，请先设置"),
    SEND_EMAIL_ERR(10011,"发送邮件出错:"),
    EMAIL_EXIST_ERR(10012,"该邮箱账号已存在"),
    EMAIL_CODE_ERR(10013,"验证码错误"),
    USER_ERR(10014,"当前用户已经被删除"),

    USER_EMAIL_ERR(20001,"该账户未设置邮箱或与输入的邮箱不匹配"),
    ;
    public Integer code;
    public String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
