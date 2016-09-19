package com.bxlwt.www.bxlwt.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bxlwt.www.bxlwt.MyApp;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;



/**
 * Created by zhengj on 2016/7/26.
 */
public class NetUtil {



    /**
     * 创建带参数的url
     * @param url
     * @param params
     * @return
     */
    public static String createRequestUrl(String url, HashMap<String,Object> params) {
        String requestUrl=null;
        if (url != null && params !=null) {
            Set<String> keySet = params.keySet();
            ArrayList<String> keys = new ArrayList<>(keySet);
            Collections.sort(keys);
            StringBuffer sb = new StringBuffer();
            for (String key : keys) {
                sb.append("&").append(key).append("=").append(params.get(key));
            }
            //删除开始的&
            sb.deleteCharAt(0);
            requestUrl = url + "?"+sb.toString();
        }
        return requestUrl;
    }


   /* public static String getJsonFromNet(String requestUrl) {
        String json=null;
        BufferedWriter writer=null;
        try {
            Response response = OkHttpUtils.get().url(requestUrl).build().execute();
            json =response.body().string();
            //获取要存放的缓存文件，将请求到的json放到缓存文件中
            File cacheFile = getCacheFile(requestUrl);
            writer = new BufferedWriter(new FileWriter(cacheFile));
            long validTime = System.currentTimeMillis() + 3*60*1000;//有效时间3分
            writer.write(validTime+"");
            writer.newLine();
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
            Logger.i("请求json错误",e);
        }finally {
            IOClose(writer);
        }
        return json;
    }
*/
    public static String getJsonFromLocal(String requestUrl) {
        String json = null;
        File cacheFile = getCacheFile(requestUrl);
        BufferedReader reader = null;
        if (cacheFile.exists()) {
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                Long validTime = Long.valueOf(reader.readLine());
                if (System.currentTimeMillis()<validTime) {
//                    json = reader.readLine();
                    CharArrayWriter charArrayWriter = new CharArrayWriter();
                    char[] buf = new char[1024];
                    int len;
                    while ((len = reader.read(buf))!=-1) {
                        charArrayWriter.write(buf,0,len);
                    }
                    json =charArrayWriter.toString();
                    charArrayWriter.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOClose(reader);

            }
        }
        return json;
    }

  /*  public static String getJson(String requestUrl) {
        String json =getJsonFromLocal(requestUrl);
        if (TextUtils.isEmpty(json)) {
            json = getJsonFromNet(requestUrl);
            Logger.d("从网络获取到的数据");
        } else {
            Logger.d("从缓存获取到的数据");
        }
        return json;
    }*/

    public static File getCacheFile(String requestUrl) {
        try {
            String encodeUrl = URLEncoder.encode(requestUrl, "UTF-8");
            File cacheFile = new File(MyApp.getContext().getCacheDir(), encodeUrl);
            return cacheFile;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean IOClose(Closeable io) {
        if (io != null) {
            try {
                io.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }



}
