package com.xznn.kankanzhihu.retrofit_intro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author MapleDev
 * @time 16/08/09  19:12
 * @desc ${TODD}
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}