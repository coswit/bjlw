package com.bxlwt.www.bxlwt.Utils;

/**
 * Created by ZHENGJ on 2016/6/6.
 */
public interface Keys {
    //sharedPreferences存放文件的文件名
    String CONFIG_FILE = "com.bxlwt.www.bxlwt.preferences_file_key";
    //sharedPreferences存放文件的文件名
    String USER_INFO = "com.bxlwt.www.bxlwt.userInfo";
    //intent跳转时注册并成功登录返回到我的页面
    String REGISTER_BACK = "REGISTER_BACK";
    //intent跳转时注册并成功登录返回到我的页面
    int REGISTER_BACK_INTENT = 1001;
    //首页列表
    String HOMELIST ="homelist";
    //首页列表跳转时携带的条目id
    String HOME_LIST_ITEM_ID = "homeListItemId";
    //首页分类跳转时intent传递的条目
    String HOME_GRID_LIST_ID ="homeGridListItem";
    String HOME_GRID_LIST_TITLE ="homeGridListTitle";
    //免费类
    String FREE_TYPE = "0";
    //折扣类
    String DISCOUNT_TYPE = "2";
    //促销类
    String PROMOTION_TYPE = "1";
    //头条跳转时的html地址
    String  HOME_HEADLINES_HTML = "homeHeadlinesHtmlAddress";


}
