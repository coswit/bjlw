package coswit.com.github.bxlw.ui.main.presenter;

import java.util.List;

import coswit.com.github.bxlw.bean.HomeGridBean;
import coswit.com.github.bxlw.entity.ResponseInfo;
import coswit.com.github.bxlw.ui.main.fragment.HomeFragment;
import coswit.com.github.bxlw.ui.main.model.HomeModel;
import coswit.com.github.libs.base.BasePresenter;
import coswit.com.github.libs.baserx.RxSchedulers;
import coswit.com.github.libs.baserx.RxSubscriber;

/**
 * Created by zhengj on 2016/12/22.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragment,HomeModel> {



    public void getBannerDatas() {


        RxSchedulers.io2Main(mModel.getHomeGridObservable()).subscribe(new RxSubscriber<ResponseInfo<List<HomeGridBean>>>(mContext,false) {

            @Override
            public void onStart() {
                super.onStart();
//                mView.startProgressDialog();
            }

            @Override
            protected void _onNext(ResponseInfo<List<HomeGridBean>> datas) {
                mView.setGridDatas(datas);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
}
