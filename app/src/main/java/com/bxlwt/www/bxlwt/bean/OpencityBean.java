package com.bxlwt.www.bxlwt.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/20.
 */
public class OpencityBean {

    /**
     * code : 200
     * msg : 获取成功
     * paixu : B
     * data : [{"areaid":"131","name":"唐山","enname":"ts","parentid":"3","listorder":"100","fullname":"唐山市","ji":"2","idn":"xn--sxrq5o","open":"1"},{"areaid":"46080","name":"北京","enname":"bj","parentid":"1","listorder":"0","fullname":"北京市","ji":"2","idn":"xn--1lq90i","open":"1"}]
     */

    private int code;
    private String msg;
    private String paixu;
    /**
     * areaid : 131
     * name : 唐山
     * enname : ts
     * parentid : 3
     * listorder : 100
     * fullname : 唐山市
     * ji : 2
     * idn : xn--sxrq5o
     * open : 1
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

    public String getPaixu() {
        return paixu;
    }

    public void setPaixu(String paixu) {
        this.paixu = paixu;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String areaid;
        private String name;
        private String enname;
        private String parentid;
        private String listorder;
        private String fullname;
        private String ji;
        private String idn;
        private String open;

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnname() {
            return enname;
        }

        public void setEnname(String enname) {
            this.enname = enname;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }

        public String getIdn() {
            return idn;
        }

        public void setIdn(String idn) {
            this.idn = idn;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }
    }
}
