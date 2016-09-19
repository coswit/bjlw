package com.bxlwt.www.bxlwt.Utils;

import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by zhengj on 2016/8/11.
 */

public class XutilHelper {

    public static String getRequest(String url) {
        final String[] mResult = {null};
        if (url != null) {
            RequestParams requestParams = new RequestParams(url);
            Callback.Cancelable cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Logger.d(result);
                    }
                    mResult[0] = result;
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Logger.d("loss");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Logger.d("oncancel");
                }

                @Override
                public void onFinished() {

                }
            });
        }

        return mResult[0];
    }


    public static String getRequest(String url, HashMap<String, Object> params) {
        final String[] mResult = {null};
        if (url != null && params != null) {
            String requestUrl = createRequestUrl(url, params);
            RequestParams requestParams = new RequestParams(requestUrl);
            Callback.Cancelable cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Logger.d(result);
                    }
                    mResult[0] = result;
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Logger.d("loss");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Logger.d("oncancel");
                }

                @Override
                public void onFinished() {

                }
            });

        }
        return mResult[0];
    }


    public static String postRequest(String url) {
        final String[] mResult = {null};
        if (url != null) {
            RequestParams requestParams = new RequestParams(url);
            Callback.Cancelable cancelable = x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Logger.d(result);
                    }
                    mResult[0] = result;
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Logger.d("loss");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Logger.d("oncancel");
                }

                @Override
                public void onFinished() {

                }
            });
        }

        return mResult[0];
    }


    public static String postRequest(String url, HashMap<String, Object> params) {
        final String[] mResult = {null};
        if (url != null && params != null) {
            String requestUrl = createRequestUrl(url, params);
            RequestParams requestParams = new RequestParams(requestUrl);
            Callback.Cancelable cancelable = x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Logger.d(result);
                    }
                    mResult[0] = result;
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Logger.d("loss");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Logger.d("oncancel");
                }

                @Override
                public void onFinished() {

                }
            });

        }
        return mResult[0];
    }

    public static String postFile(String url, HashMap<String, Object> params, File file, String fileName) {
        final String[] mResult = {null};
        String requestUrl = null;
        if (url != null && params == null) {
            requestUrl = url;
        }
        if (url != null && params != null){
            requestUrl = createRequestUrl(url, params);
        }
        RequestParams requestParams = new RequestParams(requestUrl);
        requestParams.addBodyParameter(fileName, file);
        Callback.Cancelable cancelable = x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Logger.d(result);
                }
                mResult[0] = result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d("loss");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("oncancel");
            }

            @Override
            public void onFinished() {

            }
        });


        return mResult[0];
    }

    private static String createRequestUrl(String url, HashMap<String, Object> params) {
        String requestUrl = null;
        if (url != null && params != null) {
            Set<String> keySet = params.keySet();
            ArrayList<String> keys = new ArrayList<>(keySet);
            Collections.sort(keys);
            StringBuffer sb = new StringBuffer();
            for (String key : keys) {
                sb.append("&").append(key).append("=").append(params.get(key));
            }
            //删除开始的&
            sb.deleteCharAt(0);
            requestUrl = url + "?" + sb.toString();
        }
        return requestUrl;
    }

}
