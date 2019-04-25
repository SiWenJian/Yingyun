package com.zhongping.yingyun.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2018/4/8.
 */

public class StringUtil {
    //手机号正则表达式
    public static final String PHONE_NUMBER_REG="^0?(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$";
    //密码正则表达式
    public static final String PASSWORD_REG="^\\w+$";
    //邮箱的正则表达式
    public static final String EMAIL_REG = "[\\w]+@[\\w]+.[\\w]+";
    //是否是空字符串
    public static boolean isEmptyStr(String str){
        return str==null || str.trim().length()==0||str.equals("null");
    }
    public static boolean isPassword(String password){
        Pattern p= Pattern.compile(PASSWORD_REG);
        Matcher m=p.matcher(password);
        return m.matches();
    }
    //是否是手机号
    public static boolean isPhoneNumber(String phone){
        Pattern p= Pattern.compile(PHONE_NUMBER_REG);
        Matcher m=p.matcher(phone);
        return m.matches();
    }
    public static boolean isEmail(String email){
        Pattern p= Pattern.compile(EMAIL_REG);
        Matcher m=p.matcher(email);
        return m.matches();
    }
}
