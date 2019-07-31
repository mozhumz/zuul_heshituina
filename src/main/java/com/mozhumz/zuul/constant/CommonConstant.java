package com.mozhumz.zuul.constant;

/**
 * @author huyuanjia
 * @date 2019/5/7 9:09
 */
public class CommonConstant {
    public static  final String token="token";

    public static  final String globalSessionUser="globalSessionUser";

    /**
     * 客户验证码前缀
     */
    public static final String customerCode="customerCode";

    public static final String userCode="userCode";

    public static final long customerCodeSeconds=60*5;

    public static final String[]remoteUrls={"/api/sys/sendEmailCode"};

    public static final String emailCodeTitle="何氏推拿-验证码";
}
