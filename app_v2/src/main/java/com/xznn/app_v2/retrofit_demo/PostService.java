package com.xznn.app_v2.retrofit_demo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author MapleDev
 * @time 16/08/09  16:43
 * @desc ${TODD}
 */
public interface PostService {
    @GET("/getposts")
    Call<PostInfo> listCount(@Query("count") int count);

}