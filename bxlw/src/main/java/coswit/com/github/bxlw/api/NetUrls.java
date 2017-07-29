package coswit.com.github.bxlw.api;

public interface NetUrls {

    //网络请求超时时间
    int NET_OUT_TIMES =1000*5;
    String HOST = "http://app.bxlwt.com/api/index.php/";

    //用户注册
    String REGISTER = "user/register";
    //用户登录
    String LOGIN = "user/login";
    //用户参数
    String USERNAME = "username";
    String PWD = "password";
    String MOBILE="mobile";//发送验证码的手机号
    String CODE = "code";//发送的验证码
    //发送验证码 http://app.bxlwt.com/api/index.php/user/sendyzm/?mobile=18531652574
    String  SEND_CODE = "user/sendyzm/";
    //验证码验证 http://app.bxlwt.com/api/index.php/user/yzcode?mobile=13011021132&code=1234
    String CHECK_CODE ="user/yzcode";
    //修改用户昵称 http://app.bxlwt.com/api/index.php/user/update_info?userid=193224&nickname=ttt
    String UPDATE_USERINFO ="user/update_info";
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
    //分类列表 http://app.bxlwt.com/api/index.php/youhui/type
    //列表图片 http://app.bxlwt.com/uploads/ico/1.png
    String HOME_GRID_LIST = "youhui/type";
    //分类列表跳转 优惠分类跳转 http://app.bxlwt.com/api/index.php/youhui/typelist?id=396
    String  HOME_GRID_LIST_CLASSFY="youhui/typelist";
    String  HOME_GRID_LIST_CLASSFY_ID ="id";
    //百姓头条 http://app.bxlwt.com/api/index.php/news/lists
    String HOME_HEADLINES ="/news/lists";
    //发现 http://app.bxlwt.com/api/index.php/faxian/lists
    String DISCOVERY ="/faxian/lists";
    //我的积分 http://app.bxlwt.com/api/index.php/int/jifen?userid=193224
    String INTERGRATE ="/int/jifen";
    //消息 http://app.bxlwt.com/api/index.php/im/lists/?touserid=214821&type=%E7%B3%BB%E7%BB%9F%E6%B6%88%E6%81%AF
    String MESSAGE ="/im/lists/";
    //已开通城市 http://app.bxlwt.com/api/index.php/area/openarea
    String OPNE_CITY = "/area/openarea";
    //首页banner http://app.bxlwt.com/api/index.php/banner/index
    String  HOME_BANNER = "/banner/index";
    //搜索 主要词显示 http://app.bxlwt.com/api/index.php/search/lists
    String SEARCH_KEYWORD_LIST = "/search/lists";
    //搜索 关键词跳转 http://app.bxlwt.com/api/index.php/search/search?keyword=%E7%9B%9B%E4%B8%96
    String SEARCH_KEYWORD_ACT = "/search/search";
    String KEYWORD = "keyword";
    //意见反馈 http://app.bxlwt.com/api/index.php/user/feedback/?userid=193224&title=
    String ADVICE ="/user/feedback/";
    String ADVICE_TITLE = "title";
    //所有城市 http://app.bxlwt.com/api/index.php/area/area
    String ALL_CITYS = "/area/area";
    //微信登录 http://app.bxlwt.com/api/index.php/user/login_open?opentype=wechat&usertype=app
    //              &openid=1&headimgurl=1&nickname=1&unionid=1&country=1&province=1&sex=1&from=1
    String WX_LOGIN = "/user/login_open";
    //获取微信的用户信息 https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
    String WX_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";
    String WX_TOKEN = "access_token";
    String WX_OPENID = "openid";





}
