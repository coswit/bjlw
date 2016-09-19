package com.bxlwt.www.bxlwt.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.base.BasePager;
import com.bxlwt.www.bxlwt.bean.MyCouponBean;

import java.util.List;


public class MyCouponExpiredPager extends BasePager {

    private RecyclerView mRecyclerView;


    public MyCouponExpiredPager(Context context) {
        super(context);
    }

    public MyCouponExpiredPager(Context context, List result) {
        super(context, result);

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.pager_mycoupon, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_pager_mycoupon);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new MyReclerViewAdapter());
    }

    class MyReclerViewAdapter extends RecyclerView.Adapter<MyReclerViewAdapter.MyReclerViewHolder> {
        @Override
        public MyReclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_mycoupun, parent, false);
            itemView.setBackgroundResource(R.mipmap.coupon_expired);
            MyReclerViewHolder holder = new MyReclerViewHolder(itemView);
            return holder;
        }
        @Override
        public void onBindViewHolder(MyReclerViewHolder holder, int position) {
            List<MyCouponBean.DataBean> info = MyCouponExpiredPager.this.mDatas;
            MyCouponBean.DataBean dataBean = info.get(position);
            holder.dates.setText("时间："+ dataBean.getBegin_time()+"～"+ dataBean.getEnd_time());
            holder.price.setText(dataBean.getPrice());
            holder.shopName.setText(dataBean.getPname());
            if (TextUtils.equals(dataBean.getType(), Keys.FREE_TYPE)) {
                holder.type.setText("免费");
            }
            if (TextUtils.equals(dataBean.getType(), Keys.PROMOTION_TYPE)) {
                holder.type.setText("促销");
            }
            if (TextUtils.equals(dataBean.getType(), Keys.DISCOUNT_TYPE)) {
                holder.type.setText("折扣");
            }

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class MyReclerViewHolder extends RecyclerView.ViewHolder {

            private  TextView type;
            private  TextView price;
            private  TextView dates;
            private  TextView shopName;

            public MyReclerViewHolder(View itemView) {
                super(itemView);
                type = (TextView) itemView.findViewById(R.id.tv_mycoupon_type);
                price = (TextView) itemView.findViewById(R.id.tv_mycoupon_price);
                dates = (TextView) itemView.findViewById(R.id.tv_mycoupon_dates);
                shopName = (TextView) itemView.findViewById(R.id.tv_mycoupon_shopName);
            }

        }

    }
}
