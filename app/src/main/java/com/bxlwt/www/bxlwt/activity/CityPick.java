package com.bxlwt.www.bxlwt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.base.BaseLoadAct;
import com.bxlwt.www.bxlwt.bean.CityBean;
import com.bxlwt.www.bxlwt.bean.OpencityBean;
import com.bxlwt.www.bxlwt.city.CircleTextView;
import com.bxlwt.www.bxlwt.city.City;
import com.bxlwt.www.bxlwt.city.CityAdapter;
import com.bxlwt.www.bxlwt.city.MySlideView;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.github.promeg.pinyinhelper.Pinyin;
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhengj on 2016/9/20.
 */
public class CityPick extends BaseLoadAct implements MySlideView.onTouchListener, CityAdapter.onItemClickListener {


    @BindView(R.id.tag_cityPick)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.fl_slide_container)
    FrameLayout mSlideContainer;

    private List<OpencityBean.DataBean> mDatas;
    private MySlideView mSlideView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubIcon.setVisibility(View.INVISIBLE);
        mSubTitle.setVisibility(View.INVISIBLE);
        mTitle.setText("选择城市");
        View view = getLayoutInflater().inflate(R.layout.act_city, mContainer);
        mSlideView = (MySlideView) View.inflate(mContext, R.layout.view_myslideview, null);
        ButterKnife.bind(this, view);

        mRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });
    }

    @Override
    public void initData() {
        showLoadingView();
        initOpenCityData();

//        if (names.size() > 0) {
//            initView(names);
//            showInitView();
//        } else {
//            showFailView();
//        }

    }

    private void initOpenCityData() {

        String requestUrl = NetUrls.HOST + NetUrls.OPNE_CITY;
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
                    OpencityBean bean = JsonHelper.json2Bean(result, OpencityBean.class);
                    if (bean.getCode() == 200) {
                        mDatas = bean.getData();
                        if (mDatas != null) {
                            //添加城市
                            mFlowLayout.setAdapter(new TagAdapter<OpencityBean.DataBean>(mDatas) {
                                @Override
                                public View getView(FlowLayout parent, int position, final OpencityBean.DataBean object) {
                                    TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_opencity, mFlowLayout, false);
                                    tv.setText(object.getName());
                                    tv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(mContext, MainActivity.class);
//                                            intent.putExtra(Keys.CITY_PICK,object.getName().toString());
                                            SharedPreferencesUtil.saveString(mContext, Keys.CITY_PICK, object.getName());
                                            CityPick.this.startActivity(intent);
                                            CityPick.this.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                                        }
                                    });
                                    return tv;
                                }
                            });
//                            showInitView();
                            initCityData();
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


    /**
     * 获取所有城市列表
     * @return
     */
    private List<String> initCityData() {
        final ArrayList<String> citysName = new ArrayList<>();
        String requestUrl = NetUrls.HOST + NetUrls.ALL_CITYS;
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
                    CityBean bean = JsonHelper.json2Bean(result, CityBean.class);
                    if (bean.getCode() == 200) {
                        List<CityBean.DataBean> data = bean.getData();
                        if (mDatas != null) {
                            for (int i = 0; i < data.size(); i++) {
                                citysName.add(data.get(i).getName());
                            }
                            initView(citysName);
                            mSlideContainer.removeAllViews();
                            mSlideContainer.addView(mSlideView);
                            showInitView();

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

        return citysName;
    }

    private List<City> cityList = new ArrayList<>();
    private Set<String> firstPinYin = new LinkedHashSet<>();
    public static List<String> pinyinList = new ArrayList<>();
    private PinyinComparator pinyinComparator;

    //    private MySlideView mySlideView;
    private CircleTextView circleTxt;


    private RecyclerView recyclerView;
    private TextView tvStickyHeaderView;
    private CityAdapter adapter;
    private LinearLayoutManager layoutManager;

    private void initView(List<String> names) {
        cityList.clear();
        firstPinYin.clear();
        pinyinList.clear();
//        mySlideView = (MySlideView) findViewById(R.id.my_slide_view);

        circleTxt = (CircleTextView) findViewById(R.id.my_circle_view);
        pinyinComparator = new PinyinComparator();
        tvStickyHeaderView = (TextView) findViewById(R.id.tv_sticky_header_view);
        for (int i = 0; i < names.size(); i++) {
            City city = new City();
            city.setCityName(names.get(i));
            city.setCityPinyin(transformPinYin(names.get(i)));
            cityList.add(city);
        }
        Collections.sort(cityList, pinyinComparator);
        for (City city : cityList) {
            firstPinYin.add(city.getCityPinyin().substring(0, 1));
        }
        for (String string : firstPinYin) {
            pinyinList.add(string);
        }

        mSlideView.setListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.rv_sticky_example);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CityAdapter(getApplicationContext(), cityList);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                View stickyInfoView = recyclerView.findChildViewUnder(tvStickyHeaderView.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    tvStickyHeaderView.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                View transInfoView = recyclerView.findChildViewUnder(tvStickyHeaderView.getMeasuredWidth() / 2, tvStickyHeaderView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - tvStickyHeaderView.getMeasuredHeight();
                    if (transViewStatus == CityAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            tvStickyHeaderView.setTranslationY(dealtY);
                        } else {
                            tvStickyHeaderView.setTranslationY(0);
                        }
                    } else if (transViewStatus == CityAdapter.NONE_STICKY_VIEW) {
                        tvStickyHeaderView.setTranslationY(0);
                    }
                }


            }
        });


    }

    @Override
    public void itemClick(int position) {
//        Toast.makeText(getApplicationContext(), "你选择了:" + cityList.get(position).getCityName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, MainActivity.class);
//                                            intent.putExtra(Keys.CITY_PICK,object.getName().toString());
        SharedPreferencesUtil.saveString(mContext, Keys.CITY_PICK, cityList.get(position).getCityName());
        CityPick.this.startActivity(intent);
        CityPick.this.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }

    public String transformPinYin(String character) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < character.length(); i++) {
            buffer.append(Pinyin.toPinyin(character.charAt(i)));
        }
        return buffer.toString();
    }

    @Override
    public void showTextView(String textView, boolean dismiss) {

        if (dismiss) {
            circleTxt.setVisibility(View.GONE);
        } else {
            circleTxt.setVisibility(View.VISIBLE);
            circleTxt.setText(textView);
        }

        int selectPosition = 0;
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).getFirstPinYin().equals(textView)) {
                selectPosition = i;
                break;
            }
        }
//        recyclerView.scrollToPosition(selectPosition);
        scroll2Position(selectPosition);
    }


    public class PinyinComparator implements Comparator<City> {
        @Override
        public int compare(City cityFirst, City citySecond) {
            return cityFirst.getCityPinyin().compareTo(citySecond.getCityPinyin());
        }
    }


    private void scroll2Position(int index) {
        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            recyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = recyclerView.getChildAt(index - firstPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            recyclerView.scrollToPosition(index);
        }
    }

}
