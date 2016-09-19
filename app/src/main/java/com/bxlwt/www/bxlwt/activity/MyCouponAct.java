package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.base.BasePager;
import com.bxlwt.www.bxlwt.bean.MyCouponBean;
import com.bxlwt.www.bxlwt.dao.PagerTabAdapter;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.bxlwt.www.bxlwt.pager.MyCouponEmptyPager;
import com.bxlwt.www.bxlwt.pager.MyCouponExpiredPager;
import com.bxlwt.www.bxlwt.pager.MyCouponPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCouponAct extends BaseLoadAct {

    @BindView(R.id.tab_mycoupon)
    SmartTabLayout mTabLayout;
    @BindView(R.id.vp_mycoupon)
    ViewPager mViewPager;
    ArrayList<BasePager> mPagers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.act_mycoupon, mContainer);
        mTitle.setText("我的优惠");
        mSubIcon.setVisibility(View.GONE);
        mSubTitle.setText("使用规则");
        mSubTitle.setTextSize(11);
        ButterKnife.bind(this, view);
        mPagers = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void initData() {
        String userId = getUserId();
        if (userId != null) {
            showLoadingView();
            netCouponRequest(userId);
        }
    }

    /**
     * 未过期优惠券网络请求
     *
     * @param userId
     */
    private void netCouponRequest(final String userId) {
        final String requestUrl = NetUrls.HOST + NetUrls.MYCOUPON;
        RequestParams params = new RequestParams(requestUrl);
        params.addBodyParameter(NetUrls.USER_ID, userId);
        params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
        Logger.d(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {

                    MyCouponBean myCouponBean = JsonHelper.json2Bean(result, MyCouponBean.class);
                    if (myCouponBean != null) {
                        if (myCouponBean.getCode() == 200) {
                            List<MyCouponBean.DataBean> datas = myCouponBean.getData();
                            netExpiredCouponRequest(userId, datas);//成功后进行下次请求
                        }
                        if (myCouponBean.getCode() == 102) {
                            initTab(null, null);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showFailView();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                showFailView();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 过期优惠券网络请求
     *
     * @param userId
     * @param datas
     */
    private void netExpiredCouponRequest(String userId, final List<MyCouponBean.DataBean> datas) {
        String requestUrl = NetUrls.HOST + NetUrls.MYCOUPON_EXPIRED;
        RequestParams params = new RequestParams(requestUrl);
        params.addBodyParameter(NetUrls.USER_ID, userId);
        params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
        Logger.d(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    MyCouponBean myCouponBean = JsonHelper.json2Bean(result, MyCouponBean.class);
                    if (myCouponBean != null) {
                        if (myCouponBean.getCode() == 200) {
                            List<MyCouponBean.DataBean> outDatas = myCouponBean.getData();
                            initTab(datas, outDatas);
                        }
                        if (myCouponBean.getCode() == 102) {

                            initTab(datas, null);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showFailView();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                showFailView();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initTab(List datas, List<MyCouponBean.DataBean> outDatas) {
        if (mPagers != null) {

            String[] titles = {"可使用", "已过期"};
            if (datas == null && outDatas == null) {
                mPagers.clear();
                mPagers.add(new MyCouponEmptyPager(mContext));
                mPagers.add(new MyCouponEmptyPager(mContext));
            }
            if (datas != null && outDatas == null) {
                mPagers.clear();
                mPagers.add(new MyCouponPager(mContext, datas));
                mPagers.add(new MyCouponEmptyPager(mContext));
            }
            if (datas != null && outDatas != null) {
                mPagers.clear();
                mPagers.add(new MyCouponPager(mContext, datas));
                mPagers.add(new MyCouponExpiredPager(mContext, outDatas));
            }
            mViewPager.setAdapter(new PagerTabAdapter(mPagers, titles));
            mTabLayout.setViewPager(mViewPager);
            showInitView();
        }

    }


}
