package com.bxlwt.www.bxlwt.pager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.activity.HomeItemAct;
import com.bxlwt.www.bxlwt.base.BasePager;
import com.bxlwt.www.bxlwt.bean.HomeListBean;
import com.bxlwt.www.bxlwt.http.NetUrls;

import java.util.List;


public class HomeTabPager extends BasePager {

    private RecyclerView mRecyclerView;
    List<HomeListBean.DataBean> mBean;

    public HomeTabPager(Context context) {
        super(context);
    }

    public HomeTabPager(Context context, List result) {
        super(context, result);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.pager_home_tab, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_homePager);
        mBean = mDatas;
        initRecyclerView();


        return view;
    }

    private void initRecyclerView() {
      /*  datas = new ArrayList<>();
        for (int i = 'a'; i <'z' ; i++) {
            datas.add("秋季家装灯饰特场专用券"+(char)i);
        }*/
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new MyReclerViewAdapter());


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
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
            });


        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyReclerViewHolder extends RecyclerView.ViewHolder {
            private final TextView mLeftNum;
            private ImageView mImage;
            private TextView mTitle;
            private TextView mConsumedNum;
            private TextView mPrice;
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
