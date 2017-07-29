package coswit.com.github.bxlw.api;

import java.util.List;

import coswit.com.github.bxlw.bean.HomeGridBean;
import coswit.com.github.bxlw.entity.ResponseInfo;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhengj on 2016/12/28.
 */

public interface ApiService {
//
    @GET(NetUrls.HOME_GRID_LIST)
    Observable<ResponseInfo<List<HomeGridBean>>> getHomeGridDatas();


//    @GET("{params}")
//    Observable<Response> getHomeGridObservable(@Path("params") String params);
}
