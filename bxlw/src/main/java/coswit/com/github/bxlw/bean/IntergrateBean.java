package coswit.com.github.bxlw.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/19.
 */
public class IntergrateBean  {

    /**
     * code : 200
     * msg : 成功
     * zjf : 15
     * data : [{"intid":"30382","inttype":"","intpoint":"0","point":"5","addtime":"1469498969","mod":"user","reason":"用户签到加积分","ip":"101.254.160.119","userid":"193224","admin":"","username":"","experience":"5","taskid":"3","type":"签到"},{"intid":"30519","inttype":"","intpoint":"0","point":"10","addtime":"1473327539","mod":"user","reason":"用户注册加积分","ip":"101.254.160.120","userid":"193224","admin":"","username":"","experience":"10","taskid":"2","type":"用户注册"}]
     */

    private int code;
    private String msg;
    private String zjf;
    /**
     * intid : 30382
     * inttype :
     * intpoint : 0
     * point : 5
     * addtime : 1469498969
     * mod : user
     * reason : 用户签到加积分
     * ip : 101.254.160.119
     * userid : 193224
     * admin :
     * username :
     * experience : 5
     * taskid : 3
     * type : 签到
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

    public String getZjf() {
        return zjf;
    }

    public void setZjf(String zjf) {
        this.zjf = zjf;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String intid;
        private String inttype;
        private String intpoint;
        private String point;
        private String addtime;
        private String mod;
        private String reason;
        private String ip;
        private String userid;
        private String admin;
        private String username;
        private String experience;
        private String taskid;
        private String type;

        public String getIntid() {
            return intid;
        }

        public void setIntid(String intid) {
            this.intid = intid;
        }

        public String getInttype() {
            return inttype;
        }

        public void setInttype(String inttype) {
            this.inttype = inttype;
        }

        public String getIntpoint() {
            return intpoint;
        }

        public void setIntpoint(String intpoint) {
            this.intpoint = intpoint;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getMod() {
            return mod;
        }

        public void setMod(String mod) {
            this.mod = mod;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
