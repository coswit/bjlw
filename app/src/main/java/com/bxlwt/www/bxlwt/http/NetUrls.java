package com.bxlwt.www.bxlwt.http;

public interface NetUrls {

    //网络请求超时时间
    int NET_OUT_TIMES =1000*5;
    String HOST = "http://app.bxlwt.com/api/index.php";

    //用户注册
    String REGISTER = "/user/register";
    //用户登录
    String LOGIN = "/user/login";
    //用户参数
    String USERNAME = "username";
    String PWD = "password";
    String MOBILE="mobile";//发送验证码的手机号
    String CODE = "code";//发送的验证码
    //发送验证码 http://app.bxlwt.com/api/index.php/user/sendyzm/?mobile=18531652574
    String  SEND_CODE = "/user/sendyzm/";
    //验证码验证 http://app.bxlwt.com/api/index.php/user/yzcode?mobile=13011021132&code=1234
    String CHECK_CODE ="/user/yzcode";
    //修改用户昵称 http://app.bxlwt.com/api/index.php/user/update_info?userid=193224&nickname=ttt
    String UPDATE_USERINFO ="/user/update_info";
    String NICKNAME= "nickname";
    String USER_ID ="userid";
    String USER_FACE= "face";
    //获取用户信息 http://app.bxlwt.com/api/index.php/user/get_info/?userid=193224
    String GET_USERINFO ="/user/get_info/";
    //优惠列表 http://app.bxlwt.com/api/index.php/youhui/lists
    String HOME_LIST ="/youhui/lists";
    //列表图片
    String HOME_LIST_IMAGE="http://app.bxlwt.com";
    //优惠券详情  http://app.bxlwt.com/api/index.php/youhui/qlist/?id=24
    String HOME_LIST_ITEM_DETAIL="/youhui/qlist/";
    String HOME_LIST_ITEM_ID="id";
    //领取优惠券 http://app.bxlwt.com/api/index.php/youhui/add/?id=25&userid=193224
    String HOME_LIST_ITEM_GET_COUPON="/youhui/add/";
    //可使用优惠券 http://app.bxlwt.com/api/index.php/youhui/kylist/?userid=193224
    String MYCOUPON ="/youhui/kylist/";
    //过期优惠券 http://app.bxlwt.com/api/index.php/youhui/gqlist/?userid=193224
    String MYCOUPON_EXPIRED ="/youhui/gqlist/";
    //分类列表
    String HOME_GRID_LIST = "/youhui/type";
    //分类列表跳转 优惠分类跳转 http://app.bxlwt.com/api/index.php/youhui/typelist?id=396
    String  HOME_GRID_LIST_CLASSFY="/youhui/typelist";
    String  HOME_GRID_LIST_CLASSFY_ID ="id";
    //百姓头条 http://app.bxlwt.com/api/index.php/news/lists
    String HOME_HEADLINES ="/news/lists";
    //发现 http://app.bxlwt.com/api/index.php/faxian/lists
    String DISCOVERY ="/faxian/lists";
}
