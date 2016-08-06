package com.xznn.kankanzhihu.activities;

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
import com.xznn.kankanzhihu.bean.PostsBean;
import com.xznn.kankanzhihu.callback.GetPostsBeanCallBack;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class PostsActivity extends AppCompatActivity {

    private static final int GET_POSTSBEAN = 0x001;
    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    private MyHandle mMyHandle;


    private class MyHandle extends Handler {
        WeakReference<PostsActivity> mWeakReference;

        public MyHandle(WeakReference<PostsActivity> weakReference) {
            mWeakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_POSTSBEAN:
                    List<PostsBean> list = (List<PostsBean>) msg.obj;

                    Logger.d("=== 变量：(List<PostsBean>)msg.obj = " + list);
                    mRvView.setLayoutManager(new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.VERTICAL, true));
                    mRvView.setAdapter(new PostsAdapter(list, PostsActivity.this));
                    break;
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        initData();

        mMyHandle = new MyHandle(new WeakReference<PostsActivity>(this));

    }

    private void initData() {
        long currentTimeMillis = System.currentTimeMillis();
        Logger.d("=== 变量：currentTimeMillis  = " + currentTimeMillis);

//        String url = APIUrl.GET_POSTS +"/" +currentTimeMillis;
        final String url = APIUrl.GET_POSTS;


        OkHttpUtils.get()//
                .url(url)//
                .build()//
                .execute(new GetPostsBeanCallBack() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Logger.e("=== 变量：Wrong:id = " + id);

                    }

                    @Override
                    public void onResponse(List<PostsBean> response, int id) {
//                        Logger.d("=== 变量：response = " + response);
                        mMyHandle.obtainMessage(GET_POSTSBEAN, response).sendToTarget();
                    }
                });


    }
}
