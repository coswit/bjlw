package com.bxlwt.www.bxlwt.base;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.activity.SearchAct;


public abstract class BaseFragment extends Fragment {
    public FrameLayout mContainer;
    private View mView;
    public Context mContext;
    public FragmentActivity mActivity;
    public TextView mTitle;
    public LinearLayout mLoading;
    public LinearLayout mFailLoading;


    /**
     * 嵌入fragment中的view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = MyApp.getContext();
        mActivity = getActivity();
        View view = View.inflate(mContext, R.layout.fragment_base, null);
        mContainer = (FrameLayout) view.findViewById(R.id.fragment_base_container);
        mTitle = (TextView) view.findViewById(R.id.tv_baseFragment_title);
        mLoading = (LinearLayout) view.findViewById(R.id.ll_base_loading);
        mFailLoading = (LinearLayout) view.findViewById(R.id.ll_base_fail);

        //加载失败后重新加载数据
        mFailLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        ImageView search = (ImageView) view.findViewById(R.id.iv_base_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchAct.class);
                startActivity(intent);
                mActivity.overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);

            }
        });
        mView = initView();//fragment自身要展示的view
        mContainer.addView(mView);
        return view;
    }

    protected abstract View initView();

    public void  requestAsyncTask(){
        new AsyncTask<Void, Void, Object>() {
            @Override
            protected Object doInBackground(Void... params) {
                return BaseFragment.this.doInBackground();
            }

            @Override
            protected void onPostExecute(Object o) {
                BaseFragment.this.onPostExecute(o);
            }

        }.execute();
    }


    protected abstract Object doInBackground();

    protected abstract void onPostExecute(Object object);
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    public void showLoadingView() {
        mLoading.setVisibility(View.VISIBLE);
        mContainer.setVisibility(View.INVISIBLE);
        mFailLoading.setVisibility(View.INVISIBLE);
    }

    public void showFailView() {
        mLoading.setVisibility(View.INVISIBLE);
        mContainer.setVisibility(View.INVISIBLE);
        mFailLoading.setVisibility(View.VISIBLE);
    }

    public void showInitView() {
        mLoading.setVisibility(View.INVISIBLE);
        mContainer.setVisibility(View.VISIBLE);
        mFailLoading.setVisibility(View.INVISIBLE);
    }

    public abstract void initData();

}
