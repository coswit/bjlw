package com.bxlwt.www.bxlwt.dao;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bxlwt.www.bxlwt.base.BasePager;

import java.util.ArrayList;

/**
 * Created by ZHENGJ on 2016/6/22.
 */
public class PagerTabAdapter extends PagerAdapter {
    private ArrayList<BasePager> mPagers;
    private  String[] mTitles;
    public PagerTabAdapter(ArrayList<BasePager> pagers, String[] titles) {
        this.mPagers =pagers;
        this.mTitles =titles;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mPagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mPagers.get(position).mView;
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    
}
