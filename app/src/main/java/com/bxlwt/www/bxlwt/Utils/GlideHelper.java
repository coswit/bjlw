package com.bxlwt.www.bxlwt.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zhengj on 2016/7/6.
 */
public class GlideHelper {
    public  static  void  loadImageFromUrl(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
    public  static  void  loadImageFromeRes(Context context, Integer intId, ImageView imageView){
        Glide.with(context).load(intId).into(imageView);
    }



}
