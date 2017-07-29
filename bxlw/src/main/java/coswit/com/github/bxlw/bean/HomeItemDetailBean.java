package coswit.com.github.bxlw.bean;

/**
 * Created by zhengj on 2016/9/12.
 */
public class HomeItemDetailBean {

    /**
     * code : 200
     * msg : 成功
     * data : {"id":"24","name":"麦当劳40元抵用券","icon":"/pub/upfiles/2016-09/201609070951094893.jpg","image":"/pub/upfiles/2016-09/201609071531039745.jpg","cate_id":"396","begin_time":"2016-09-07","end_time":"2016-09-30","price":"40","expire_day":"10","areaid":"46080","youhui_type":"0","total_fee":"10","create_time":"1422726137","is_recommend":"1","description":"麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券","pname":" 肯德基（省府店）","user_tel":"","address":"鼓楼区八一七北路68号福建供销大厦二楼 ","score_limit":"5","ynum":"899","lnum":"1","del":"0"}
     */

    private int code;
    private String msg;
    /**
     * id : 24
     * name : 麦当劳40元抵用券
     * icon : /pub/upfiles/2016-09/201609070951094893.jpg
     * image : /pub/upfiles/2016-09/201609071531039745.jpg
     * cate_id : 396
     * begin_time : 2016-09-07
     * end_time : 2016-09-30
     * price : 40
     * expire_day : 10
     * areaid : 46080
     * youhui_type : 0
     * total_fee : 10
     * create_time : 1422726137
     * is_recommend : 1
     * description : 麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券麦当劳40元抵用券
     * pname :  肯德基（省府店）
     * user_tel :
     * address : 鼓楼区八一七北路68号福建供销大厦二楼
     * score_limit : 5
     * ynum : 899
     * lnum : 1
     * del : 0
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
        private String id;
        private String name;
        private String icon;
        private String image;
        private String cate_id;
        private String begin_time;
        private String end_time;
        private String price;
        private String expire_day;
        private String areaid;
        private String youhui_type;
        private String total_fee;
        private String create_time;
        private String is_recommend;
        private String description;
        private String pname;
        private String user_tel;
        private String address;
        private String score_limit;
        private String ynum;
        private String lnum;
        private String del;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getExpire_day() {
            return expire_day;
        }

        public void setExpire_day(String expire_day) {
            this.expire_day = expire_day;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getYouhui_type() {
            return youhui_type;
        }

        public void setYouhui_type(String youhui_type) {
            this.youhui_type = youhui_type;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getScore_limit() {
            return score_limit;
        }

        public void setScore_limit(String score_limit) {
            this.score_limit = score_limit;
        }

        public String getYnum() {
            return ynum;
        }

        public void setYnum(String ynum) {
            this.ynum = ynum;
        }

        public String getLnum() {
            return lnum;
        }

        public void setLnum(String lnum) {
            this.lnum = lnum;
        }

        public String getDel() {
            return del;
        }

        public void setDel(String del) {
            this.del = del;
        }
    }
}
