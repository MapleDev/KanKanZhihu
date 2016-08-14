package com.xznn.kankanzhihu.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xznn.kankanzhihu.R;
import com.xznn.kankanzhihu.adapter.HeaderAndFooterRecyclerViewWrapper;
import com.xznn.kankanzhihu.adapter.PostsAdapter;
import com.xznn.kankanzhihu.api.APIUrl;
import com.xznn.kankanzhihu.bean.PostBean;
import com.xznn.kankanzhihu.retrofit_demo.PostInfo;
import com.xznn.kankanzhihu.retrofit_demo.PostService;
import com.xznn.kankanzhihu.retrofit_demo.RetrofitWrapper;
import com.xznn.kankanzhihu.retrofit_intro.GitHubService;
import com.xznn.kankanzhihu.retrofit_intro.Repo;
import com.xznn.kankanzhihu.ui.MySlidingMenu;
import com.xznn.kankanzhihu.utils.HttpUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends AppCompatActivity {

    private static final int GET_POSTSBEAN = 0x001;
    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.sliding_menu_layout)
    MySlidingMenu mSlidingMenuLayout;

    private MyHandle mMyHandle;
    private HeaderAndFooterRecyclerViewWrapper mWrapperAdapter;
    private List<PostBean.PostsBean> mList;
    private PostsAdapter mPostsAdapter;

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        initView();
        initData();
        mMyHandle = new MyHandle(new WeakReference<PostsActivity>(this));


        long currentTimeMillis = System.currentTimeMillis();

        // 下拉刷新
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                new Thread(new Runnable() {
//                    public void run() {
//                        SystemClock.sleep(10000);
//                    }
//                }).start();

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefresh.setRefreshing(false);

                            }
                        });
                    }
                }, 3000);
                Snackbar.make(mRvView, "正在刷新。。。", Snackbar.LENGTH_LONG).show();

                TextView textView = new TextView(PostsActivity.this);
                textView.setText("正在刷新...Header");
                mWrapperAdapter.addHeaderView(textView);
                mWrapperAdapter.notifyDataSetChanged();


            }
        });

        // Retrofit
        //1 ====================================
        PostService postService = RetrofitWrapper.getInstance().create(PostService.class);
        retrofit2.Call<PostInfo> postInfoCall = postService.listCount(1);
        postInfoCall.enqueue(new retrofit2.Callback<PostInfo>() {
            @Override
            public void onResponse(retrofit2.Call<PostInfo> call, retrofit2.Response<PostInfo> response) {
                PostInfo body = response.body();
                Logger.w("====== 所在方法：onResponse ======" + body.getPosts().toString());
//                Snackbar.make(mRvView, body.getPosts().toString(), Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(retrofit2.Call<PostInfo> call, Throwable t) {
                Logger.d("====== 所在方法：onFailure ======");
                Snackbar.make(mRvView, t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });

        //1 ====================================

        //2 ====================================

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build();

        GitHubService service = retrofit.create(GitHubService.class);
        retrofit2.Call<List<Repo>> listCall = service.listRepos("mapledev");
        listCall.enqueue(new retrofit2.Callback<List<Repo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Repo>> call, retrofit2.Response<List<Repo>> response) {
                List<Repo> repos = response.body();
//                for (Repo t : repos) {
//                Logger.w("====== 所在方法：onResponse ======" + t.getName());
//                }

            }

            @Override
            public void onFailure(retrofit2.Call<List<Repo>> call, Throwable t) {
                Logger.d("====== 所在方法：onFailure ======");
            }
        });
        //2 ====================================
    }


    private boolean checknew(long currentTimeMillis) {
        return true;
    }

    private void initView() {
        mRvView.setLayoutManager(new LinearLayoutManager(this));
//                    mRvView.setAdapter(new PostsAdapter(list, PostsActivity.this));
        mPostsAdapter = new PostsAdapter(this);
        mWrapperAdapter =  new HeaderAndFooterRecyclerViewWrapper(mPostsAdapter);
        mRvView.setAdapter(mWrapperAdapter);
    }

    private void initData() {
//        Logger.d("=== 变量：currentTimeMillis  = " + currentTimeMillis);
//        String url = APIUrl.GET_POSTS +"/" +currentTimeMillis;

        final String url = APIUrl.GET_POSTS;
        HttpUtil.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e("====== 所在方法：onFailure ======");
//                Toast.makeText(PostsActivity.this, "网络提出了一个问题。", Toast.LENGTH_SHORT).show();
                Snackbar.make(mRvView, "网络提出了一个问题 > <", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Logger.d("服务器提出了一个问题。");
                    throw new IOException("Unexpected code " + response);
                }
                String string = response.body().string();
//                Logger.d("=== 变量：string = " + string);
                PostBean postBean = new Gson().fromJson(string, PostBean.class);
                if (!TextUtils.isEmpty(postBean.getError())) {
                    Logger.d("网络提出了一个问题。：" + postBean.getError());
                    throw new IOException("网络提出了一个问题。：" + postBean.getError());
                }
                mMyHandle.obtainMessage(GET_POSTSBEAN, postBean.getPosts()).sendToTarget();
            }
        });

    }


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

//                    Logger.d("=== 变量：(List<PostsBean>)msg.obj = " + list);
//                    mRvView.setLayoutManager(new LinearLayoutManager(PostsActivity.this));
////                    mRvView.setAdapter(new PostsAdapter(list, PostsActivity.this));
//                    HeaderAndFooterRecyclerViewWrapper mAdapter =  new HeaderAndFooterRecyclerViewWrapper(new PostsAdapter(list, PostsActivity.this));
//                    mRvView.setAdapter(mAdapter);

                    mList = (List<PostBean.PostsBean>) msg.obj;
                    mPostsAdapter.setData(mList);
                    mWrapperAdapter.notifyDataSetChanged();
                    break;
            }
        }


    }



}


