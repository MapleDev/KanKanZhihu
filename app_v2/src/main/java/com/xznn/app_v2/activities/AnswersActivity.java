package com.xznn.app_v2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xznn.app_v2.AppController;
import com.xznn.app_v2.R;
import com.xznn.app_v2.adapter.AnswersAdapter;
import com.xznn.app_v2.api.APIUrl;
import com.xznn.app_v2.bean.AnswerBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswersActivity extends AppCompatActivity {

    private static final int GET_POST_ANSWERS = 0x001;
    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    private Object mJson;
    private String mRespJsonStr;

//    private MyHandle mMyHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_answers);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initData();

//        mMyHandle = new MyHandle(new WeakReference<AnswersActivity>(this));

    }



    private void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String date = intent.getStringExtra("date").replaceAll("-", "");
        String name = intent.getStringExtra("name");

        String url = String.format(APIUrl.GET_POST_ANSWERS, date, name);
//        Logger.d("=== 变量：url = " + url);

        // 使用 Volley Get Json
        getJson(url);

    }

    public void getJson(String url) {
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";
//        String url = "http://api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
                mRespJsonStr = response.toString();
                Logger.d("=== 变量：response.toString() = " + mRespJsonStr);
                if (TextUtils.isEmpty(mRespJsonStr)) {
                    return;
                }
                // 解析 JSON
                parseJsonStr(mRespJsonStr);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Logger.d("=== 变量：Error = " + error.getMessage());
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void parseJsonStr(String respJsonStr) {
        AnswerBean answerBean = new Gson().fromJson(respJsonStr, AnswerBean.class);
        if (!TextUtils.isEmpty(answerBean.getError())) {
            Logger.d("服务器提出了一个问题：" + answerBean.getError());
//            throw new IOException("服务器提出了一个问题：" + answerBean.getError());
        }
        List<AnswerBean.AnswersBean> answers = answerBean.getAnswers();
        Logger.w("===1 变量：answers = " + answers);
        EventBus.getDefault().post(answers);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandleEventBusMsg( List<AnswerBean.AnswersBean> answers ) {
        Logger.w("===2 变量：answers = " + answers);

        mRvView.setLayoutManager(new LinearLayoutManager(this));
        mRvView.setAdapter(new AnswersAdapter(answers, this));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
