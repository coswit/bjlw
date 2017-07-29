package coswit.com.github.bxlw;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by zhengj on 2016/12/3.
 */

public class CommonPagerAdapter extends PagerAdapter {

    private List mList;
    private Context context;
    private boolean isPagerChanged;
    public CommonPagerAdapter(List list,Context context,boolean isPagerChanged) {
        mList=list;
        this.context = context;
        this.isPagerChanged = isPagerChanged;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(context).load(mList.get(isPagerChanged ? position % mList.size() :position )).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return isPagerChanged ? Integer.MAX_VALUE:mList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


}
