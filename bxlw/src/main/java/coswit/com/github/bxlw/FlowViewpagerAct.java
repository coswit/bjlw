package coswit.com.github.bxlw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coswit.com.github.bxlw.view.AutoScrollViewPager;

/**
 * Created by zhengj on 2016/12/3.
 */
public class FlowViewpagerAct extends AppCompatActivity {

    @BindView(R.id.vp_flowviewpager)
    AutoScrollViewPager mViewpager;
    List list = new ArrayList();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowviewpager);
        ButterKnife.bind(this);
        list.add(R.mipmap.banner1);
        list.add(R.mipmap.banner2);
        list.add(R.mipmap.banner3);


        mViewpager.beginAutoPlay(3000,list);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewpager.stopAutoPlay();
    }
}
