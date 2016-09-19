package com.bxlwt.www.bxlwt.bean;

/**
 * Created by zhengj on 2016/8/25.
 */
public class CheckCodeBean {


    /**
     * code : 200
     * msg : 验证码正确！！！
     * data : {"smsid":"1377","type":"reg","text":"您的验证码是1234，请不要泄露给其他人，并请尽快使用。【百姓瞭望台】","code":"1234","addtime":"1472104333","edittime":"1472104333","mobile":"13436976540","userid":"0","status":"0","ip":"101.251.219.234"}
     */

    private int code;
    private String msg;
    /**
     * smsid : 1377
     * type : reg
     * text : 您的验证码是1234，请不要泄露给其他人，并请尽快使用。【百姓瞭望台】
     * code : 1234
     * addtime : 1472104333
     * edittime : 1472104333
     * mobile : 13436976540
     * userid : 0
     * status : 0
     * ip : 101.251.219.234
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String smsid;
        private String type;
        private String text;
        private String code;
        private String addtime;
        private String edittime;
        private String mobile;
        private String userid;
        private String status;
        private String ip;

        public String getSmsid() {
            return smsid;
        }

        public void setSmsid(String smsid) {
            this.smsid = smsid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
