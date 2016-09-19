package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;

/**
 * Created by zhengj on 2016/9/18.
 */
public class HeadlinesContentAct extends BaseLoadAct {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubTitle.setVisibility(View.INVISIBLE);
        mSubIcon.setVisibility(View.INVISIBLE);
        mTitle.setText("百姓头条");

        View view = getLayoutInflater().inflate(R.layout.act_home_headlines_content, mContainer);
        mWebView = (WebView) view.findViewById(R.id.webview_headline);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDefaultFontSize(18);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        showLoadingView();
//        showInitView();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String html = intent.getStringExtra(Keys.HOME_HEADLINES_HTML);
        if (html != null) {
            mWebView.loadUrl(html);
            showInitView();
        }
    }
}
