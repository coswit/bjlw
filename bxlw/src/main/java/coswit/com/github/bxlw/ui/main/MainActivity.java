package coswit.com.github.bxlw.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.BindView;
import coswit.com.github.bxlw.R;
import coswit.com.github.bxlw.Utils.FragmentTabHost;
import coswit.com.github.bxlw.ui.main.fragment.DiscoveryFragment;
import coswit.com.github.bxlw.ui.main.fragment.HomeFragment;
import coswit.com.github.bxlw.ui.main.fragment.MyFragment;
import coswit.com.github.libs.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String[] TAGS = {"homeFragment", "discoveryFragment", "myFragment"};
    private static final int[] TAB_ICONS = {R.drawable.selector_ic_home, R.drawable.selector_ic_disc, R.drawable.selector_ic_my};
    private static final CharSequence[] TAB_TITLES = {"优惠", "发现", "我的"};
    @BindView(R.id.tabContainer)
    FrameLayout mTabContainer;
    @BindView(R.id.tabHost)
    FragmentTabHost mTabHost;
    private ImageView mTabIcon;
    private TextView mTabTitle;


    public static void startAction(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        getLayoutInflater();
        initTab();
    }

    private void initTab() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabContainer);
        mTabHost.getTabWidget().setDividerDrawable(R.color.white);
        Class fragments[] = {HomeFragment.class, DiscoveryFragment.class, MyFragment.class};
        for (int i = 0; i < TAGS.length; i++) {
            View tabView = getTabWidgtView(i);
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(TAGS[i]).setIndicator(tabView);
            mTabHost.addTab(tabSpec, fragments[i], null);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }

        });
    }

    /**
     * 获取底部tab
     *
     * @param i
     * @return
     */
    private View getTabWidgtView(int i) {
        View view = getLayoutInflater().inflate(R.layout.item_tab_view, null);
        mTabTitle = (TextView) view.findViewById(R.id.tv_item_tab_title);
        mTabIcon = (ImageView) view.findViewById(R.id.iv_item_tab_icon);
        mTabIcon.setImageResource(TAB_ICONS[i]);
        mTabTitle.setText(TAB_TITLES[i]);
        return view;
    }


}
