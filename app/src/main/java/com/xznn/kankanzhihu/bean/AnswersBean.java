package com.xznn.kankanzhihu.bean;

/**
 * @author MapleDev
 * @time 16/07/29  22:00
 * @desc ${TODD}
 */
public class AnswersBean {
    /**
     * answerid : 61342442
     * authorhash : 54645c9d19dccccd67c7405bba8eb586
     * authorname : 琳林
     * avatar : https://pic4.zhimg.com/d870ebdcaea45bcca79fe1c6ce18aed3_l.jpg
     * questionid : 23329386
     * summary : 刮风天，下雨天，或是下雪天，你和你的另一半窝在客厅的沙发里，电视里随机播放一部你俩都爱的电影，桌子上有两杯红酒，有小零食，厨房里煲着汤，你俩依偎在一起，天南海北的聊着，越聊越投机，聊累了就睡个长长的午觉，第二天还是周末。
     * time : 2015-08-29 10:44:21
     * title : 人在什么时候最舒服？
     * vote : 8162
     */


    private int answerid;
    private String authorhash;
    private String authorname;
    private String avatar;
    private int questionid;
    private String summary;
    private String time;
    private String title;
    private int vote;

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    public String getAuthorhash() {
        return authorhash;
    }

    public void setAuthorhash(String authorhash) {
        this.authorhash = authorhash;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

}
