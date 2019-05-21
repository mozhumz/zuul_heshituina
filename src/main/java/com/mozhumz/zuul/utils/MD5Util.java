package com.mozhumz.zuul.utils;

import com.hyj.util.param.CheckParamsUtil;
import org.springframework.util.DigestUtils;

/**
 * @author huyuanjia
 * @date 2019/2/24 16:24
 */
public class MD5Util {
    public static final String DEFAULT_KEY="Mozhumz_Xr_WangWei";
    public static final String PWD_KEY="Mozhumz_555_PWD_KEY";


    /**
     * MD5方法
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) {
        if (!CheckParamsUtil.check(key)) {
            return DigestUtils.md5DigestAsHex(text.getBytes());
        }
        //加密后的字符串
        return DigestUtils.md5DigestAsHex((text + key).getBytes()).toString();
    }

    /**
     * 密码校验
     * @param pwd0 前端密码
     * @param pwd1 数据库密码
     * @return
     */
    public static boolean checkPwd(String pwd0,String pwd1) {
        if (!CheckParamsUtil.check(pwd0,pwd1)) {
            return false;
        }
        return md5(pwd0, MD5Util.PWD_KEY).equals(pwd1);
    }

    /**
     * 默认密码 123456
     * @return
     */
    public static String getDefaultPwd(){
        //147397e354ea6a1b25bcaa82c2692614
        return md5("123456",DEFAULT_KEY);
    }

    public static void main(String[] args) {
        System.out.println(md5(getDefaultPwd(),PWD_KEY));
    }


}
