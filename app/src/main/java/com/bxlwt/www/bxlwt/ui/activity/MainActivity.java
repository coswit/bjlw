package com.bxlwt.www.bxlwt.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.base.BaseActivity;
import com.bxlwt.www.bxlwt.ui.fragment.DiscoveryFragment;
import com.bxlwt.www.bxlwt.ui.fragment.HomeFragment;
import com.bxlwt.www.bxlwt.ui.fragment.MyFragment;
import com.bxlwt.www.bxlwt.view.FragmentTabHost;

public class MainActivity extends BaseActivity {
    FrameLayout mTabContainer;
    FragmentTabHost   mTabHost;
    public LayoutInflater mLayoutInflater;
    ImageView mTabIcon;
    TextView mTabTitle;
    public static final String HOME_TAG = "home";
    public static final String DISCOVERY_TAG = "discovery";
    public static final String MY_TAG = "set";
    private int[] mImages;
    private CharSequence[] mTitles;
    private Intent mIntent;


    public static void startAction(Activity activity) {
        activity.startActivity(new Intent(activity,MainActivity.class));
        activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayoutInflater = LayoutInflater.from(this);
        mTabContainer = (FrameLayout) findViewById(R.id.tabContainer);
//        mTabHost = (com.bxlwt.www.bxlwt.view.FragmentTabHost) findViewById(R.id.tabHost);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);

        initTab();

    }

    private void initTab() {
        String[] tag = new String[]{HOME_TAG, DISCOVERY_TAG, MY_TAG};
        mTitles = new CharSequence[]{"优惠", "发现", "我的",};


        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabContainer);
        mTabHost.getTabWidget().setDividerDrawable(R.color.white);
        Class fragments[] = {HomeFragment.class, DiscoveryFragment.class, MyFragment.class};
        //背景图片
        mImages = new int[]{R.drawable.selector_ic_home, R.drawable.selector_ic_disc, R.drawable.selector_ic_my};
        for (int i = 0; i < tag.length; i++) {
            View tabView = getTabWidgtView(i);
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tag[i]).setIndicator(tabView);
            mTabHost.addTab(tabSpec, fragments[i], null);

        }
    }

    /**
     * 获取底部tab
     *
     * @param i
     * @return
     */
    private View getTabWidgtView(int i) {
        View view = mLayoutInflater.inflate(R.layout.item_tab_view, null);
        mTabTitle = (TextView) view.findViewById(R.id.tv_item_tab_title);
        mTabIcon = (ImageView) view.findViewById(R.id.iv_item_tab_icon);
        mTabIcon.setImageResource(mImages[i]);
        mTabTitle.setText(mTitles[i]);
        return view;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mIntent =getIntent();
//        mPickedCity = mIntent.getStringExtra(Keys.CITY_PICK);
//
//        Logger.d("城市" + mPickedCity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIntent = getIntent();
        int i = mIntent.getIntExtra(Keys.REGISTER_BACK, 10);
        if (i == Keys.REGISTER_BACK_INTENT) {
            mTabHost.setCurrentTab(2);
        }
    }
}
