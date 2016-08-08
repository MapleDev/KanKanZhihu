package com.xznn.app_v2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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

        getWindow().requestFeature(Window.FEATURE_PROGRESS);


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

        // Let's display the progress in the activity title bar, like the
        // browser app does.

        mWebView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebView.loadUrl(answerUrl);

        //mWebView.loadUrl(answerUrl);

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
