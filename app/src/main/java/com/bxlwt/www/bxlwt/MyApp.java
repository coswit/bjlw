package com.bxlwt.www.bxlwt;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.xutils.x;



public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //xutil3的相关配置
        x.Ext.init(this);
        x.Ext.setDebug(false);




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
