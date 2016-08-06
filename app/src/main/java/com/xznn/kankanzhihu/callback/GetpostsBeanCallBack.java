package com.xznn.kankanzhihu.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.xznn.kankanzhihu.bean.PostsBean;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Response;


/**
 * @author MapleDev
 * @time 16/07/29  16:07
 * @desc ${TODD}
 */
public abstract class GetPostsBeanCallBack extends Callback<List<PostsBean>> {

    @Override
    public List<PostsBean> parseNetworkResponse(Response response, int id) throws Exception {
        List<PostsBean> postsBeans = null;
        String respJsonStr = response.body().string();

        // Gson 解析
        Gson gson = new Gson();
        String posts = null;
        try {
            posts = new JSONObject(respJsonStr).optString("posts");
            Logger.d("=== 变量：posts  = " + posts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postsBeans = gson.fromJson(posts, new TypeToken<List<PostsBean>>() {}.getType());
        Logger.d("=== 变量：postsBeans = " + postsBeans);


        // FastJson 解析 ok
//        IsErrorBean isErrorBean1 = JSON.parseObject(respJsonStr, IsErrorBean.class);
//        if (TextUtils.isEmpty(isErrorBean1.getError())) {
//            postsBeans = JSONArray.parseArray(isErrorBean1.getPosts(), PostsBean.class);
//            Logger.d("=== 变量：postsBeen = " + postsBeans);
//        }

        return postsBeans;
    }
}

// Demo
/*
public abstract class UserCallback extends Callback<User>
{
    @Override
    public User parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        User user = new Gson().fromJson(string, User.class);
        return user;
    }
}
*/
