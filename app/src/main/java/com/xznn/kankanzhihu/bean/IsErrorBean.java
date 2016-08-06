package com.xznn.kankanzhihu.bean;

/**
 * @author MapleDev
 * @time 16/07/29  14:52
 * @desc ${TODD}
 */
public class IsErrorBean {

    /**
     * count : 10
     * error :
     */

    private int count;
    private String error;
    private String posts;

//    private List posts;

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

//
//    public ArrayList getPosts() {
//        return posts;
//    }
//
//    public void setPosts(ArrayList posts) {
//        this.posts = posts;
//    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

//    @Override
//    public String toString() {
//        return "IsErrorBean{" +
//                "count=" + count +
//                ", error='" + error + '\'' +
//                ", posts='" + posts + '\'' +
//                '}';
//    }


//    @Override
//    public String toString() {
//        return "IsErrorBean{" +
//                "count=" + count +
//                ", error='" + error + '\'' +
//                ", posts=" + posts +
//                '}';
//    }
}
