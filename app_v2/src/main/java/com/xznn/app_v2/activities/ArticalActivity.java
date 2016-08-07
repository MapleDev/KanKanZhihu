package com.xznn.app_v2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;
import com.xznn.app_v2.R;
import com.xznn.app_v2.api.APIUrl;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticalActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
//        intent.putExtra("questionid", answersBean.getQuestionid());
//        intent.putExtra("answerid", answersBean.getAnswerid());
//        intent.putExtra("authorhash", answersBean.getAuthorhash());

//    问题url
//    https://m.zhihu.com/question/48337357
//    答案url
//    https://m.zhihu.com/question/49061174/answer/114657173

        Intent intent = getIntent();
        if(intent == null ) {
            return;
        }
        int questionid = intent.getIntExtra("questionid", -1);
        int answerid = intent.getIntExtra("answerid", -1);
        String authorhash = intent.getStringExtra("authorhash");

//        答案url：https://www.zhihu.com/question/questionid/answer/answerid
        final String answerUrl = String.format(APIUrl.GET_ARTICAL_ANSWER, questionid, answerid);
        Logger.d("=== 变量：answerUrl  = " + answerUrl);

        mWebView.loadUrl(answerUrl);

//        mWebView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(answerUrl);
//                return true;
//            }
//        });

//        mWebView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                titleview.setText(title);//a textview
//            }
//        });

    }
}
