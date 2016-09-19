package com.bxlwt.www.bxlwt.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/14.
 */
public class MyCouponBean  {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"2","youhui_id":"25","type":"1","user_name":"18701194357","user_tel":"18701194357","yname":"华莱士30元抵用券","price":"30","pname":" 肯德基（省府店）","youhui_sn":"BX14734133739489","user_id":"193224","create_time":"1473413373","begin_time":"2016-09-06","end_time":"2016-10-08","expire_time":"0"},{"id":"6","youhui_id":"28","type":"0","user_name":"18701194357","user_tel":"18701194357","yname":"一元吃肯德基","price":"1","pname":"肯德基（省府店）","youhui_sn":"BX14737604068138","user_id":"193224","create_time":"1473760406","begin_time":"2016-09-07","end_time":"2017-01-27","expire_time":"0"},{"id":"7","youhui_id":"37","type":"0","user_name":"18701194357","user_tel":"18701194357","yname":"盛世经典牛排50元代金券","price":"50","pname":"盛世经典牛排","youhui_sn":"BX14737605156715","user_id":"193224","create_time":"1473760515","begin_time":"2016-09-07","end_time":"2017-01-27","expire_time":"0"},{"id":"8","youhui_id":"33","type":"0","user_name":"18701194357","user_tel":"18701194357","yname":"盛世经典牛排50元代金券","price":"50","pname":"盛世经典牛排","youhui_sn":"BX14737606628199","user_id":"193224","create_time":"1473760662","begin_time":"2016-09-07","end_time":"2017-01-27","expire_time":"0"},{"id":"13","youhui_id":"31","type":"1","user_name":"18701194357","user_tel":"18701194357","yname":"华莱士30元抵用券","price":"30","pname":" 肯德基（省府店）","youhui_sn":"BX14738221435314","user_id":"193224","create_time":"1473822143","begin_time":"2016-09-06","end_time":"2016-10-08","expire_time":"0"},{"id":"14","youhui_id":"30","type":"0","user_name":"18701194357","user_tel":"18701194357","yname":"麦当劳40元抵用券","price":"40","pname":" 肯德基（省府店）","youhui_sn":"BX14738221916765","user_id":"193224","create_time":"1473822191","begin_time":"2016-09-07","end_time":"2016-09-30","expire_time":"0"}]
     */

    private int code;
    private String msg;
    /**
     * id : 2
     * youhui_id : 25
     * type : 1
     * user_name : 18701194357
     * user_tel : 18701194357
     * yname : 华莱士30元抵用券
     * price : 30
     * pname :  肯德基（省府店）
     * youhui_sn : BX14734133739489
     * user_id : 193224
     * create_time : 1473413373
     * begin_time : 2016-09-06
     * end_time : 2016-10-08
     * expire_time : 0
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String youhui_id;
        private String type;
        private String user_name;
        private String user_tel;
        private String yname;
        private String price;
        private String pname;
        private String youhui_sn;
        private String user_id;
        private String create_time;
        private String begin_time;
        private String end_time;
        private String expire_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYouhui_id() {
            return youhui_id;
        }

        public void setYouhui_id(String youhui_id) {
            this.youhui_id = youhui_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getYname() {
            return yname;
        }

        public void setYname(String yname) {
            this.yname = yname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getYouhui_sn() {
            return youhui_sn;
        }

        public void setYouhui_sn(String youhui_sn) {
            this.youhui_sn = youhui_sn;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }
    }
}
