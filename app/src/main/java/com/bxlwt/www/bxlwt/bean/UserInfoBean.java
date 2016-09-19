package com.bxlwt.www.bxlwt.bean;

/**
 * Created by zhengj on 2016/8/22.
 */
public class UserInfoBean {


    /**
     * code : 200
     * token : 5dpi2l4bjc7v333hps6p6ha4g4
     * account : 18701194354
     * user_id : 214696
     * msg : 用户信息添加成功！！！
     * status : 0
     * type :
     * data : {"userid":"214696","username":"18701194354","mobile":"18701194354","cardnum":"","point":"0","money":"0.00","qq":"","truename":"","type":"","status":"0","face":"http://www.bxlwt.com/uploads/face.png","nickname":"18701194354","face_s":"http://www.bxlwt.com/uploads/face.png"}
     */

    private int code;
    private String token;
    private String account;
    private String user_id;
    private String msg;
    private String status;
    private String type;
    /**
     * userid : 214696
     * username : 18701194354
     * mobile : 18701194354
     * cardnum :
     * point : 0
     * money : 0.00
     * qq :
     * truename :
     * type :
     * status : 0
     * face : http://www.bxlwt.com/uploads/face.png
     * nickname : 18701194354
     * face_s : http://www.bxlwt.com/uploads/face.png
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String userid;
        private String username;
        private String mobile;
        private String cardnum;
        private String point;
        private String money;
        private String qq;
        private String truename;
        private String type;
        private String status;
        private String face;
        private String nickname;
        private String face_s;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace_s() {
            return face_s;
        }

        public void setFace_s(String face_s) {
            this.face_s = face_s;
        }
    }
}
