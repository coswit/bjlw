package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhengj on 2016/8/26.
 */
public class SearchAct extends AppCompatActivity {

    @BindView(R.id.sv_actSearch)
    SearchView mSearchView;
    @BindView(R.id.iv_search_revert)
    ImageView ivSearchRevert;
    @BindView(R.id.tag_searchAct)
    TagFlowLayout mFlowLayout;
    ArrayList<String> mDatas = new ArrayList<>();
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mInflater = getLayoutInflater();
        ButterKnife.bind(this);
        mSearchView.setQueryHint("请输入商品关键词");


        mDatas.add("优惠券");
        mDatas.add("手机端特价啊");
        mDatas.add("白菜价");
        mDatas.add("风扇");
        mDatas.add("白菜价");
        mDatas.add("风扇");
        mDatas.add("优惠券");
        mDatas.add("手机端特价");
        mDatas.add("优惠券");
        mDatas.add("手机端特价");
        mDatas.add("手机端特价");


        if (mDatas != null) {
            mFlowLayout.setAdapter(new TagAdapter<String>(mDatas) {
                @Override
                public View getView(FlowLayout parent, int position, String  object) {
                    TextView tv= (TextView) mInflater.inflate(R.layout.item_search, mFlowLayout,false);
                    tv.setText(object);
                    return tv;
                }
            });
        }

    }

    @OnClick({R.id.iv_search_revert})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_revert:
                finish();
                overridePendingTransition(R.anim.out_enter_anim,R.anim.out_exit_anim);
                break;

        }
    }
}
