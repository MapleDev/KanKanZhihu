package com.xznn.kankanzhihu.api;

/**
 * @author MapleDev
 * @time 16/07/29  14:55
 * @desc ${TODD}
 */
public class APIUrl {
    //获取「看知乎」首页文章列表，每次取10篇。
    public static final String GET_POSTS = "http://api.kanzhihu.com/getposts";
    //获取单篇文章的答案列表
    public static final String GET_POST_ANSWERS = "http://api.kanzhihu.com/getpostanswers/%s/%s";
//    问题url
//    https://m.zhihu.com/question/48337357
    public static final String GET_ARTICAL_QUESTION = "http://m.zhihu.com/question/%s";
//    答案url
//    https://m.zhihu.com/question/49061174/answer/114657173
    public static final String GET_ARTICAL_ANSWER = "http://m.zhihu.com/question/%s/answer/%s";


}
