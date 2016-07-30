package com.xznn.kankanzhihu.callback;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.xznn.kankanzhihu.bean.PostsBean;
import com.xznn.kankanzhihu.bean.IsErrorBean;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;


/**
 * @author MapleDev
 * @time 16/07/29  16:07
 * @desc ${TODD}
 */
public abstract class GetpostsBeanCallBack extends Callback<List<PostsBean>> {

    @Override
    public List<PostsBean> parseNetworkResponse(Response response, int id) throws Exception {
        List<PostsBean> getpostsBeen = null;
        String string = response.body().string();
        IsErrorBean isErrorBean = JSON.parseObject(string, IsErrorBean.class);
        if (TextUtils.isEmpty(isErrorBean.getError())) {
            getpostsBeen = JSON.parseArray(isErrorBean.getResult(), PostsBean.class);
        }
        return getpostsBeen;
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
