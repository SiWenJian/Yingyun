package com.zhongping.yingyun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhongping.yingyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wenjian on 2019/4/24.
 */

public class LoginSuccessActivity extends AppCompatActivity {
    @BindView(R.id.close)
    TextView close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.close)
    public void onViewClicked() {
        finish();
    }
}
