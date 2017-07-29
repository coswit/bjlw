package coswit.com.github.bxlw.ui.main.fragment;

import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import coswit.com.github.bxlw.R;
import coswit.com.github.bxlw.api.NetUrls;
import coswit.com.github.bxlw.bean.HomeGridBean;
import coswit.com.github.bxlw.entity.ResponseInfo;
import coswit.com.github.bxlw.ui.main.model.HomeModel;
import coswit.com.github.bxlw.ui.main.presenter.HomeFragmentPresenter;
import coswit.com.github.bxlw.view.AutoScrollViewPager;
import coswit.com.github.bxlw.widget.UpDownViewFlipper;
import coswit.com.github.libs.base.BaseFragment;

/**
 * Created by zhengj on 2016/12/22.
 */
public class HomeFragment extends BaseFragment<HomeFragmentPresenter, HomeModel> {


    @BindView(R.id.vp_banner_homeFragment)
    AutoScrollViewPager bannerViewpager;
    @BindView(R.id.ll_dot)
    LinearLayout llDot;
    @BindView(R.id.gv_home)
    GridView gvHome;
    @BindView(R.id.flipper_home)
    UpDownViewFlipper flipperHome;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

       mPresenter.getBannerDatas();
    }


    public void setGridDatas(ResponseInfo<List<HomeGridBean>> datas) {
        ArrayList<String> list = new ArrayList();
        for(int i = 0;i<datas.getData().size();i++) {
            list.add(NetUrls.HOME_LIST_IMAGE+datas.getData().get(i).getImg());
        }
        bannerViewpager.beginAutoPlay(3000,list);
    }


    @Override
    public void onStop() {
        super.onStop();
        bannerViewpager.stopAutoPlay();
    }
}

