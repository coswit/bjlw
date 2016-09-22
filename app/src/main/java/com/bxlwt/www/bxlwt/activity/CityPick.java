package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.OpencityBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhengj on 2016/9/20.
 */
public class CityPick extends BaseLoadAct {


    @BindView(R.id.tag_cityPick)
    TagFlowLayout mFlowLayout;

    private List<OpencityBean.DataBean> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubIcon.setVisibility(View.INVISIBLE);
        mSubTitle.setVisibility(View.INVISIBLE);
        mTitle.setText("选择城市");
        View view = getLayoutInflater().inflate(R.layout.act_city, mContainer);
        ButterKnife.bind(this, view);

        mRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });
    }

    @Override
    public void initData() {
        showLoadingView();
        initOpenCityData();

    }

    private void initOpenCityData() {

        String requestUrl = NetUrls.HOST + NetUrls.OPNE_CITY;
        RequestParams params = new RequestParams(requestUrl);
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
//                    Logger.d(result);
                    OpencityBean bean = JsonHelper.json2Bean(result, OpencityBean.class);
                    if (bean.getCode() == 200) {
                        mDatas = bean.getData();
                        if (mDatas != null) {
                            //添加城市
                            mFlowLayout.setAdapter(new TagAdapter<OpencityBean.DataBean>(mDatas) {
                                @Override
                                public View getView(FlowLayout parent, int position, final OpencityBean.DataBean object) {
                                    TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_opencity, mFlowLayout, false);
                                    tv.setText(object.getName());
                                    tv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(mContext, MainActivity.class);
//                                            intent.putExtra(Keys.CITY_PICK,object.getName().toString());
                                            SharedPreferencesUtil.saveString(mContext, Keys.CITY_PICK,object.getName());
                                            CityPick.this.startActivity(intent);
                                            CityPick.this.overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
                                        }
                                    });
                                    return tv;
                                }
                            });
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
        });


    }

    private void initCityData() {

    }

}
