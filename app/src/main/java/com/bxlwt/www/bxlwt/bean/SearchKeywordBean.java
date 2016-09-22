package com.bxlwt.www.bxlwt.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/21.
 */
public class SearchKeywordBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"11745","keywords":"盛世","model":"app","count":"28"},{"id":"11749","keywords":"肯德基","model":"app","count":"18"},{"id":"11751","keywords":"优惠券","model":"app","count":"6"},{"id":"11750","keywords":"麦当劳","model":"app","count":"3"}]
     */

    private int code;
    private String msg;
    /**
     * id : 11745
     * keywords : 盛世
     * model : app
     * count : 28
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
        private String keywords;
        private String model;
        private String count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
