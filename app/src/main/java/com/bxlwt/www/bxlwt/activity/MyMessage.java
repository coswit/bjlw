package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bxlwt.www.bxlwt.base.BaseSetAct;

/**
 * Created by zhengj on 2016/8/29.
 */
public class MyMessage  extends BaseSetAct{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("我的消息");
    }
}
