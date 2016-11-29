package com.bxlwt.www.bxlwt;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.xutils.x;


public class MyApp extends Application {
    private static Context context;
    public static final String  WX_APP_ID = "wxfa6bfda53ecdf7dd";
    public static final String WX_SECRET = "faf6e014b3a8ce0bc85782cd45255fc9";
    public static IWXAPI api;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //xutil3的相关配置
        x.Ext.init(this);
        x.Ext.setDebug(false);


        //微信登录相关
        api = WXAPIFactory.createWXAPI(context,WX_APP_ID,false);
        api.registerApp(WX_APP_ID);


        Logger.init("bjlwt")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2);                 // default 0
               //.default AndroidLogAdapter



    }



    public static Context getContext() {
        return context;
    }


}
