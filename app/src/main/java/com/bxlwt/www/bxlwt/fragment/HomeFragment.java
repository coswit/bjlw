package com.bxlwt.www.bxlwt.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.GlideHelper;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.activity.HeadlinesContentAct;
import com.bxlwt.www.bxlwt.activity.HomeClassfyAct;
import com.bxlwt.www.bxlwt.base.BaseFragment;
import com.bxlwt.www.bxlwt.base.BasePager;
import com.bxlwt.www.bxlwt.bean.HomeGridBean;
import com.bxlwt.www.bxlwt.bean.HomeHeadLinesBean;
import com.bxlwt.www.bxlwt.bean.HomeListBean;
import com.bxlwt.www.bxlwt.dao.PagerTabAdapter;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.bxlwt.www.bxlwt.pager.HomeTabPager;
import com.bxlwt.www.bxlwt.view.UpDownViewFlipper;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.vp_banner_homeFragment)
    ViewPager vpBannerHomeFragment;
    @BindView(R.id.ll_dot)
    LinearLayout llDot;
    @BindView(R.id.id_stickynavlayout_indictor)
    TabLayout tabHome;
    @BindView(R.id.id_stickynavlayout_viewpager)
    ViewPager vpHomeTab;
    @BindView(R.id.flipper_home)
    UpDownViewFlipper viewFlipper;
    @BindView(R.id.id_stickynavlayout_topview)
    LinearLayout idStickynavlayoutTopview;
    @BindView(R.id.ll_home_a)
    LinearLayout mClickA;
    @BindView(R.id.ll_home_b)
    LinearLayout mClickB;
    @BindView(R.id.ll_home_c)
    LinearLayout mClickC;
    @BindView(R.id.ll_home_d)
    LinearLayout mClickD;
    @BindView(R.id.ll_home_e)
    LinearLayout mClickE;
    @BindView(R.id.ll_home_f)
    LinearLayout mClickF;
    @BindView(R.id.ll_home_g)
    LinearLayout mClickG;
    @BindView(R.id.iv_homeGrid_1)
    ImageView ivHomeGrid1;
    @BindView(R.id.tv_homeGrid_1)
    TextView tvHomeGrid1;
    @BindView(R.id.iv_homeGrid_2)
    ImageView ivHomeGrid2;
    @BindView(R.id.tv_homeGrid_2)
    TextView tvHomeGrid2;
    @BindView(R.id.iv_homeGrid_3)
    ImageView ivHomeGrid3;
    @BindView(R.id.tv_homeGrid_3)
    TextView tvHomeGrid3;
    @BindView(R.id.iv_homeGrid_4)
    ImageView ivHomeGrid4;
    @BindView(R.id.tv_homeGrid_4)
    TextView tvHomeGrid4;
    @BindView(R.id.iv_homeGrid_5)
    ImageView ivHomeGrid5;
    @BindView(R.id.tv_homeGrid_5)
    TextView tvHomeGrid5;
    @BindView(R.id.iv_homeGrid_6)
    ImageView ivHomeGrid6;
    @BindView(R.id.tv_homeGrid_6)
    TextView tvHomeGrid6;
    @BindView(R.id.iv_homeGrid_7)
    ImageView ivHomeGrid7;
    @BindView(R.id.tv_homeGrid_7)
    TextView tvHomeGrid7;
    @BindView(R.id.iv_homeGrid_8)
    ImageView ivHomeGrid8;
    @BindView(R.id.tv_homeGrid_8)
    TextView tvHomeGrid8;
    @BindView(R.id.ll_home_disc_others)
    LinearLayout llHomeDiscOthers;
    //临时存放图片的集合
    private int[] pictures = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3};

    private Activity mActivity;
    private HomeGridBean mGridBean;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        mActivity = getActivity();
        mTitle.setText(R.string.app_name);
        ButterKnife.bind(this, view);


        viewpagerCarousel();
