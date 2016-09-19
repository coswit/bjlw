package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.base.BaseSetAct;

/**
 * Created by zhengj on 2016/8/29.
 */
public class SetEditPhone  extends BaseSetAct{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("绑定手机");
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_set_edit_phone, mContainer);
    }
}
