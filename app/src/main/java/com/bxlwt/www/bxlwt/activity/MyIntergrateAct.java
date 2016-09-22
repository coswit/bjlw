package com.bxlwt.www.bxlwt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.IntergrateBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by zhengj on 2016/8/29.
 */
public class MyIntergrateAct extends BaseLoadAct{

    private RecyclerView mRecyclerView;
    private List<IntergrateBean.DataBean> mDatas;
    private TextView mTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubIcon.setVisibility(View.INVISIBLE);
        mSubTitle.setVisibility(View.INVISIBLE);
        mTitle.setText("我的积分");
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_intergrate, mContainer);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_intergrate);
        mTotal = (TextView) findViewById(R.id.tv_intergrateAct_total);
        showLoadingView();
    }

    @Override
    public void initData() {
        String userId = getUserId();
        if (userId != null) {
            String requestUrl = NetUrls.HOST + NetUrls.INTERGRATE;
            RequestParams params = new RequestParams(requestUrl);
            params.addBodyParameter(NetUrls.USER_ID,userId);
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
                        IntergrateBean bean = JsonHelper.json2Bean(result, IntergrateBean.class);
                        if (bean.getCode() == 200) {
                            mDatas = bean.getData();
                            mTotal.setText(bean.getZjf());
                            initRecyclerView();

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
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new MyReclerViewAdapter());
        showInitView();


    }

    class MyReclerViewAdapter extends RecyclerView.Adapter<MyReclerViewAdapter.MyReclerViewHolder> {

        private View itemView;

        @Override
        public MyReclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_intergrate, parent, false);
            MyReclerViewHolder holder = new MyReclerViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyReclerViewHolder holder, final int position) {
            if (mDatas != null) {
                holder.mType.setText(mDatas.get(position).getType());
                holder.mReason.setText(mDatas.get(position).getReason());
                holder.mScore.setText("+"+mDatas.get(position).getPoint());
            }

        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyReclerViewHolder extends RecyclerView.ViewHolder {

            private TextView mType;
            private TextView mReason;
            private TextView mScore;


            public MyReclerViewHolder(View itemView) {
                super(itemView);

                mType = (TextView) itemView.findViewById(R.id.tv_item_intergrate_type);
                mReason = (TextView) itemView.findViewById(R.id.tv_item_intergrate_reason);
                mScore = (TextView) itemView.findViewById(R.id.tv_item_intergrate_score);
            }

        }

    }
}