//        initTab();
//        initFlipper();
//        initListDatas();
        return view;
    }


    @Override
    public void initData() {
        showLoadingView();
        initListDatas();
        initFlipperDatas();
    }

    private void initFlipperDatas() {
        String requestUrl = NetUrls.HOST + NetUrls.HOME_HEADLINES;
        RequestParams params = new RequestParams(requestUrl);
        params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
        Logger.d(params);
        x.http().get(params, new Callback.CacheCallback<String>() {
            private String result = null;

            @Override
            public boolean onCache(String result) {
                return true;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
//                    Logger.d(result);
                    HomeHeadLinesBean bean = JsonHelper.json2Bean(result, HomeHeadLinesBean.class);
                    if (bean.getCode() == 200) {

                        List<HomeHeadLinesBean.DataBean> headDatas = bean.getData();
                        initFlipper(headDatas);

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

    /**
     * 头条跳动
     *
     * @param headDatas
     */
    private void initFlipper(List<HomeHeadLinesBean.DataBean> headDatas) {
        final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> htmlList = new ArrayList<>();
        if (headDatas != null) {
            for (int i = 0; i < headDatas.size(); i++) {
                list.add(headDatas.get(i).getTitle());
                //头条详情访问地址 http://www.bxlwt.com/show-45-246550.html
                htmlList.add("http://www.bxlwt.com/show-"+headDatas.get(i).getCatid()+
                        "-"+headDatas.get(i).getId()+".html");
            }
            viewFlipper.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_flipperview, null);
                TextView itemText = (TextView) itemView.findViewById(R.id.tv_item_popText);
                final int postion = i;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, HeadlinesContentAct.class);
                        intent.putExtra(Keys.HOME_HEADLINES_HTML,htmlList.get(postion));
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim) ;
                    }
                });
                viewFlipper.addView(itemView);
                itemText.setText(list.get(i));
            }
        }

        viewFlipper.startFlipping();
        showInitView();
    }

    private void initGridDatas() {
        String requestUrl = NetUrls.HOST + NetUrls.HOME_GRID_LIST;
        RequestParams params = new RequestParams(requestUrl);
        params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
        Logger.d(params);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    mGridBean = JsonHelper.json2Bean(result, HomeGridBean.class);
                    if (mGridBean.getCode() == 200) {
                        tvHomeGrid1.setText(mGridBean.getData().get(0).getCatename());
                        tvHomeGrid2.setText(mGridBean.getData().get(1).getCatename());
                        tvHomeGrid3.setText(mGridBean.getData().get(2).getCatename());
                        tvHomeGrid4.setText(mGridBean.getData().get(3).getCatename());
                        tvHomeGrid5.setText(mGridBean.getData().get(4).getCatename());
                        tvHomeGrid6.setText(mGridBean.getData().get(5).getCatename());
                        tvHomeGrid7.setText(mGridBean.getData().get(6).getCatename());
                        tvHomeGrid8.setText(mGridBean.getData().get(7).getCatename());
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(0).getImg()).into(ivHomeGrid1);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(1).getImg()).into(ivHomeGrid2);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(2).getImg()).into(ivHomeGrid3);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(3).getImg()).into(ivHomeGrid4);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(4).getImg()).into(ivHomeGrid5);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(5).getImg()).into(ivHomeGrid6);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(6).getImg()).into(ivHomeGrid7);
                        Glide.with(mContext).load(NetUrls.HOME_LIST_IMAGE + mGridBean.getData().get(7).getImg()).into(ivHomeGrid8);


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


    private void initListDatas() {
        String requestUrl = NetUrls.HOST + NetUrls.HOME_LIST;
        RequestParams params = new RequestParams(requestUrl);
        params.setConnectTimeout(NetUrls.NET_OUT_TIMES);
        params.setCacheMaxAge(60 * 1000 * 3);
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
//                    Logger.d(result);
                    this.result = result;
                    HomeListBean listBean = JsonHelper.json2Bean(this.result, HomeListBean.class);
                    if (listBean.getCode() == 200) {
                        List<HomeListBean.DataBean> datas = listBean.getData();
                        if (datas != null) {
                            Logger.d(datas);
                            List<HomeListBean.DataBean> freeDatas = new ArrayList<HomeListBean.DataBean>();
                            List<HomeListBean.DataBean> discountDatas = new ArrayList<HomeListBean.DataBean>();
                            List<HomeListBean.DataBean> promotionDatas = new ArrayList<HomeListBean.DataBean>();
                            for (int i = 0; i < datas.size(); i++) {
                                String type = datas.get(i).getYouhui_type();
                                if (TextUtils.equals(type, Keys.FREE_TYPE)) {
                                    freeDatas.add(datas.get(i));
                                } else if (TextUtils.equals(type, Keys.DISCOUNT_TYPE)) {
                                    discountDatas.add(datas.get(i));
                                } else if (TextUtils.equals(type, Keys.PROMOTION_TYPE)) {
                                    promotionDatas.add(datas.get(i));
                                }
                            }


                            ArrayList<BasePager> pagers = new ArrayList<>();
                            String[] titles = {"全部", "免费", "折扣", "促销"};
                            pagers.add(new HomeTabPager(mActivity, datas));
                            pagers.add(new HomeTabPager(mActivity, freeDatas));
                            pagers.add(new HomeTabPager(mActivity, discountDatas));
                            pagers.add(new HomeTabPager(mActivity, promotionDatas));


                            vpHomeTab.setAdapter(new PagerTabAdapter(pagers, titles));
                            tabHome.setupWithViewPager(vpHomeTab);
                            initGridDatas();

                        }
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


    @OnClick({R.id.ll_home_a, R.id.ll_home_b, R.id.ll_home_c, R.id.ll_home_d, R.id.ll_home_e, R.id.ll_home_f, R.id.ll_home_g, R.id.ll_home_disc_others})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_home_a:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(0).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(0).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_b:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(1).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(1).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_c:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(2).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(2).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_d:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(3).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(3).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_e:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(4).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(4).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_f:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(5).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(5).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_g:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(6).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(6).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
            case R.id.ll_home_disc_others:
                if (mGridBean != null) {
                    intent = new Intent(mContext, HomeClassfyAct.class);
                    intent.putExtra(Keys.HOME_GRID_LIST_TITLE, mGridBean.getData().get(7).getCatename());
                    intent.putExtra(Keys.HOME_GRID_LIST_ID, mGridBean.getData().get(7).getId());
                    startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(pictures[position % pictures.length]);
            GlideHelper.loadImageFromeRes(mContext, pictures[position % pictures.length], imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    /**
     * 轮播图
     */
    private void viewpagerCarousel() {
        vpBannerHomeFragment.setAdapter(new MyPagerAdapter());
        //让ViewPager默认选中比较大的一页，这样的话可以实现往右边滑动很多次,注意：此处最好不要设置过大，否则可能造成ViewPager滑动卡顿
        vpBannerHomeFragment.setCurrentItem(pictures.length * 10000);
        //初始化dot的布局
        for (int i = 0; i < pictures.length; i++) {
            //创建点
            View dotView = new View(mContext);
            //设置点的宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
            params.leftMargin = 18;
            dotView.setLayoutParams(params);
            //给view设置背景图片，就是黑点的图片
            dotView.setBackgroundResource(R.drawable.dot_unfocus);
            //将view对象添加到ll_dot布局中
            llDot.addView(dotView);
        }
        //循环更新点选择
        vpBannerHomeFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取ViewPager当前选中的是第几页
                int currentItem = vpBannerHomeFragment.getCurrentItem() % pictures.length;
                //根据当前是第几页，让第几个点设置白色点
                for (int i = 0; i < pictures.length; i++) {
                    View child = llDot.getChildAt(i);
                    child.setBackgroundResource(i == currentItem ? R.drawable.dot_focus : R.drawable.dot_unfocus);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //发送延时消息，选中下一页
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    //图片轮播
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //收到消息，先选中下一页，然后再次发送延时消息
            vpBannerHomeFragment.setCurrentItem(vpBannerHomeFragment.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected Object doInBackground() {
        return null;
    }

    @Override
    protected void onPostExecute(Object object) {

    }


     /*  private void initTab() {
            ArrayList<BasePager> pagers = new ArrayList<>();
            String[] titles = {"全部", "免费", "折扣", "优惠"};
            for (int i = 0; i < 4; i++) {
                pagers.add(new HomeTabPager(mActivity,listDatas));
            }
            vpHomeTab.setAdapter(new PagerTabAdapter(pagers, titles));
            tabHome.setupWithViewPager(vpHomeTab);

    }*/


        /*  x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String >() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    HomeListBean listBean = JsonHelper.json2Bean(result, HomeListBean.class);
                    if (listBean.getCode() == 200) {
                        List<HomeListBean.DataBean> datas = listBean.getData();

                        if (datas != null) {
                            ArrayList<BasePager> pagers = new ArrayList<>();
                            String[] titles = {"全部", "免费", "折扣", "优惠"};
                            for (int i = 0; i < 4; i++) {
                                pagers.add(new HomeTabPager(mActivity,datas));
                            }
                            vpHomeTab.setAdapter(new PagerTabAdapter(pagers, titles));
                            tabHome.setupWithViewPager(vpHomeTab);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mLoading.setVisibility(View.INVISIBLE);
                mContainer.setVisibility(View.INVISIBLE);
                mFailLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                mLoading.setVisibility(View.INVISIBLE);
                mContainer.setVisibility(View.VISIBLE);
                mFailLoading.setVisibility(View.INVISIBLE);
            }
        });
*/


}
