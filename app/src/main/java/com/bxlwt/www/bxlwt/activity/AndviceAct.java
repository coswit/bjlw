package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.MessageBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhengj on 2016/9/21.
 */
public class AndviceAct extends BaseLoadAct {

    @BindView(R.id.et_actAndvice)
    EditText etActAndvice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("意见反馈");
        mSubIcon.setVisibility(View.INVISIBLE);
        mSubTitle.setVisibility(View.INVISIBLE);
        View view = getLayoutInflater().inflate(R.layout.act_andvice, mContainer);
        ButterKnife.bind(this, view);
        showInitView();
    }

    @Override
    public void initData() {


    }

    @OnClick(R.id.btn_andvice)
    public void onClick() {

        String content = etActAndvice.getText().toString().trim();
        String userId = getUserId();
        if (!TextUtils.isEmpty(content)) {
            if (userId != null) {
                showLoadingView();
                String requestUrl = NetUrls.HOST + NetUrls.ADVICE;
                RequestParams params = new RequestParams(requestUrl);
                params.addBodyParameter(NetUrls.USER_ID, userId);
                params.addBodyParameter(NetUrls.ADVICE_TITLE, content);
                params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
                Logger.d(params);
                x.http().get(params, new Callback.CacheCallback<String>() {

                    @Override
                    public boolean onCache(String result) {
                        return true;
                    }

                    @Override
                    public void onSuccess(String result) {
                        if (result != null) {
//                          Logger.d(result);
                            MessageBean bean = JsonHelper.json2Bean(result, MessageBean.class);
                            if (bean.getCode() == 200) {
                                showInitView();
                                ToastUtil.show(mContext,bean.getMsg());
                                finish();
                                overridePendingTransition(R.anim.out_enter_anim,R.anim.out_exit_anim);

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
            } else {
                startActivity(new Intent(mContext, LoginAct.class));
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        } else {
            ToastUtil.show(mContext, "内容不能为空");
        }
    }
}
