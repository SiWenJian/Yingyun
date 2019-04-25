package com.zhongping.yingyun.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhongping.yingyun.R;
import com.zhongping.yingyun.bean.MsgBean;
import com.zhongping.yingyun.tool.ThreadUtil;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wenjian on 2019/4/24.
 */

public class MsgCodeActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.time)
    TextView timetext;
    @BindView(R.id.rel_time)
    RelativeLayout relTime;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.sendagain)
    TextView sendagain;
    @BindView(R.id.item_edittext)
    EditText itemEdittext;
    @BindView(R.id.item_code_iv0)
    TextView itemCodeIv0;
    @BindView(R.id.item_code_iv1)
    TextView itemCodeIv1;
    @BindView(R.id.item_code_iv2)
    TextView itemCodeIv2;
    @BindView(R.id.item_code_iv3)
    TextView itemCodeIv3;
    @BindView(R.id.item_code_iv4)
    TextView itemCodeIv4;
    @BindView(R.id.item_code_iv5)
    TextView itemCodeIv5;
    @BindView(R.id.develop)
    ImageView develop;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.text8)
    TextView text8;
    @BindView(R.id.text9)
    TextView text9;
    @BindView(R.id.text0)
    TextView text0;
    @BindView(R.id.delect)
    LinearLayout delect;
    @BindView(R.id.lin_jianpan)
    LinearLayout linJianpan;
    private Animation rotate;
    private String showphone = "";
    OkHttpClient okHttpClient;
    MsgBean msgBean;
    private String inputContent;
    boolean canagain = false;
    int time = 60;

    private TextView[] textViews;
    private static int MAX = 6;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 111:
                    ToastUtils.showShort(getApplicationContext(),"网络超时");
                    break;
                case 333:
                    ToastUtils.showShort(getApplicationContext(), msg.arg1 + "***验证码错误");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgcode);
        ButterKnife.bind(this);
        showphone = getIntent().getStringExtra("phone");
        phone.setText(showphone);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        if (rotate != null) {
            img.startAnimation(rotate);
        }
        textViews = new TextView[MAX];
        textViews[0] = (TextView) findViewById(R.id.item_code_iv0);
        textViews[1] = (TextView) findViewById(R.id.item_code_iv1);
        textViews[2] = (TextView) findViewById(R.id.item_code_iv2);
        textViews[3] = (TextView) findViewById(R.id.item_code_iv3);
        textViews[4] = (TextView) findViewById(R.id.item_code_iv4);
        textViews[5] = (TextView) findViewById(R.id.item_code_iv5);
        itemEdittext.setCursorVisible(false);
        itemEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = itemEdittext.getText().toString();
                if (inputContent.length() >= MAX) {
                    verMsg(Urls.VERMSG + "telephone=" + showphone + "&type=3&verification=" + inputContent);
                } else {

                }
                for (int i = 0; i < MAX; i++) {
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                        textViews[i].setTextColor(getResources().getColor(R.color.blue));
                        for (int n = i; n > 0; n--) {
                            textViews[n - 1].setTextColor(getResources().getColor(R.color.textcolor));
                        }
                    } else {
                        textViews[i].setText("");
                    }
                }
            }
        });
        countDown();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (rotate != null) {
            img.clearAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    //验证验证码
    public void verMsg(String url) {
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
                Log.d("验核状况", response.body().string());
                if (response.code() == 200) {

                } else {
                    Message message = new Message();
                    message.what = 333;
                    message.arg1 = response.code();
                    handler.sendMessage(message);
                }
            }
        });
    }

    @OnClick({R.id.back, R.id.sendagain, R.id.item_edittext,R.id.develop, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9, R.id.text0, R.id.delect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sendagain:
                if (canagain) {
                    itemEdittext.setText("");
                    time=60;
                    countDown();
                    relTime.setVisibility(View.VISIBLE);
                    img.startAnimation(rotate);
//                getMsg(Urls.GETMSG + "telephone=" + showphone + "&type=3");
                } else {
                    ToastUtils.showShort(getApplicationContext(), time + "s后重试");
                }
                break;
            case R.id.item_edittext:
                linJianpan.setVisibility(View.VISIBLE);
                InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(itemEdittext.getWindowToken(), 0);
                break;
            case R.id.develop:
                linJianpan.setVisibility(View.GONE);
                break;
            case R.id.text1:
                itemEdittext.setText(itemEdittext.getText().toString() + "1");
                break;
            case R.id.text2:
                itemEdittext.setText(itemEdittext.getText().toString() + "2");
                break;
            case R.id.text3:
                itemEdittext.setText(itemEdittext.getText().toString() + "3");
                break;
            case R.id.text4:
                itemEdittext.setText(itemEdittext.getText().toString() + "4");
                break;
            case R.id.text5:
                itemEdittext.setText(itemEdittext.getText().toString() + "5");
                break;
            case R.id.text6:
                itemEdittext.setText(itemEdittext.getText().toString() + "6");
                break;
            case R.id.text7:
                itemEdittext.setText(itemEdittext.getText().toString() + "7");
                break;
            case R.id.text8:
                itemEdittext.setText(itemEdittext.getText().toString() + "8");
                break;
            case R.id.text9:
                itemEdittext.setText(itemEdittext.getText().toString() + "9");
                break;
            case R.id.text0:
                itemEdittext.setText(itemEdittext.getText().toString() + "0");
                break;
            case R.id.delect:
                if (itemEdittext.getText().toString().length() != 0) {
                    String s = itemEdittext.getText().toString();
                    itemEdittext.setText(s.substring(0, s.length() - 1));
                }
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
                if (response.code() == 200) {
                    msgBean = new Gson().fromJson(response.body().string(), MsgBean.class);
                    handler.sendEmptyMessage(222);
                } else {
                    Message message = new Message();
                    message.what = 444;
                    message.arg1 = response.code();
                    handler.sendMessage(message);
                }
            }
        });
    }

    //跑秒
    public void countDown() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ((time--) != 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timetext.setText((time) + "s");
                        }
                    });
                    ThreadUtil.sleep(1000L);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        canagain = true;
                        img.clearAnimation();
                        relTime.setVisibility(View.GONE);
                    }
                });

            }
        }).start();
    }

}
