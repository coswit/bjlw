package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.base.BaseSetAct;

/**
 * Created by zhengj on 2016/8/29.
 */
public class HelperAct  extends BaseSetAct{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("帮助中心");
        LayoutInflater.from(mContext).inflate(R.layout.activity_helper,mContainer);
    }
}
