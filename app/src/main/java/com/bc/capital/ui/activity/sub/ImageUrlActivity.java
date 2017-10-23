package com.bc.capital.ui.activity.sub;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseActivity;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ImageUrlActivity extends BaseActivity {
    private WebView webView;
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        _context = ImageUrlActivity.this;
        setContentView(R.layout.activity_imageurl);
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    protected void bindEvent() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放

    }

    @Override
    protected void initData() {
        init();
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键


                        webView.goBack();   //后退

                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }
    private void init() {
        String bannersLinkUrl = null;
        if (getIntent()!=null) {
            bannersLinkUrl = getIntent().getStringExtra("bannersLinkUrl");
        }
        Log.e("TAG", "======================="+bannersLinkUrl);
        //WebView加载web资源
        webView.loadUrl(bannersLinkUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
