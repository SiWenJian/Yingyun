package com.zhongping.yingyun;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Wenjian on 2019/4/24.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void handleMsg(Message msg);
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                default:
                    handleMsg(msg);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
