package com.zhongping.yingyun.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.zhongping.yingyun.R;
import com.zhongping.yingyun.bean.MsgBean;
import com.zhongping.yingyun.tool.StringUtil;
import com.zhongping.yingyun.tool.ToastUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wenjian on 2019/4/24.
 */

public class ForgetPsdActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.send)
    Button send;
    Intent intent;
    @BindView(R.id.empty_phone)
    ImageView emptyPhone;
    @BindView(R.id.progress)
    ProgressBar progress;
    OkHttpClient okHttpClient;
    MsgBean msgBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 111:
                    ToastUtils.showShort(getApplicationContext(),"网络超时");
                    send.setClickable(true);
                    progress.setVisibility(View.VISIBLE);
                    break;
                case 222:
                    if (msgBean!=null&&msgBean.getResultCode().equals("1")){
                        ToastUtils.showShort(getApplicationContext(),"短信发送成功");
                        intent = new Intent(getApplicationContext(), MsgCodeActivity.class);
                        intent.putExtra("phone",phone.getText().toString());
                        startActivity(intent);
                        finish();
                    }else {
                        ToastUtils.showShort(getApplicationContext(),"短信发送成功");
                        progress.setVisibility(View.GONE);
                        send.setClickable(true);
                    }
                    break;
                case 333:
                    ToastUtils.showShort(getApplicationContext(),msg.arg1+"");
                    progress.setVisibility(View.GONE);
                    send.setClickable(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsd);
        ButterKnife.bind(this);
        send.setClickable(false);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    emptyPhone.setVisibility(View.VISIBLE);
                } else {
                    emptyPhone.setVisibility(View.GONE);
                }
                if (s.length() == 11) {
                    send.setClickable(true);
                    send.setBackground(getResources().getDrawable(R.drawable.bg_btn));
                } else {
                    send.setClickable(false);
                    send.setBackground(getResources().getDrawable(R.drawable.bg_sure));
                }
            }
        });
    }

    @OnClick({R.id.back, R.id.send, R.id.empty_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.send:
                if (StringUtil.isEmptyStr(phone.getText().toString())) {
                    ToastUtils.showShort(getApplicationContext(), "请输入手机号");
                    return;
                }
                if (!StringUtil.isPhoneNumber(phone.getText().toString())) {
                    ToastUtils.showShort(getApplicationContext(), "请输入正确的手机格式");
                    return;
                }
                send.setClickable(false);
//                getMsg(Urls.GETMSG + "telephone=" + phone.getText().toString() + "&type=3");
                intent = new Intent(getApplicationContext(), MsgCodeActivity.class);
                intent.putExtra("phone",phone.getText().toString());
                startActivity(intent);
                finish();
                break;
            case R.id.empty_phone:
                phone.setText("");
                break;
        }
    }

    //获取验证码
    public void getMsg(String url) {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e instanceof SocketTimeoutException) {//判断超时异常
                    Log.d("超时", "访问超时");
                    handler.sendEmptyMessage(111);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    msgBean=new Gson().fromJson(response.body().string(),MsgBean.class);
                    handler.sendEmptyMessage(222);
                }else {
                    Message message=new Message();
                    message.what=333;
                    message.arg1=response.code();
                    handler.sendMessage(message);
                }
            }
        });
    }
}
