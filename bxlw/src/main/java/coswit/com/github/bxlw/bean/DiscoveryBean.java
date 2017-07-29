package coswit.com.github.bxlw.bean;

import java.util.List;

/**
 * Created by zhengj on 2016/9/18.
 */
public class DiscoveryBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"1","title":"性感甜心回归","thumb":"/pub/upfiles/2016-09/201609141333505008.jpg","keyword":"穿衣","description":"跟着小野马学穿衣","poids":"1","addtime":"1473830994","endtime":"","content":"跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣","hits":"12","del":"0"}]
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * title : 性感甜心回归
     * thumb : /pub/upfiles/2016-09/201609141333505008.jpg
     * keyword : 穿衣
     * description : 跟着小野马学穿衣
     * poids : 1
     * addtime : 1473830994
     * endtime :
     * content : 跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣跟着小野马学穿衣
     * hits : 12
     * del : 0
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
        private String title;
        private String thumb;
        private String keyword;
        private String description;
        private String poids;
        private String addtime;
        private String endtime;
        private String content;
        private String hits;
        private String del;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPoids() {
            return poids;
        }

        public void setPoids(String poids) {
            this.poids = poids;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getDel() {
            return del;
        }

        public void setDel(String del) {
            this.del = del;
        }
    }
}
