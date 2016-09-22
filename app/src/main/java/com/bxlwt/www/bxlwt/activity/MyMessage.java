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
import com.bxlwt.www.bxlwt.bean.MymessageBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


public class MyMessage  extends BaseLoadAct{
    private RecyclerView mRecyclerView;
    private List<MymessageBean.DataBean.ListBean> mDatas;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubIcon.setVisibility(View.INVISIBLE);
        mSubTitle.setVisibility(View.INVISIBLE);
        mTitle.setText("我的消息");
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_message, mContainer);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_message);

        showLoadingView();
    }

    @Override
    public void initData() {
        String userId = getUserId();
        if (userId != null) {
            String requestUrl = NetUrls.HOST + NetUrls.MESSAGE;
            RequestParams params = new RequestParams(requestUrl);
            params.addBodyParameter("touserid",userId);
            params.addBodyParameter("type","系统消息");
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
                    Logger.d(result);
                        MymessageBean bean = JsonHelper.json2Bean(result, MymessageBean.class);
                        if (bean.getCode() == 200) {
                            mDatas = bean.getData().getList();
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
        showInitView();
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
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
            MyReclerViewHolder holder = new MyReclerViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyReclerViewHolder holder, final int position) {
            if (mDatas != null) {
                holder.mMessageContent.setText(mDatas.get(position).getImcontent());
            }

        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyReclerViewHolder extends RecyclerView.ViewHolder {

            private TextView mMessageContent;



            public MyReclerViewHolder(View itemView) {
                super(itemView);

                mMessageContent = (TextView) itemView.findViewById(R.id.tv_item_message);

            }

        }

    }
}
