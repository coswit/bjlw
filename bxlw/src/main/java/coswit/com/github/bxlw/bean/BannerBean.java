package coswit.com.github.bxlw.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengj on 2016/9/20.
 */
public class BannerBean implements Serializable{

    /**
     * code : 200
     * msg : 成功
     * data : [{"imgurl":"http://www.bxlwt.com//pub/upfiles/jpg/201202291637251038.jpg","title":"第1屏","url":"http://www.bxlwt.com/show-45-246614.html"},{"imgurl":"http://www.bxlwt.com//pub/upfiles/jpg/201203112037005012.jpg","title":"第2屏","url":""},{"imgurl":"http://www.bxlwt.com//pub/upfiles/jpg/201203112046051140.jpg","title":"第3屏","url":""}]
     */

    private int code;
    private String msg;
    /**
     * imgurl : http://www.bxlwt.com//pub/upfiles/jpg/201202291637251038.jpg
     * title : 第1屏
     * url : http://www.bxlwt.com/show-45-246614.html
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

    public static class DataBean implements Parcelable{
        private String imgurl;
        private String title;
        private String url;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
