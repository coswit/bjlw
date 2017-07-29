package coswit.com.github.libs.customViews;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import coswit.com.github.libs.R;
import coswit.com.github.libs.commonUtils.DisplayUtil;

/**
 * Created by zhengj on 2016/12/3.
 */

public class AutoScrollViewPager extends FrameLayout {
    public static final int WHAT = 1001;
    private long times;
    private ViewPager mViewPager;
    private List mList;
    private LinearLayout mContainer;
    private long dotSize = 5;//小圆点大小
    private long dotMargin = 8;//小圆点距离


    public AutoScrollViewPager(Context context) {
        super(context);
        initView();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.autoplay, null);
        addView(view);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_autoplay);
        mContainer = (LinearLayout) view.findViewById(R.id.dotContainer);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(WHAT, times);
        }
    };


    public void beginAutoPlay(long times, List listImage) {
        this.times = times;
        mList = listImage;
        if (mList.size() > 0) {
            initDots();
            mViewPager.setAdapter(new AutoScrollPagerAdapter());
            handler.sendEmptyMessageDelayed(WHAT, times);


            //点选择
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                }

                @Override
                public void onPageSelected(int position) {
                    int currentItem = mViewPager.getCurrentItem() % mList.size();
                    for (int i = 0; i < mList.size(); i++) {
                        View child = mContainer.getChildAt(i);
                        child.setBackgroundResource(i == currentItem ? R.drawable.dot_focus : R.drawable.dot_unfocus);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    private void initDots() {
        mContainer.removeAllViews();
        for (int i = 0; i < mList.size(); i++) {
            View dotView = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.dip2px(dotSize), DisplayUtil.dip2px(dotSize));
            params.leftMargin = DisplayUtil.dip2px(dotMargin);
            dotView.setLayoutParams(params);
            dotView.setBackgroundResource(i == 0 ? R.drawable.dot_focus : R.drawable.dot_unfocus);
            mContainer.addView(dotView);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        handler.removeCallbacksAndMessages(null);
    }

    public void stopAutoPlay() {
        handler.removeCallbacksAndMessages(null);
    }


    class AutoScrollPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(mList.get(position % mList.size())).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return  Integer.MAX_VALUE ;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
