package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.Utils.Utils;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.HomeItemDetailBean;
import com.bxlwt.www.bxlwt.bean.MessageBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeItemAct extends BaseLoadAct {

    @BindView(R.id.tv_homeListDetail_price)
    TextView mPrice;
    @BindView(R.id.tv_homeListDetail_date)
    TextView mDate;
    @BindView(R.id.tv_homeListDetail_shopName)
    TextView mShopName;
    @BindView(R.id.tv_homeListDetail_address)
    TextView mAddress;
    @BindView(R.id.tv_homeListDetail_phoneNum)
    TextView mPhoneNum;
    @BindView(R.id.tv_homeItemDetail_title)
    TextView mDetailTitle;
    @BindView(R.id.tv_homeItemDetail_description)
    TextView mDescription;
    @BindView(R.id.tv_homeItemDetail_beget)
    TextView mlBeget;
    @BindView(R.id.tv_homeItemDetail_left)
    TextView mLeft;
    @BindView(R.id.iv_homeItemDetail_icon)
    ImageView mDetailIcon;
    @BindView(R.id.iv_homeItemDetail_image)
    ImageView mDetailImage;
    @BindView(R.id.iv_homeItemDetail_topIcon)
    ImageView mTopIcon;
    @BindView(R.id.btn_homeItemDetail_tip)
    Button btnHomeItemDetailTip;
    private String mItemId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.act_item_home, null);
        mContainer.addView(view);
        mTitle.setText("优惠详情");
        mSubIcon.setImageResource(R.drawable.share_nextpager);

        mSubTitle.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mContainer.setVisibility(View.INVISIBLE);
        mFailView.setVisibility(View.INVISIBLE);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initData() {
        showLoadingView();
        loadingData();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void loadingData() {
        Intent intent = getIntent();
        mItemId = intent.getStringExtra(Keys.HOME_LIST_ITEM_ID);
        if (mItemId != null) {
            //根据条目id显示相应详情
            String requestUrl = NetUrls.HOST + NetUrls.HOME_LIST_ITEM_DETAIL;
            RequestParams params = new RequestParams(requestUrl);
            params.setConnectTimeout(1000);
            params.addBodyParameter(NetUrls.HOME_LIST_ITEM_ID, mItemId);
            Logger.d("首页跳转后的详情页：" + params);
            params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
            x.http().get(params, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        HomeItemDetailBean bean = JsonHelper.json2Bean(result, HomeItemDetailBean.class);
                        if (bean != null) {
                            if (bean.getCode() == 200) {
                                setInfo(bean);
                                showInitView();
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

                @Override
                public boolean onCache(String result) {
                    return false;
                }
            });


        }
    }

    /**
     * 将获取到的数据设置到页面中
     *
     * @param bean
     */
    private void setInfo(HomeItemDetailBean bean) {
        mPrice.setText(bean.getData().getPrice());
        mDate.setText("有效期：" + bean.getData().getBegin_time() + " 至 " + bean.getData().getEnd_time());
        mShopName.setText(bean.getData().getPname());
        mAddress.setText(bean.getData().getAddress());
        mPhoneNum.setText(bean.getData().getUser_tel());
        mDescription.setText(bean.getData().getDescription());
        mDetailTitle.setText(bean.getData().getName());
        mlBeget.setText(bean.getData().getLnum() + "张");
        mLeft.setText(bean.getData().getYnum() + "张");
        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + bean.getData().getIcon()).into(mDetailIcon);
        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + bean.getData().getImage()).into(mTopIcon);
        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + bean.getData().getImage()).into(mDetailImage);
    }

    @OnClick({R.id.btn_homeItemDetail_tip, R.id.ll_homeItemDetail_diaNum})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_homeItemDetail_diaNum://拨打电话
                CharSequence phoneNum = mPhoneNum.getText();
                Uri uri = Uri.parse("tel:" + phoneNum);
                startActivity(new Intent(Intent.ACTION_CALL, uri));

                break;

            case R.id.btn_homeItemDetail_tip://领取优惠券
                String userId = getUserId();
                if (mItemId != null && userId != null) {
                    String requestUrl = NetUrls.HOST + NetUrls.HOME_LIST_ITEM_GET_COUPON;
                    RequestParams params = new RequestParams(requestUrl);
                    params.addBodyParameter(NetUrls.HOME_LIST_ITEM_ID, mItemId);
                    params.addBodyParameter(NetUrls.USER_ID, userId);
                    Logger.d(params);

                    //领取优惠券要弹出的对话框
                    View dialogView = getLayoutInflater().inflate(R.layout.dialog_home_item_coupon, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    final AlertDialog alertDialog = builder.setView(dialogView).create();
                    alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_coupon));
                    WindowManager.LayoutParams dialogParams = alertDialog.getWindow().getAttributes();
                    dialogParams.height = (int) Utils.Dp2Pixel(160, mContext);
                    dialogParams.width = (int) Utils.Dp2Pixel(320, mContext);
                    //dialogView初始化
                    final LinearLayout dialogLoading = (LinearLayout) dialogView.findViewById(R.id.ll_homeItemDetail_dialog_loading);
                    final LinearLayout dialogSuccess = (LinearLayout) dialogView.findViewById(R.id.ll_homeItemDetail_dialog_success);
                    final ImageView dialogFailCoupoun = (ImageView) dialogView.findViewById(R.id.iv_homeItemDetail_dialog_fail);
                    final TextView dialogBackHome = (TextView) dialogView.findViewById(R.id.tv_homeItemDetail_dialog_backHome);
                    final TextView dialogGoCoupon = (TextView) dialogView.findViewById(R.id.tv_homeItemDetail_dialog_goCoupon);
                    final TextView dialogHasCoupon = (TextView) dialogView.findViewById(R.id.tv_homeItemDetail_dialog_success);


                    dialogLoading.setVisibility(View.VISIBLE);
                    dialogSuccess.setVisibility(View.INVISIBLE);
                    dialogFailCoupoun.setVisibility(View.INVISIBLE);
                    alertDialog.show();

                    final Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (result != null) {
                                Logger.d(result);
                                MessageBean bean = JsonHelper.json2Bean(result, MessageBean.class);
                                if (bean != null) {
                                    if (bean.getCode() == 200) {
                                        dialogLoading.setVisibility(View.INVISIBLE);
                                        dialogSuccess.setVisibility(View.VISIBLE);
                                        dialogFailCoupoun.setVisibility(View.INVISIBLE);
                                        dialogHasCoupon.setText("领取成功");
                                        //跳转主页
                                        dialogBackHome.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog.dismiss();
                                                Intent intent = new Intent(mContext, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                                            }
                                        });
                                        //跳转优惠券
                                        dialogGoCoupon.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog.dismiss();
                                                Intent intent = new Intent(mContext, MyCouponAct.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                                            }
                                        });

                                    } else if (bean.getCode() == 108) {
                                        dialogLoading.setVisibility(View.INVISIBLE);
                                        dialogSuccess.setVisibility(View.INVISIBLE);
                                        dialogFailCoupoun.setVisibility(View.VISIBLE);
                                    } else if (bean.getCode() == 109) {
                                        dialogLoading.setVisibility(View.INVISIBLE);
                                        dialogSuccess.setVisibility(View.VISIBLE);
                                        dialogFailCoupoun.setVisibility(View.INVISIBLE);
                                        dialogHasCoupon.setText("您已经领过了此优惠券");
                                        //跳转主页
                                        dialogBackHome.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog.dismiss();
                                                Intent intent = new Intent(mContext, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                                            }
                                        });
                                        //跳转优惠券
                                        dialogGoCoupon.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog.dismiss();
                                                Intent intent = new Intent(mContext, MyCouponAct.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                                            }
                                        });
                                    } else {
                                        alertDialog.dismiss();
                                        ToastUtil.show(mContext, bean.getMsg());
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            alertDialog.dismiss();
                            ToastUtil.show(mContext, "领取失败");
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {
                            alertDialog.dismiss();
                            ToastUtil.show(mContext, "领取失败");
                        }

                        @Override
                        public void onFinished() {

                        }

                        @Override
                        public boolean onCache(String result) {
                            return false;
                        }
                    });

                    //关闭弹出框
                    final ImageView dialogClose = (ImageView) dialogView.findViewById(R.id.iv_homeItemDetail_dialog_close);
                    dialogClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancelable.cancel();
                            alertDialog.dismiss();
                        }
                    });
                } else if (userId == null) {//未登录状态
                    startActivity(new Intent(mContext, LoginAct.class));
                    overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }

                break;
        }
    }


}
