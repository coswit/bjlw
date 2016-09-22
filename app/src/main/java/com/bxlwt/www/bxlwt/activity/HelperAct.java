package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.base.BaseSetAct;

/**
 * Created by zhengj on 2016/8/29.
 */
public class HelperAct  extends BaseSetAct{

    private AlertDialog mAlertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("帮助中心");
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_helper, mContainer);
        Button btn = (Button) view.findViewById(R.id.btn_helper);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AndviceAct.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });


    }
}
