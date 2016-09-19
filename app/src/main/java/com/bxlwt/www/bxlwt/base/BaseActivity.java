package com.bxlwt.www.bxlwt.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;

/**
 * Created by zhengj on 2016/9/1.
 */
public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private String mUserId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MyApp.getContext();
    }
    public UserInfoBean getUserInfoBean() {
        String info = SharedPreferencesUtil.getString(mContext, Keys.USER_INFO, null);
        if (info != null) {
            UserInfoBean userInfoBean = JsonHelper.json2Bean(info, UserInfoBean.class);
            mUserId =userInfoBean.getUser_id();
            return userInfoBean;
        }
        return null;
    }

    public String getUserId() {
        UserInfoBean bean = getUserInfoBean();
        if (bean != null) {
            mUserId=bean.getUser_id();
        }
        return mUserId;
    }

    public String updateUserInfo() {

        return null;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
