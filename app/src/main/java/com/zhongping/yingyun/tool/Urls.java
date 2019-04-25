package com.zhongping.yingyun.tool;

/**
 * Created by Wenjian on 2019/2/15.
 */

public class Urls {
    //服务器地址
    public static final String BASE="http://192.168.27.24:8080";
   //登录
    public static final String LOGIN=BASE+"/carrierLogin/auth";
    //检验手机号是否被注册
    public static final String VALIDATETEL=BASE+"/api/v1/app/employee/validateTel?";
    //个人注册
    public static final String PERSON=BASE+"/api/v1/app/employee/register";
    //公司注册
    public static final String COMPANY=BASE+"/api/v1/app/employee/registerCom";
    //获取验证码
    public static final String GETMSG=BASE+"/messageRecord/sendSmsValidation?";
    //验证验证码
    public static final String VERMSG=BASE+"/carrierEmployee/verification?";

}
