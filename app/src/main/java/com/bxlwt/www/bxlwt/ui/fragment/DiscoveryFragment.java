package com.bxlwt.www.bxlwt.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.activity.DiscveryItemAct;
import com.bxlwt.www.bxlwt.base.BaseFragment;
import com.bxlwt.www.bxlwt.bean.DiscoveryBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.bxlwt.www.bxlwt.view.AutoMeasureImageView;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by zhengj on 2016/8/8.
 */
public class DiscoveryFragment extends BaseFragment {


    private RecyclerView mRecyclerView;
    private List<DiscoveryBean.DataBean> mDatas;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_discovery, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_discovery);

        mTitle.setText("发现");
       showLoadingView();
        return view;
    }

    @Override
    protected Object doInBackground() {return null;
    }

    @Override
    protected void onPostExecute(Object object) {

    }

    @Override
    public void initData() {
        String requestUrl = NetUrls.HOST + NetUrls.DISCOVERY;
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
                    DiscoveryBean bean = JsonHelper.json2Bean(result, DiscoveryBean.class);
                    if (bean.getCode() == 200) {
                        mDatas = bean.getData();
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
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_discovery, parent, false);
            MyReclerViewHolder holder = new MyReclerViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyReclerViewHolder holder, final int position) {
            if (mDatas != null) {
                holder.mTitle.setText(mDatas.get(position).getTitle());
                holder.mDiscription.setText(mDatas.get(position).getDescription());
                holder.mBeRead.setText(mDatas.get(position).getHits()+" 人浏览");
                Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE+mDatas.get(position).getThumb()).into(holder.mImage);
            }

//            //条目点击跳转
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DiscveryItemAct.class);
                    intent.putExtra(Keys.DISCOVERY_DETAIL_HTML, mDatas.get(position).getId());
                    mActivity.startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
            });


        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyReclerViewHolder extends RecyclerView.ViewHolder {

            private AutoMeasureImageView mImage;
            private TextView mTitle;
            private TextView mDiscription;
            private TextView mBeRead;


            public MyReclerViewHolder(View itemView) {
                super(itemView);
                mImage = (AutoMeasureImageView) itemView.findViewById(R.id.iv_item_discover);
                mTitle = (TextView) itemView.findViewById(R.id.tv_item_discovery_title);
                mDiscription = (TextView) itemView.findViewById(R.id.tv_item_dicovery_description);
                mBeRead = (TextView) itemView.findViewById(R.id.tv_item_dicovery_beread);
            }

        }

    }

}
