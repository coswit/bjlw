package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxlwt.www.bxlwt.MyApp;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.HomeListBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by zhengj on 2016/9/18.
 */
public class HomeClassfyAct extends BaseLoadAct {


    private RecyclerView mRecyclerView;
    private String mId;
    private List<HomeListBean.DataBean> mBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubTitle.setVisibility(View.INVISIBLE);
        mSubIcon.setVisibility(View.INVISIBLE);

        View view = getLayoutInflater().inflate(R.layout.act_home_classfy, mContainer);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_homeClassfy);

        showLoadingView();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Keys.HOME_GRID_LIST_ID);
        String title = intent.getStringExtra(Keys.HOME_GRID_LIST_TITLE);
        if (title != null && mId !=null) {
            mTitle.setText(title);
        }

    }

    @Override
    public void initData() {
        if (mId != null) {
            initListDatas(mId);

        }
    }

    private void initListDatas(String Id) {
        String requestUrl = NetUrls.HOST + NetUrls.HOME_GRID_LIST_CLASSFY;
        RequestParams params = new RequestParams(requestUrl);
        params.addBodyParameter(NetUrls.HOME_GRID_LIST_CLASSFY_ID,Id);
        params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
        Logger.d(params);
        x.http().get(params, new Callback.CacheCallback<String>() {
            private String result = null;

            @Override
            public boolean onCache(String result) {
                this.result = result;
                return true;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Logger.d(result);
                    this.result = result;
                    HomeListBean bean = JsonHelper.json2Bean(this.result, HomeListBean.class);
                    if (bean.getCode() == 200) {
                        mBean = bean.getData();
                        initRecyclerView();
                    } else if (bean.getCode()==102) {
                        showEmptyView();
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

    private void initRecyclerView() {
        if (mBean != null) {

            LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(new MyReclerViewAdapter());
            showInitView();
        }
    }

    class MyReclerViewAdapter extends RecyclerView.Adapter<MyReclerViewAdapter.MyReclerViewHolder> {

        private View itemView;



        @Override
        public MyReclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_pager, parent, false);


            MyReclerViewHolder holder = new MyReclerViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyReclerViewHolder holder, final int position) {
            holder.mConsumedNum.setText(mBean.get(position).getLnum() + "人领用");
            Glide.with(MyApp.getContext()).load(NetUrls.HOME_LIST_IMAGE + mBean.get(position).getImage()).into(holder.mImage);
            holder.mLeftNum.setText(mBean.get(position).getYnum() + "张");
            holder.mPrice.setText(mBean.get(position).getPrice());
            holder.mTitle.setText(mBean.get(position).getName());
            String type = mBean.get(position).getYouhui_type();
            if (TextUtils.equals(type, Keys.FREE_TYPE)) {
                holder.mType.setText("限时免费");
            } else if (TextUtils.equals(type, Keys.DISCOUNT_TYPE)) {
                holder.mType.setText("折扣");
            }else if (TextUtils.equals(type, Keys.PROMOTION_TYPE)) {
                holder.mType.setText("促销");
            }

            //条目点击跳转
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HomeItemAct.class);
                    intent.putExtra(Keys.HOME_LIST_ITEM_ID, mBean.get(position).getId());
                   HomeClassfyAct.this.startActivity(intent);
                    HomeClassfyAct.this.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
            });


        }


        @Override
        public int getItemCount() {
            return mBean.size();
        }

        class MyReclerViewHolder extends RecyclerView.ViewHolder {
            private final TextView mLeftNum;
            private ImageView mImage;
            private TextView mTitle;
            private  TextView mConsumedNum;
            private  TextView mPrice;
            private TextView mType;

            public MyReclerViewHolder(View itemView) {
                super(itemView);
                mTitle = (TextView) itemView.findViewById(R.id.tv_item_pager_title);
                mImage = (ImageView) itemView.findViewById(R.id.iv_homepager_item_img);
                mConsumedNum = (TextView) itemView.findViewById(R.id.tv_homepager_item_consumed);
                mLeftNum = (TextView) itemView.findViewById(R.id.tv_homepager_item_leftNum);
                mPrice = (TextView) itemView.findViewById(R.id.tv_homepager_item_price);
                mType = (TextView) itemView.findViewById(R.id.tv_homepager_item_type);
            }

        }

    }
}
