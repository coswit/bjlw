package com.bxlwt.www.bxlwt.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.NetUtil;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.base.BaseSetAct;
import com.bxlwt.www.bxlwt.bean.MessageBean;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhengj on 2016/8/29.
 */
public class SetEditNickname extends BaseSetAct {

    @BindView(R.id.et_edit_nickname)
    EditText etEditNickname;
    private ProgressDialog mProgressDialog;
    private UserInfoBean mUserInfoBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("设置昵称");
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_set_edit_nickname, mContainer);
        ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在修改昵称中");



    }

    @OnClick({R.id.iv_eidt_clearNickname, R.id.btn_edit_submitNickname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_eidt_clearNickname:
                break;
            case R.id.btn_edit_submitNickname:
                submitEditNickname();

                break;
        }

    }

    private void submitEditNickname() {


//        String nickName = etEditNickname.getText().toString().trim();
        String nickName = null;
        try {
            nickName = URLEncoder.encode(etEditNickname.getText().toString().trim(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(nickName)) {
            ToastUtil.show(getApplicationContext(), "用户昵称不能为空");
            return;
        }
        Pattern pattern = Pattern.compile("^.{2,12}$");
        Matcher matcher = pattern.matcher(etEditNickname.getText().toString().trim());
        if (!matcher.matches()) {
            ToastUtil.show(getApplicationContext(), "用户昵称不合法");
            return;
        }
        if (!NetUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网路是否连接");
            return;
        }
        mUserInfoBean = getUserInfoBean();
        if (mUserInfoBean != null) {
            HashMap<String, Object> params = new HashMap<>();
            params.put(NetUrls.USER_ID, mUserInfoBean.getData().getUserid());
            params.put(NetUrls.NICKNAME, nickName);

            final String requestUrl = NetUtil.createRequestUrl(NetUrls.HOST + NetUrls.UPDATE_USERINFO, params);
            Logger.d("修改用户昵称：" + requestUrl);
            //修改用户进度条
            mProgressDialog.show();
            x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        MessageBean bean = JsonHelper.json2Bean(result, MessageBean.class);
                        if (bean.getCode() == 200) {
                            ToastUtil.show(mContext, bean.getMsg());
                            getUserInfo();
                        } else {
                            mProgressDialog.dismiss();
                            ToastUtil.show(mContext, bean.getMsg());
                        }

                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                    ToastUtil.show(mContext, "请检查网路是否连接");
                    mProgressDialog.dismiss();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    mProgressDialog.dismiss();
                }

                @Override
                public void onFinished() {

                }
            });
        }
    }


    private void getUserInfo() {
        if (mUserInfoBean != null) {
            final String requestUrl = NetUrls.HOST + NetUrls.GET_USERINFO + "?" + NetUrls.USER_ID + "=" + mUserInfoBean.getUser_id();
            Logger.d("获取用户信息：" + requestUrl);
            x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Logger.d(result);
                        UserInfoBean userInfoBean = JsonHelper.json2Bean(result, UserInfoBean.class);
                        Logger.d("用户信息获取" + userInfoBean.getMsg());
                        if (userInfoBean.getCode() == 200) {
                            SharedPreferencesUtil.saveString(mContext, Keys.USER_INFO, result);
                            //成功后跳转页面
                            SetEditNickname.this.finish();
                            SetEditNickname.this.overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                        }
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                    ToastUtil.show(mContext, "请检查网路是否连接");
                    mProgressDialog.dismiss();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    mProgressDialog.dismiss();
                }

                @Override
                public void onFinished() {
                    mProgressDialog.dismiss();
                }
            });
        }
    }



}
