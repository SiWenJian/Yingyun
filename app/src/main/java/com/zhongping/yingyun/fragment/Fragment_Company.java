package com.zhongping.yingyun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhongping.yingyun.R;
import com.zhongping.yingyun.ViewPagerFragment;
import com.zhongping.yingyun.activity.ProtocolActivity;
import com.zhongping.yingyun.bean.RegisterBean;
import com.zhongping.yingyun.bean.ResultBean;
import com.zhongping.yingyun.tool.StringUtil;
import com.zhongping.yingyun.tool.ToastUtils;
import com.zhongping.yingyun.tool.Urls;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Wenjian on 2019/4/23.
 */

public class Fragment_Company extends ViewPagerFragment {
    View view;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.empty_name)
    ImageView emptyName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.empty_phone)
    ImageView emptyPhone;
    @BindView(R.id.correct)
    ImageView correct;
    @BindView(R.id.repeat)
    TextView repeat;
    @BindView(R.id.psd)
    EditText psd;
    @BindView(R.id.empty_psd)
    ImageView emptyPsd;
    @BindView(R.id.newpsd)
    EditText newpsd;
    @BindView(R.id.empty_newpsd)
    ImageView emptyNewpsd;
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.zhucexieyi)
    TextView zhucexieyi;
    @BindView(R.id.jiaoguixieyi)
    TextView jiaoguixieyi;
    @BindView(R.id.register)
    Button register;
    Unbinder unbinder;
    @BindView(R.id.progress)
    ProgressBar progress;
    OkHttpClient okHttpClient;
    ResultBean resultBean;
    boolean flag = false;
    RegisterBean registerBean;
    Intent intent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 111:
                    if (resultBean.getResultCode().equals("1")) {
                        correct.setVisibility(View.VISIBLE);
                        repeat.setVisibility(View.GONE);
                        flag = false;
                    } else {
                        correct.setVisibility(View.GONE);
                        repeat.setVisibility(View.VISIBLE);
                        flag = true;
                    }
                    break;
                case 222:
                    if (registerBean.getResultCode().equals("1")) {
                        ToastUtils.showShort(getActivity(), "注册成功");
                        progress.setVisibility(View.GONE);
                        getActivity().finish();
                    }
                    break;
                case 333:
                    progress.setVisibility(View.GONE);
                    ToastUtils.showShort(getActivity(),"网络请求超时");
                    register.setClickable(true);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_company, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    emptyName.setVisibility(View.VISIBLE);
                } else {
                    emptyName.setVisibility(View.GONE);
                }
            }
        });
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
                if (s.length() == 11) {
                    confirmation(Urls.VALIDATETEL + "Mobile=" + phone.getText().toString() + "&type=3");
                } else {
                    correct.setVisibility(View.GONE);
                    repeat.setVisibility(View.GONE);
                }
            }
        });
        psd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    emptyPsd.setVisibility(View.VISIBLE);
                } else {
                    emptyPsd.setVisibility(View.GONE);
                }
            }
        });
        newpsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    emptyNewpsd.setVisibility(View.VISIBLE);
                } else {
                    emptyNewpsd.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @OnClick({R.id.empty_name, R.id.empty_phone, R.id.empty_psd, R.id.empty_newpsd, R.id.zhucexieyi, R.id.jiaoguixieyi, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.empty_name:
                name.setText("");
                break;
            case R.id.empty_phone:
                phone.setText("");
                correct.setVisibility(View.GONE);
                repeat.setVisibility(View.GONE);
                break;
            case R.id.empty_psd:
                psd.setText("");
                break;
            case R.id.empty_newpsd:
                newpsd.setText("");
                break;
            case R.id.zhucexieyi:
                intent=new Intent(getActivity(), ProtocolActivity.class);
                startActivity(intent);
                break;
            case R.id.jiaoguixieyi:
                intent=new Intent(getActivity(), ProtocolActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                if (StringUtil.isEmptyStr(name.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "请输入您的姓名");
                    return;
                }
                if (StringUtil.isEmptyStr(phone.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "请输入手机号");
                    return;
                }
                if (!StringUtil.isPhoneNumber(phone.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "请输入格式正确的手机号");
                    return;
                }
                if (StringUtil.isEmptyStr(psd.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "请输入密码");
                    return;
                }
                if (StringUtil.isEmptyStr(newpsd.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "请输入确认密码");
                    return;
                }
                if (!StringUtil.isPassword(psd.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "密码只能包含大小写字母及阿拉伯数字");
                    return;
                }
                if (!psd.getText().toString().equals(newpsd.getText().toString())) {
                    ToastUtils.showShort(getActivity(), "两次输入密码不相同");
                    return;
                }
                if (flag) {
                    ToastUtils.showShort(getActivity(), "手机号已被注册");
                    return;
                }
                if (!agree.isChecked()) {
                    ToastUtils.showShort(getActivity(), "请阅读并同意相关协议");
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                register.setClickable(false);
                register(Urls.COMPANY);
                break;
        }
    }

    //验证手机号是否被注册过
    public void confirmation(String url) {
        okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    resultBean = new Gson().fromJson(response.body().string(), ResultBean.class);
                    if (resultBean != null) {
                        handler.sendEmptyMessage(111);
                    }
                }
            }
        });
    }
    //公司注册
    public void register(String url) {
        okHttpClient = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS).build();
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name.getText().toString())
                .add("password", psd.getText().toString())
                .add("mobile", phone.getText().toString())
                .add("companyType", "PERSON")
                .add("parentId", "83")
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e instanceof SocketTimeoutException) {//判断超时异常
                    Log.d("超时", "访问超时");
                    handler.sendEmptyMessage(333);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("注册结果",response.body().string());
                if (response.code() == 200) {
                    registerBean = new Gson().fromJson(response.body().string(), RegisterBean.class);
                    if (registerBean != null) {
                        handler.sendEmptyMessage(222);
                    }
                }
            }
        });
    }
}
