package com.xznn.kankanzhihu.callback;

import com.xznn.kankanzhihu.bean.AnswersBean;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * @author MapleDev
 * @time 16/07/29  22:10
 * @desc ${TODD}
 */
public abstract class AnswersBeanCallBack extends Callback<List<AnswersBean>> {

    @Override
    public List<AnswersBean> parseNetworkResponse(Response response, int id) throws Exception {
        List<AnswersBean> getBeens = null;
        String string = response.body().string();
//        IsErrorBean isErrorBean = JSON.parseObject(string, IsErrorBean.class);
//        if (TextUtils.isEmpty(isErrorBean.getError())) {
//            getBeens = JSON.parseArray(isErrorBean.getPosts(), AnswersBean.class);
//        }
        return getBeens;
    }
}
