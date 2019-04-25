package com.zhongping.yingyun.tool;

public class ThreadUtil {
    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
