package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;

/**
 * Created by zhengj on 2016/9/21.
 */
public class AboutusAct extends BaseLoadAct {
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubTitle.setVisibility(View.INVISIBLE);
        mTitle.setText("关于我们");
        mSubIcon.setVisibility(View.INVISIBLE);
//        View view = View.inflate(mContext, R.layout.item_discovery, mContainer);
        View view = getLayoutInflater().inflate(R.layout.act_discovery_item, mContainer);
        mWebView = (WebView) view.findViewById(R.id.webview_discoverItem);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDefaultFontSize(18);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

    }


    @Override
    public void initData() {
        showLoadingView();
        mWebView.loadUrl("http://www.bxlwt.com/about-5281-0-2.html");
       showInitView();

    }
}
