package coswit.com.github.bxlw.ui.main.model;

import java.util.List;

import coswit.com.github.bxlw.Utils.RxHttp;
import coswit.com.github.bxlw.bean.HomeGridBean;
import coswit.com.github.bxlw.entity.ResponseInfo;
import coswit.com.github.libs.base.BaseModel;
import rx.Observable;

/**
 * Created by zhengj on 2016/12/22.
 */

public class HomeModel implements BaseModel{
    public Observable<ResponseInfo<List<HomeGridBean>>> getHomeGridObservable(){

    return  RxHttp.getApiService().getHomeGridDatas();
    }

}
