package com.bxlwt.www.bxlwt.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import java.io.Reader;

/**
 * Created by zhengj on 2016/7/6.
 */
public class JsonHelper {

    public static final Gson gson = new Gson();

    public static <T> T json2Bean(String json,Class<T> clazz){
        T bean = null;
        try {
            bean = gson.fromJson(json,clazz);
        } catch (JsonSyntaxException e) {
            Logger.d("JsonHelper,json解析数据异常:","json解析数据异常\njson:"+json);
            e.printStackTrace();
        }
        return bean;
    }


    public static <T> T json2Bean(Reader json, Class<T> clazz){
        T bean = null;
        try {
            bean = gson.fromJson(json,clazz);
        } catch (JsonSyntaxException e) {
            Logger.d("JsonHelper,json解析数据异常","json解析数据异常\njson:"+json);
            e.printStackTrace();
        }
        return bean;
    }
}
