package com.bxlwt.www.bxlwt.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/19.
 */
public class MymessageBean {

    /**
     * code : 200
     * msg : 成功
     * page : {"pageSize":"20","pageNo":"1","count":"1"}
     * data : {"list":[{"imid":"6088","imtype":"系统消息","xtype":null,"imcontent":"欢迎注册百姓瞭望台","imaddtime":"2016-09-13","tousername":"","touserid":"214821","pdscription":"","thumb":"","url":"","isread":"0"}]}
     */

    private int code;
    private String msg;
    /**
     * pageSize : 20
     * pageNo : 1
     * count : 1
     */

    private PageBean page;
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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class PageBean {
        private String pageSize;
        private String pageNo;
        private String count;

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

    public static class DataBean {
        /**
         * imid : 6088
         * imtype : 系统消息
         * xtype : null
         * imcontent : 欢迎注册百姓瞭望台
         * imaddtime : 2016-09-13
         * tousername :
         * touserid : 214821
         * pdscription :
         * thumb :
         * url :
         * isread : 0
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String imid;
            private String imtype;
            private Object xtype;
            private String imcontent;
            private String imaddtime;
            private String tousername;
            private String touserid;
            private String pdscription;
            private String thumb;
            private String url;
            private String isread;

            public String getImid() {
                return imid;
            }

            public void setImid(String imid) {
                this.imid = imid;
            }

            public String getImtype() {
                return imtype;
            }

            public void setImtype(String imtype) {
                this.imtype = imtype;
            }

            public Object getXtype() {
                return xtype;
            }

            public void setXtype(Object xtype) {
                this.xtype = xtype;
            }

            public String getImcontent() {
                return imcontent;
            }

            public void setImcontent(String imcontent) {
                this.imcontent = imcontent;
            }

            public String getImaddtime() {
                return imaddtime;
            }

            public void setImaddtime(String imaddtime) {
                this.imaddtime = imaddtime;
            }

            public String getTousername() {
                return tousername;
            }

            public void setTousername(String tousername) {
                this.tousername = tousername;
            }

            public String getTouserid() {
                return touserid;
            }

            public void setTouserid(String touserid) {
                this.touserid = touserid;
            }

            public String getPdscription() {
                return pdscription;
            }

            public void setPdscription(String pdscription) {
                this.pdscription = pdscription;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIsread() {
                return isread;
            }

            public void setIsread(String isread) {
                this.isread = isread;
            }
        }
    }
}
