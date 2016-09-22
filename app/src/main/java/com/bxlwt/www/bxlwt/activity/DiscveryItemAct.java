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
 * Created by zhengj on 2016/9/19.
 */
public class DiscveryItemAct extends BaseLoadAct{

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubTitle.setVisibility(View.INVISIBLE);
        mTitle.setText("发现");
        mSubIcon.setVisibility(View.INVISIBLE);
//        View view = View.inflate(mContext, R.layout.item_discovery, mContainer);
        View view = getLayoutInflater().inflate(R.layout.act_discovery_item, mContainer);
        mWebView =(WebView) view.findViewById(R.id.webview_discoverItem);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDefaultFontSize(18);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

    }



    @Override
    public void initData() {
        showLoadingView();
        Intent intent = getIntent();
        String id = intent.getStringExtra(Keys.DISCOVERY_DETAIL_HTML);
        //http://www.bxlwt.com/faxian-1.html
        if (id != null) {
            mWebView.loadUrl("http://www.bxlwt.com/faxian-" + id + ".html");
            showInitView();
        } else {
            showFailView();
        }
    }
}
