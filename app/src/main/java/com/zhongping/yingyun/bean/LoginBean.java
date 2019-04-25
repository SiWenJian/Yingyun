package com.zhongping.yingyun.bean;

/**
 * Created by Wenjian on 2019/4/24.
 */

public class LoginBean {

    /**
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWJJZCI6IjQwMjg5Yjk4NmE0MzBkOTgwMTZhNDQyYmJlZWEwMDBmIiwic3ViIjoiMTU2Mzg3NDg1MDMiLCJhdWRpZW5jZSI6IndlYiIsImF1dGgiOlsiR1JSWiIsIllIWloiLCJHUlpYIiwiZ2VyZW5yZW56aGVuZyJdLCJjcmVhdGVkIjoxNTU2MDc2NTg1MzEzLCJleHAiOjE1NTY2ODEzODV9.N0gdew6wwbB9nazRXPluRbzFv1sfkq0erHpxGQEsbL0PMObKXLRgEY92bEnwzKMqMLeGbi2W0DPGBhvdizwIfQ
     * success : true
     * url : null
     */

    private String token;
    private boolean success;
    private Object url;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }
}
