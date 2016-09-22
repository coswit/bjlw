package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.Utils.Utils;
import com.bxlwt.www.bxlwt.base.BaseSetAct;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhengj on 2016/8/29.
 */
public class SetAct extends BaseSetAct {

    @BindView(R.id.ll_setact_signOut)
    LinearLayout mSignOut;
    private Intent mIntent;


    private String mUserInfo;
    private AlertDialog mAlertDialog;

    private View mSignOutYes;
    private View mSignOutNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("设置");
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_set, mContainer);
        ButterKnife.bind(this, view);


        //退出确定对话框
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_sign_out, null);
        mSignOutYes = dialogView.findViewById(R.id.tv_dialog_signOut_yes);
        mSignOutNo = dialogView.findViewById(R.id.tv_dialog_signOut_no);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mAlertDialog = builder.setView(dialogView).create();
        mAlertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_signout));
        WindowManager.LayoutParams params = mAlertDialog.getWindow().getAttributes();
        params.height=(int) Utils.Dp2Pixel(130,mContext);
        params.width =(int) Utils.Dp2Pixel(330,mContext);

        mSignOutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
        mSignOutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SharedPreferencesUtil.saveString(mContext, Keys.USER_INFO, null);
                    mAlertDialog.dismiss();
                    finish();
                    overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
            }
        });
    }


    @OnClick({R.id.ll_setAct_userInfo, R.id.ll_setAct_clearCache, R.id.ll_setact_signOut,R.id.ll_setAct_aboutus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setAct_userInfo://个人资料
                if (mUserInfo != null) {
                    mIntent = new Intent(mContext, SetUserInfoAct.class);
                } else {
                    mIntent = new Intent(mContext,LoginAct.class);
                }
                startActivity(mIntent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.ll_setAct_clearCache:
                break;
            case R.id.ll_setAct_aboutus://关于我们
                mIntent = new Intent(mContext,AboutusAct.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.ll_setact_signOut://退出登录
                if (mUserInfo != null) {
                    mAlertDialog.show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //根据是否登录判断是否显示退出账号
        mUserInfo = SharedPreferencesUtil.getString(mContext, Keys.USER_INFO, null);
        if (mUserInfo != null) {
            mSignOut.setVisibility(View.VISIBLE);
        }else {
            mSignOut.setVisibility(View.INVISIBLE);
        }
    }
}
