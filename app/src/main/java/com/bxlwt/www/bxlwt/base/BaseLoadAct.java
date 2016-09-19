package com.bxlwt.www.bxlwt.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;

/**
 * Created by zhengj on 2016/9/12.
 */
public abstract class BaseLoadAct extends BaseActivity {

    public LinearLayout mRevert;
    public FrameLayout mContainer;
    public LinearLayout mFailView;
    public LinearLayout mLoadingView;
    public TextView mTitle;
    public TextView mSubTitle;
    public TextView mEmptyView;
    public ImageView mSubIcon;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_load);
        mRevert = (LinearLayout) findViewById(R.id.ll_baseLoad_revert);
        mContainer = (FrameLayout) findViewById(R.id.ll_baseLoad_container);
        mFailView = (LinearLayout) findViewById(R.id.ll_baseLoad_fail);
        mLoadingView = (LinearLayout) findViewById(R.id.ll_baseLoad_loading);
        mTitle = (TextView) findViewById(R.id.tv_baseLoad_title);
        mSubTitle = (TextView) findViewById(R.id.tv_baseLoad_subTitle);
        mSubIcon = (ImageView) findViewById(R.id.iv_baseLoad_subIcon);
        mEmptyView = (TextView) findViewById(R.id.tv_baseLoad_empty);

        mContext = MyApp.getContext();

        mRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
            }
        });
        mFailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
        mContainer.setVisibility(View.INVISIBLE);
        mFailView.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.INVISIBLE);

    }

    public void showFailView() {
        mLoadingView.setVisibility(View.INVISIBLE);
        mContainer.setVisibility(View.INVISIBLE);
        mFailView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.INVISIBLE);
    }

    public void showInitView() {
        mLoadingView.setVisibility(View.INVISIBLE);
        mContainer.setVisibility(View.VISIBLE);
        mFailView.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.INVISIBLE);
    }

    public void showEmptyView() {
        mLoadingView.setVisibility(View.INVISIBLE);
        mContainer.setVisibility(View.INVISIBLE);
        mFailView.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.VISIBLE);
    }
    public abstract void initData();
}
