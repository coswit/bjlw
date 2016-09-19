package com.bxlwt.www.bxlwt.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/18.
 */
public class HomeGridBean {


    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"394","catename":"美容化妆","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/1.png"},{"id":"395","catename":"家居日常","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/2.png"},{"id":"396","catename":"食品酒水","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/3.png"},{"id":"397","catename":"母婴玩具","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/4.png"},{"id":"398","catename":"服装鞋帽","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/5.png"},{"id":"399","catename":"手机数码","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/6.png"},{"id":"400","catename":"家用电器","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/7.png"},{"id":"401","catename":"其他分类","parentid":"0","listorder":"0","isshow":"1","img":"/uploads/ico/8.png"}]
     */

    private int code;
    private String msg;
    /**
     * id : 394
     * catename : 美容化妆
     * parentid : 0
     * listorder : 0
     * isshow : 1
     * img : /uploads/ico/1.png
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
        private String catename;
        private String parentid;
        private String listorder;
        private String isshow;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
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

        public String getIsshow() {
            return isshow;
        }

        public void setIsshow(String isshow) {
            this.isshow = isshow;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
