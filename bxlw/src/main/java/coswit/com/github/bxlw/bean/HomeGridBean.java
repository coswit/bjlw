package coswit.com.github.bxlw.bean;

import java.io.Serializable;

/**
 * Created by zhengj on 2016/9/18.
 */
public class HomeGridBean implements Serializable{


    /**
     * id : 394
     * catename : 美容化妆
     * parentid : 0
     * listorder : 0
     * isshow : 1
     * img : /uploads/ico/1.png
     */

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
