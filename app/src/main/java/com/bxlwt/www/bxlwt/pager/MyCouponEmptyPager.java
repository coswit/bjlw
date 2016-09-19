package com.bxlwt.www.bxlwt.pager;

import android.content.Context;
import android.view.View;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.base.BasePager;

/**
 * Created by zhengj on 2016/9/14.
 */
public class MyCouponEmptyPager extends BasePager {



    public MyCouponEmptyPager(Context context) {
        super(context);

    }

  /*  public MyCouponEmptyPager(Context context) {
        mView = View.inflate(context, R.layout.pager_mycoupon_empty, null);
    }*/

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.pager_mycoupon_empty, null);
        return view;
    }
}
