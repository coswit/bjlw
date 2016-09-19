package com.bxlwt.www.bxlwt.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.NetUtil;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.bean.CheckCodeBean;
import com.bxlwt.www.bxlwt.bean.MessageBean;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhengj on 2016/8/23.
 */
public class RegisterAct extends AppCompatActivity {

    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;
    @BindView(R.id.et_register_codes)
    EditText etRegisterCodes;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.btn_register_sendcodes)
    Button btnRegisterSendcodes;


    private Intent mIntent;
    private Context mContext;
    private CountDownTimer mCountDownTimer;
    private String mVerifyCode;
    private String mPwd;
    private String mUsername;

    private boolean mIsCodeSended = false;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mContext = MyApp.getContext();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在注册登录中");

        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnRegisterSendcodes.setText(millisUntilFinished / 1000 + "秒");
                btnRegisterSendcodes.setClickable(false);
            }

            @Override
            public void onFinish() {
                btnRegisterSendcodes.setText("获取验证码");
                btnRegisterSendcodes.setClickable(true);
            }
        };

    }

    @OnClick({R.id.ll_register_revert, R.id.btn_register_sendcodes, R.id.btn_register_confirm, R.id.ll_register_rebackLogin, R.id.image_register_wx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_register_revert://返回
            case R.id.ll_register_rebackLogin://返回
//                mIntent = new Intent(mContext, LoginAct.class);
//                startActivity(mIntent);
                finish();
                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                break;
            case R.id.btn_register_sendcodes://获取验证码
                sendVerifyCode();
                break;
            case R.id.btn_register_confirm://注册
                if (checkInfo()) {
                    checkVerifyCode();
                }
                break;

            case R.id.image_register_wx://微信平台登录
                break;
        }
    }

    /**
     * 注册验证
     *
     * @return
     */
    private boolean checkInfo() {
        mPwd = etRegisterPwd.getText().toString().trim();
        mUsername = etRegisterUsername.getText().toString().trim();
        mVerifyCode = etRegisterCodes.getText().toString().trim();
        if (TextUtils.isEmpty(mUsername)) {
            ToastUtil.show(this, "手机号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mVerifyCode)) {
            ToastUtil.show(this, "验证码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mPwd)) {
            ToastUtil.show(this, "密码不能为空");
            return false;
        }
        if (!NetUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext,"请检查网路是否连接");
            return false;
        }
        Pattern accountPattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher = accountPattern.matcher(mUsername);
        if (!matcher.matches()) {
            ToastUtil.show(getApplicationContext(), "手机号不合法");
            return false;
        }

        return true;
    }

    /**
     * 发送验证码
     *
     * @return
     */
    private boolean sendVerifyCode() {
        mUsername = etRegisterUsername.getText().toString().trim();
        if (TextUtils.isEmpty(mUsername)) {
            ToastUtil.show(getApplicationContext(), "手机号不能为空");
            return false;
        }
        Pattern accountPattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher = accountPattern.matcher(mUsername);
        if (!matcher.matches()) {
            ToastUtil.show(getApplicationContext(), "手机号不合法");
            return false;
        }
        //将验证码传到后台
        HashMap<String, Object> params = new HashMap<>();
        params.put(NetUrls.MOBILE, mUsername);
        final String requestUrl = NetUtil.createRequestUrl(NetUrls.HOST + NetUrls.SEND_CODE, params);
        Logger.d("发送验证码：" + requestUrl);
        mCountDownTimer.start();//验证成功开始计时
        x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    MessageBean sendCodeBean = JsonHelper.json2Bean(result, MessageBean.class);
                    if (sendCodeBean.getCode() == 200) {//发送验证成功
//                        ToastUtil.show(mContext, sendCodeBean.getMsg());
                        Logger.d("发送验证码成功："+sendCodeBean.getMsg());
//                        mCountDownTimer.start();//验证成功开始计时
                    } else {
                        ToastUtil.show(mContext, sendCodeBean.getMsg());//发送验证错误
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        return mIsCodeSended;
    }

    /**
     * 验证码验证
     *
     * @return
     */
    private void checkVerifyCode() {
        //发送到后台进行验证码验证
        HashMap<String, Object> params = new HashMap<>();
        params.put(NetUrls.MOBILE, mUsername);
        params.put(NetUrls.CODE, mVerifyCode);
        final String requestUrl = NetUtil.createRequestUrl(NetUrls.HOST + NetUrls.CHECK_CODE, params);
        Logger.d("验证码验证：" + requestUrl);
        //注册进度条
        mProgressDialog.show();
        x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    CheckCodeBean checkCodeBean = JsonHelper.json2Bean(result, CheckCodeBean.class);
                    if (checkCodeBean.getCode() == 200) {

                        register();//验证通过同时进行注册
                    } else {
                        ToastUtil.show(mContext, checkCodeBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mProgressDialog.dismiss();
                ToastUtil.show(mContext,"注册失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                mProgressDialog.dismiss();
                ToastUtil.show(mContext,"注册失败");
            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 进行注册
     */
    private void register() {
        if (!checkInfo()) return;
        HashMap<String, Object> params = new HashMap<>();
        params.put(NetUrls.USERNAME, mUsername);
        params.put(NetUrls.PWD, mPwd);
        final String requestUrl = NetUtil.createRequestUrl(NetUrls.HOST + NetUrls.REGISTER, params);
        Logger.d("注册：" + requestUrl);

        x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Logger.d(result);
                    UserInfoBean registerBean = JsonHelper.json2Bean(result, UserInfoBean.class);
                    if (registerBean.getCode() == 200) {
                        ToastUtil.show(mContext, registerBean.getMsg());
                        //成功后跳转页面
                        Logger.d("注册成功：" + registerBean.getMsg());
                        mIntent = new Intent(mContext, MainActivity.class);
                        mIntent.putExtra(Keys.REGISTER_BACK, Keys.REGISTER_BACK_INTENT);
                        SharedPreferencesUtil.saveString(mContext,Keys.USER_INFO,result);
                        startActivity(mIntent);
                        overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                    } else {
                        ToastUtil.show(mContext, registerBean.getMsg());
                    }

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mProgressDialog.dismiss();
                ToastUtil.show(mContext,"注册失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                mProgressDialog.dismiss();
                ToastUtil.show(mContext,"注册失败");
            }

            @Override
            public void onFinished() {
                mProgressDialog.dismiss();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsCodeSended = false;
    }
}
