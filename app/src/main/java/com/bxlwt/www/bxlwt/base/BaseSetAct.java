package com.bxlwt.www.bxlwt.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;

/**
 * Created by zhengj on 2016/8/29.
 */
public class BaseSetAct extends BaseActivity {

    public LinearLayout mRevert;
    public LinearLayout mContainer;
    public TextView mTitle;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_set);

        mRevert = (LinearLayout) findViewById(R.id.ll_baseAct_revert);
        mContainer = (LinearLayout) findViewById(R.id.ll_base_container);
        mTitle = (TextView) findViewById(R.id.tv_base_title);

        mRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_enter_anim,R.anim.out_exit_anim);
            }
        });
    }




}
