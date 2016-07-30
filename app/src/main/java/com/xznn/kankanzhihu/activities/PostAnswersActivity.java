package com.xznn.kankanzhihu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.xznn.kankanzhihu.R;
import com.xznn.kankanzhihu.adapter.PostsAdapter;
import com.xznn.kankanzhihu.api.APIUrl;
import com.xznn.kankanzhihu.bean.AnswersBean;
import com.xznn.kankanzhihu.bean.PostsBean;
import com.xznn.kankanzhihu.callback.AnswersBeanCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class PostAnswersActivity extends AppCompatActivity {

    private static final int GET_POST_ANSWERS = 0x001;
    @BindView(R.id.rv_view)
    RecyclerView mRvView;

    private MyHandle mMyHandle;


    private class MyHandle extends Handler {
        WeakReference<PostAnswersActivity> mWeakReference;

        public MyHandle(WeakReference<PostAnswersActivity> weakReference) {
            mWeakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_POST_ANSWERS:
                    Logger.d("=== 变量：(List<PostsBean>)msg.obj = " + (List<AnswersBean>) msg.obj);
                    mRvView.setLayoutManager(new LinearLayoutManager(PostAnswersActivity.this, LinearLayoutManager.VERTICAL, true));
                    mRvView.setAdapter(new PostsAdapter((List<PostsBean>) msg.obj, PostAnswersActivity.this));
                    break;
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_answers);
        ButterKnife.bind(this);

        initData();

        mMyHandle = new MyHandle(new WeakReference<PostAnswersActivity>(this));

    }

    private void initData() {
//        intent.putExtra("name", postsBean.getName());
//        intent.putExtra("date", postsBean.getDate());
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");

        String url = String.format(APIUrl.GET_POST_ANSWERS, name, date);
        Logger.d("=== 变量：url = " + url);
        OkHttpUtils.get()//
                .url(url)//
                .build()//
                .execute(new AnswersBeanCallBack() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Logger.e("=== 变量：Wrong:id = " + id);

                    }

                    @Override
                    public void onResponse(List<AnswersBean> response, int id) {
//                        Logger.d(id + "=== 变量：response = " + response  );
                        mMyHandle.obtainMessage(GET_POST_ANSWERS, response).sendToTarget();
                    }
                });


    }
}
