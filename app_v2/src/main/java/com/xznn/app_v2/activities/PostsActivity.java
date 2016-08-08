package com.xznn.app_v2.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xznn.app_v2.R;
import com.xznn.app_v2.adapter.PostsAdapter;
import com.xznn.app_v2.api.APIUrl;
import com.xznn.app_v2.bean.PostBean;
import com.xznn.app_v2.utils.HttpUtil;

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

public class PostsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int GET_POSTSBEAN = 0x001;
    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private MyHandle mMyHandle;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/

        initData();
        mMyHandle = new MyHandle(new WeakReference<PostsActivity>(this));


        // 下拉刷新
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*new Thread(new Runnable() {
                    public void run() {
                        SystemClock.sleep(10000);
                    }
                }).start();*/
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
                }, 5000);
                Snackbar.make(mRvView, "正在刷新。。。", Snackbar.LENGTH_LONG).show();
            }
        });


    }

    private void initData() {
        long currentTimeMillis = System.currentTimeMillis();
//        Logger.d("=== 变量：currentTimeMillis  = " + currentTimeMillis);
//        String url = APIUrl.GET_POSTS +"/" +currentTimeMillis;

        final String url = APIUrl.GET_POSTS;
        HttpUtil.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.w("====== 所在方法：onFailure ======");
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
                    List<PostBean.PostsBean> list = (List<PostBean.PostsBean>) msg.obj;

                    Logger.d("=== 变量：(List<PostsBean>)msg.obj = " + list);
                    mRvView.setLayoutManager(new LinearLayoutManager(PostsActivity.this));
                    mRvView.setAdapter(new PostsAdapter(list, PostsActivity.this));

                    break;
            }
        }


    }

}


