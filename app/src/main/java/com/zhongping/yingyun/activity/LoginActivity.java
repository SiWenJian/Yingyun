package com.zhongping.yingyun.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhongping.yingyun.R;
import com.zhongping.yingyun.tool.SPUtils;
import com.zhongping.yingyun.tool.StringUtil;
import com.zhongping.yingyun.tool.ToastUtils;
import com.zhongping.yingyun.tool.Urls;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.empty_phone)
    ImageView emptyPhone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.empty_password)
    ImageView emptyPassword;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.savepsd)
    CheckBox savepsd;
    @BindView(R.id.forgetpsd)
    TextView forgetpsd;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.progress)
    ProgressBar progress;
    Intent intent;
    OkHttpClient okHttpClient;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:
                    ToastUtils.showShort(getApplicationContext(), msg.arg1 + "");
                    break;
                case 222:
                    progress.setVisibility(View.GONE);
                    ToastUtils.showShort(getApplicationContext(),"网络请求超时");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    emptyPhone.setVisibility(View.VISIBLE);
                } else {
                    emptyPhone.setVisibility(View.GONE);
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    emptyPassword.setVisibility(View.VISIBLE);
                } else {
                    emptyPassword.setVisibility(View.GONE);
                }
            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    emptyPhone.setVisibility(View.GONE);
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    emptyPassword.setVisibility(View.GONE);
                }
            }
        });
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        if (!StringUtil.isEmptyStr(SPUtils.get(getApplicationContext(),"phone","").toString())){
            phone.setText(SPUtils.get(getApplicationContext(),"phone","").toString());
        }
        if (!StringUtil.isEmptyStr(SPUtils.get(getApplicationContext(),"password","").toString())){
            password.setText(SPUtils.get(getApplicationContext(),"password","").toString());
            savepsd.setChecked(true);
        }
    }

    @OnClick({R.id.empty_phone, R.id.empty_password, R.id.login, R.id.savepsd, R.id.forgetpsd, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.empty_phone:
                phone.setText("");
                break;
            case R.id.empty_password:
                password.setText("");
                break;
            case R.id.login:
                if (StringUtil.isEmptyStr(phone.getText().toString())) {
                    ToastUtils.showShort(getApplicationContext(), "请输入手机号");
                    return;
                }
                if (!StringUtil.isPhoneNumber(phone.getText().toString())) {
                    ToastUtils.showShort(getApplicationContext(), "请输入合法手机号");
                    return;
                }
                if (StringUtil.isEmptyStr(password.getText().toString())) {
                    ToastUtils.showShort(getApplicationContext(), "请输入登录密码");
                    return;
                }
                if (!StringUtil.isPassword(password.getText().toString())) {
                    ToastUtils.showShort(getApplicationContext(), "密码只能包含大小写字母和数字");
                    return;
                }
                login.setClickable(false);
                progress.setVisibility(View.VISIBLE);
                if (savepsd.isChecked()){
                    SPUtils.put(getApplicationContext(),"password",password.getText().toString());
                }else {
                    SPUtils.put(getApplicationContext(),"password","");
                }
                SPUtils.put(getApplicationContext(),"phone",phone.getText().toString());
                login(Urls.LOGIN);
                break;
            case R.id.savepsd:
                break;
            case R.id.forgetpsd:
                intent = new Intent(getApplicationContext(), ForgetPsdActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    //登录
    public void login(String url) {
        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS).build();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", phone.getText().toString())
                .add("password", password.getText().toString())
                .add("loginType", "app")
                .add("type", "3").build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e instanceof SocketTimeoutException) {//判断超时异常
                    Log.d("超时", "访问超时");
                    handler.sendEmptyMessage(222);
                    login.setClickable(true);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int code = response.code();
                Log.d("状态码", response.code() + "");
                if (code == 200) {
                    intent = new Intent(getApplicationContext(), LoginSuccessActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Message message = new Message();
                    message.what = 123;
                    message.arg1 = code;
                    handler.sendMessage(message);
                    login.setClickable(true);
                }
            }
        });
    }
}
