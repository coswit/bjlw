package com.bxlwt.www.bxlwt.wxapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.activity.LoginAct;
import com.bxlwt.www.bxlwt.ui.activity.MainActivity;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;
import com.bxlwt.www.bxlwt.bean.WXAcessTokenBean;
import com.bxlwt.www.bxlwt.bean.WxUserinfoBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.bxlwt.www.bxlwt.MyApp.WX_APP_ID;
import static com.bxlwt.www.bxlwt.MyApp.WX_SECRET;
import static com.bxlwt.www.bxlwt.MyApp.api;

/**
 * Created by zhengj on 2016/9/23.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    Context mContext = MyApp.getContext();
    private ProgressDialog mProgressDialog;
    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api.handleIntent(getIntent(), this);

        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Logger.d(baseReq);
        Logger.d("wx");

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Logger.d(baseResp.errCode);
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtil.show(MyApp.getContext(), "微信登录失败");
                startActivity(new Intent(mContext, LoginAct.class));
                overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
                break;

            case BaseResp.ErrCode.ERR_OK:
                //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                String code = ((SendAuth.Resp) baseResp).code;
                Logger.d("code:" + code);
                final String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WX_APP_ID +
                        "&secret=" + WX_SECRET + "&code=" + code + "&grant_type=authorization_code";
                RequestParams params = new RequestParams(requestUrl);
                params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
                Logger.d(params);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Logger.d(result);
                        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
                        //https://api.weixin.qq.com/sns/userinfo?access_token=oOKhr7x9ftYRzvRZj9iy2h3Wy2Exb7PAnyZ56VRAkZ6PajDJX5danK4JVnfmUCTJZgCL2bwZ34_DudO76qSAMxKPWFEaVBjFTLxSwjMaZqE&openid=oD8fLwBbrOxV08_hGQUExEGhZMsk
                        if (result != null) {
                            WXAcessTokenBean tokenBean = JsonHelper.json2Bean(result, WXAcessTokenBean.class);
                            if (tokenBean != null) {
                                wxTokenRequest(tokenBean.getAccess_token(), tokenBean.getOpenid());
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
                break;

        }


    }

    /**
     * 获取微信用户信息
     *
     * @param access_token
     * @param openid
     */
    private void wxTokenRequest(String access_token, String openid) {
        String requestUrl = NetUrls.WX_USER_INFO;
        RequestParams params = new RequestParams(requestUrl);
        params.addBodyParameter(NetUrls.WX_OPENID, openid);
        params.addBodyParameter(NetUrls.WX_TOKEN, access_token);
        Logger.d(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
//                    Logger.d(result);
                    WxUserinfoBean bean = JsonHelper.json2Bean(result, WxUserinfoBean.class);
                    if (bean != null) {
                        sendUserInfo(bean);
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
    }

    private void sendUserInfo(WxUserinfoBean bean) {
        mProgressDialog.show();
        String requestUrl = NetUrls.HOST + NetUrls.WX_LOGIN;
        RequestParams params = new RequestParams(requestUrl);
        //微信登录 http://app.bxlwt.com/api/index.php/user/login_open?opentype=wechat&usertype=app
        //              &openid=1&headimgurl=1&nickname=1&unionid=1&country=1&province=1&sex=1&from=1
        params.addBodyParameter("opentype", "wechat");
        params.addBodyParameter("usertype", "app");
        params.addBodyParameter("openid", bean.getOpenid());
        params.addBodyParameter("headimgurl", bean.getHeadimgurl());
        params.addBodyParameter("nickname", bean.getNickname());
        params.addBodyParameter("unionid", bean.getUnionid());
        params.addBodyParameter("country", bean.getCountry());
        params.addBodyParameter("province", bean.getProvince());
        params.addBodyParameter("sex", bean.getSex() + "");
        Logger.d(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Logger.d(result);
                    UserInfoBean userInfoBean = JsonHelper.json2Bean(result, UserInfoBean.class);
                    if (userInfoBean.getCode() == 200) {
//                        ToastUtil.show(mContext, userInfoBean.getMsg());
                        //成功后跳转页面
                        Logger.d("微信登录成功：" + userInfoBean.getMsg());
                        mProgressDialog.dismiss();
                        mIntent = new Intent(mContext, MainActivity.class);
                        mIntent.putExtra(Keys.REGISTER_BACK, Keys.REGISTER_BACK_INTENT);
                        SharedPreferencesUtil.saveString(mContext, Keys.USER_INFO, result);
                        startActivity(mIntent);
                        finish();
                        overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
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

    }


}
