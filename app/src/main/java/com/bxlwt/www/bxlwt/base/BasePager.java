package com.bxlwt.www.bxlwt.base;

import android.content.Context;
import android.view.View;

import com.bxlwt.www.bxlwt.R;

import java.util.List;


public abstract class BasePager {

    public   Context mContext;
    public View mView ;
    public List mDatas;

    public BasePager(Context context) {

        mContext = context;
        mView = initView();
    }

    public BasePager(Context context, List result) {
        mContext = context;
        mDatas =result;
        if (mDatas!=null) {
            mView = initView();
        }else {
            mView=View.inflate(mContext, R.layout.view_fail, null);
        }
    }


    public abstract View  initView();
}
