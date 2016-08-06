package com.xznn.kankanzhihu.bean;

/**
 * @author MapleDev
 * @time 16/07/29  14:45
 * @desc ${TODD}
 */
public class PostsBean {

    /**
     * count : 32
     * date : 2016-07-29
     * excerpt : 摘录了『如何评价《幻城》电视剧？』、『为什么历史上云南的存在感很低？』、『受过良好教育的穆斯林女性对伊斯兰教是什么态度？』、『独立居住是一种怎样的体验？』、『没文化有多可怕？』、『为什么少有人同情八达岭野生动物园事件的女主，而多是冷眼咒骂甚至拍手称快的回应？』、『你亲身经历过哪些听起来像段子一样的故事？』、『国内有哪些给人印象深刻的海报？』、『有哪些让你笑到肾抽筋的段子？』、『如何看待英国每日邮报的新闻：‘广州地铁上男子称呼黑人为「黑鬼」被打耳光’？』等问题下的32个答案
     * id : 2339
     * name : recent
     * pic : http://www.kanzhihu.com/wp-content/uploads/2016/07/recent-2016-07-29.jpg
     * publishtime : 1469761200
     */

    private int count;
    private String date;
    private String excerpt;
    private int id;
    private String name;
    private String pic;
    private int publishtime;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(int publishtime) {
        this.publishtime = publishtime;
    }

    @Override
    public String toString() {
        return "PostsBean{" +
                "count=" + count +
                ", date='" + date + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", publishtime=" + publishtime +
                '}';
    }
}
