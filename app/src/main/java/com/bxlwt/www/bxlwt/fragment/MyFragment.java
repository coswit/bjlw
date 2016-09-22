package com.bxlwt.www.bxlwt.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.activity.HelperAct;
import com.bxlwt.www.bxlwt.activity.LoginAct;
import com.bxlwt.www.bxlwt.activity.MainActivity;
import com.bxlwt.www.bxlwt.activity.MyCouponAct;
import com.bxlwt.www.bxlwt.activity.MyIntergrateAct;
import com.bxlwt.www.bxlwt.activity.MyMessage;
import com.bxlwt.www.bxlwt.activity.SetAct;
import com.bxlwt.www.bxlwt.activity.SetUserInfoAct;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhengj on 2016/8/8.
 */
public class MyFragment extends Fragment {


    @BindView(R.id.btn_my_login)
    Button mShowLogin;
    @BindView(R.id.ci_my_profile)
    CircleImageView mMyProfile;
    @BindView(R.id.tv_my_nickname)
    TextView tvMyNickname;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.ll_my_loginFinished)
    LinearLayout mShowLoginFinished;
    private Intent mIntent;
    private Context mContext;
    private FragmentActivity mActivity;
    private UserInfoBean mUserInfoBean;


/*
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        return view;
    }
*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        mContext = MyApp.getContext();
        mActivity = getActivity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //用户信息更新
        mUserInfoBean = ((MainActivity) getActivity()).getUserInfoBean();
        if (mUserInfoBean != null) {
            mShowLogin.setVisibility(View.INVISIBLE);
            mShowLoginFinished.setVisibility(View.VISIBLE);
            String nickname = mUserInfoBean.getData().getNickname();
            if (!TextUtils.isEmpty(nickname)) {
                tvMyNickname.setText(nickname);
            } else {
                tvMyNickname.setText(mUserInfoBean.getAccount());
            }
            String money = mUserInfoBean.getData().getMoney();
            String point = mUserInfoBean.getData().getPoint();
            if (!TextUtils.isEmpty(point) && !TextUtils.isEmpty(point)) {
                tvMyMoney.setText("积分\t" + point + "\t/\t消费额\t￥" + money);
            }
            String faceUrl = mUserInfoBean.getData().getFace();//头像加载
            if (!TextUtils.isEmpty(faceUrl)) {
                Glide.with(mContext).load(faceUrl).into(mMyProfile);
            }
        } else {
            mShowLogin.setVisibility(View.VISIBLE);
            mShowLoginFinished.setVisibility(View.INVISIBLE);
        }


    }


    @OnClick({R.id.btn_my_login, R.id.ci_my_profile, R.id.ll_my_loginFinished, R.id.ll_set_coupon, R.id.ll_my_message, R.id.ll_my_intergrate, R.id.ll_my_share, R.id.ll_my_help, R.id.ll_my_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_my_login://跳转登录
                mIntent = new Intent(mContext, LoginAct.class);
                startActivity(mIntent);
                mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.ci_my_profile://头像
                mIntent = new Intent(mContext, SetUserInfoAct.class);
                startActivity(mIntent);
                mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.ll_my_loginFinished:
                break;
            case R.id.ll_set_coupon://我的优惠
                if (mUserInfoBean != null) {
                    mIntent = new Intent(mContext, MyCouponAct.class);
                    startActivity(mIntent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                } else {
                    startActivity(new Intent(mContext, LoginAct.class));
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_my_message://我的消息
                if (mUserInfoBean != null) {
                    mIntent = new Intent(mContext, MyMessage.class);
                    startActivity(mIntent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }else {
                    startActivity(new Intent(mContext, LoginAct.class));
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_my_intergrate://我的积分
                if (mUserInfoBean != null) {
                    mIntent = new Intent(mContext, MyIntergrateAct.class);
                    startActivity(mIntent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                } else {
                    startActivity(new Intent(mContext, LoginAct.class));
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_my_share://分享有礼
                break;
            case R.id.ll_my_help://帮助中心
                mIntent = new Intent(mContext, HelperAct.class);
                startActivity(mIntent);
                mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.ll_my_set://设置
                mIntent = new Intent(mContext, SetAct.class);
                startActivity(mIntent);
                mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
        }
    }
}
