package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.SearchKeywordBean;
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
import butterknife.OnClick;

/**
 * Created by zhengj on 2016/8/26.
 */
public class SearchAct extends BaseLoadAct {


    @BindView(R.id.iv_search_revert)
    ImageView ivSearchRevert;
    @BindView(R.id.tag_searchAct)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.et_actSearch)
    EditText etSearch;

    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopBanner.setVisibility(View.GONE);
        mInflater = getLayoutInflater();
        View view = mInflater.inflate(R.layout.activity_search, mContainer);
        ButterKnife.bind(this, view);
        showInitView();


//        if (mDatas != null) {
//            mFlowLayout.setAdapter(new TagAdapter<String>(mDatas) {
//                @Override
//                public View getView(FlowLayout parent, int position, String  object) {
//                    TextView tv= (TextView) mInflater.inflate(R.layout.item_search, mFlowLayout,false);
//                    tv.setText(object);
//                    return tv;
//                }
//            });
//        }

    }

    @Override
    public void initData() {
        showLoadingView();
        initKeywordDatas();

    }

    private void initKeywordDatas() {

        String requestUrl = NetUrls.HOST + NetUrls.SEARCH_KEYWORD_LIST;
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
                    SearchKeywordBean bean = JsonHelper.json2Bean(result, SearchKeywordBean.class);
                    if (bean.getCode() == 200) {
                        List<SearchKeywordBean.DataBean> keyWordDatas = bean.getData();
                        if (keyWordDatas != null) {

                            mFlowLayout.setAdapter(new TagAdapter<SearchKeywordBean.DataBean>(keyWordDatas) {
                                @Override
                                public View getView(FlowLayout parent, int position, final SearchKeywordBean.DataBean dataBean) {
                                    TextView tv = (TextView) mInflater.inflate(R.layout.item_search, mFlowLayout, false);
                                    tv.setText(dataBean.getKeywords());
                                    tv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(mContext, SearchKeywordAct.class);
                                            intent.putExtra(Keys.SEARCH_KEYWORD, dataBean.getKeywords());
                                            startActivity(intent);
                                            SearchAct.this.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
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

    @OnClick({R.id.iv_search_revert,R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_revert:
                finish();
                overridePendingTransition(R.anim.out_enter_anim, R.anim.out_exit_anim);
                break;
            case R.id.btn_search:
                String keyword = etSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(keyword)) {
                    Logger.d(keyword);
                    Intent intent = new Intent(mContext, SearchKeywordAct.class);
                    intent.putExtra(Keys.SEARCH_KEYWORD, keyword);
                    startActivity(intent);
                    SearchAct.this.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                } else {
                    ToastUtil.show(mContext,"请输入要搜索内容");
                }
                break;
        }
    }




}
