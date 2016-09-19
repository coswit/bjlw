package com.bxlwt.www.bxlwt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

/**
 * Created by zhengj on 2016/8/18.
 */
public class UpDownViewFlipper extends ViewFlipper {

    private int mFlipperInterval =2000;
    private int mDuration = 500;

    public UpDownViewFlipper(Context context) {
        super(context);
        init(context,null);
    }

    public UpDownViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setFlipInterval(mFlipperInterval);
        //进场切换动画
        TranslateAnimation inTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,1.0f,Animation.RELATIVE_TO_PARENT,0f);
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        AnimationSet inAnimationSet = new AnimationSet(context, attrs);
        inAnimationSet.setDuration(500);
        inAnimationSet.addAnimation(inAlphaAnimation);
        inAnimationSet.addAnimation(inTranslateAnimation);
        //出场切换动画
        TranslateAnimation outTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-1.0f);
        AlphaAnimation outAlphaAnimation = new AlphaAnimation(1,0);
        AnimationSet outAnimationSet = new AnimationSet(context, attrs);
        outAnimationSet.setDuration(500);
        outAnimationSet.addAnimation(outAlphaAnimation);
        outAnimationSet.addAnimation(outTranslateAnimation);

        setInAnimation(inAnimationSet);
        setOutAnimation(outAnimationSet);
    }





}
