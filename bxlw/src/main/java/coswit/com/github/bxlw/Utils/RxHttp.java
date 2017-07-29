package coswit.com.github.bxlw.Utils;

import coswit.com.github.bxlw.api.ApiService;
import coswit.com.github.bxlw.api.NetUrls;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengj on 2016/12/28.
 */

public class RxHttp {

    private static ApiService apiService;
    private static RxHttp rxHttp;

    private RxHttp() {
        Retrofit retrofit =  new Retrofit.Builder().baseUrl(NetUrls.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private static RxHttp getInstance() {
        if (rxHttp == null) {
            synchronized (RxHttp.class) {
                rxHttp = new RxHttp();
            }
        }
        return rxHttp;
    }


    public static ApiService getApiService() {
        return getInstance().apiService;
    }
}
