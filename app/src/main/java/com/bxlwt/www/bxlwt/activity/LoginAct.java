package com.bxlwt.www.bxlwt.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.bxlwt.www.bxlwt.wxapi.WXEntryActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bxlwt.www.bxlwt.MyApp.api;

/**
 * Created by zhengj on 2016/8/22.
 */
public class LoginAct extends AppCompatActivity implements IWXAPIEventHandler{


    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;

    private String mAccount;
    private String mPwd;
    private UserInfoBean mUserInfoBean;
    private Intent mIntent;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = MyApp.getContext();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在登录中");


    }


    @OnClick({R.id.ll_login_revert, R.id.btn_login_confirm, R.id.tv_loginAct_register, R.id.iv_login_wx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login_revert:
                finish();
                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                break;
            case R.id.btn_login_confirm://登录
                getInfo();
                if (validateInfo()) {
                    login();
                }
                break;
            case R.id.iv_login_wx://登录
                startActivity(new Intent(mContext, WXEntryActivity.class));
                if (!api.isWXAppInstalled()) {
                    ToastUtil.show(mContext, "您还未安装微信客户端");
                    return;
                }
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "bxlwt_wx_login";
                api.sendReq(req);
                api.handleIntent(getIntent(), this);//微信


                break;
            case R.id.tv_loginAct_register://跳转注册
                mIntent = new Intent(mContext, RegisterAct.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
        }
    }

    /**
     * 用户登录信息校验
     *
     * @return
     */
    public boolean validateInfo() {
        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.show(this, "用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mPwd)) {
            ToastUtil.show(this, "密码不能为空");
            return false;
        }
        Pattern accountPattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher = accountPattern.matcher(mAccount);
        if (!matcher.matches()) {
            ToastUtil.show(getApplicationContext(), "手机号不合法");
            return false;
        }
      /*  //密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
        Pattern pwdPattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher pwdMatcher = pwdPattern.matcher(mPwd);
        if (!pwdMatcher.matches()) {
            ToastUtil.show(getApplicationContext(), "手机号不合法");
            return false;
        }*/
        return true;
    }

    /**
     * 获取登录信息
     */
    public void getInfo() {
        mAccount = etLoginUsername.getText().toString().trim();
        mPwd = etLoginPwd.getText().toString().trim();
    }

    /**
     * 开始登录
     */
    public void login() {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("deviceToken", mDeviceId);
        final String requestUrl =NetUrls.HOST + NetUrls.LOGIN;
        RequestParams params = new RequestParams(requestUrl);
        params.addBodyParameter(NetUrls.PWD, mPwd);
        params.addBodyParameter(NetUrls.USERNAME, mAccount);

        Logger.d(requestUrl);
        //进度条显示
       mProgressDialog.show();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Logger.d(result);
                    mUserInfoBean = JsonHelper.json2Bean(result, UserInfoBean.class);
                    if (mUserInfoBean.getCode() == 200) {
                        ToastUtil.show(LoginAct.this, mUserInfoBean.getMsg());
                        Logger.d(mUserInfoBean.getData().getFace());
                        SharedPreferencesUtil.saveString(mContext, Keys.USER_INFO, result);
                        finish();
                        LoginAct.this.overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);

                    } else {
                        ToastUtil.show(LoginAct.this, mUserInfoBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.show(LoginAct.this, "网络连接失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                mProgressDialog.dismiss();
            }
        });


    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }


//    @Override
//    public void onReq(BaseReq baseReq) {
//        Logger.d("走了onreq");
//
//    }

    /**
     * 微信登录相关
     * @param baseResp
     */
  /*  @Override
    public void onResp(BaseResp baseResp) {
//        boolean b = api.handleIntent(getIntent(), this);
        Logger.d("走了返回方法:"+baseResp);
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtil.show(MyApp.getContext(),"微信登录失败");
                break;

            case BaseResp.ErrCode.ERR_OK:
                //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                String code = ((SendAuth.Resp) baseResp).code;
                Logger.d("code:"+code);
                String requestUrl = " //https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WX_APP_ID+
                        "&secret="+WX_APP_ID +"&code="+WX_SECRET +"&grant_type=authorization_code";
            *//*    RequestParams params = new RequestParams(requestUrl);
                params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
                Logger.d(params);
                x.http().get(params, new Callback.CacheCallback<String>() {

                    @Override
                    public boolean onCache(String result) {
                        return true;
                    }

                    @Override
                    public void onSuccess(String result) {
                        if (result != null) {
                            Logger.d(result);
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
                });*//*
                break;

        }
    }*/
}
