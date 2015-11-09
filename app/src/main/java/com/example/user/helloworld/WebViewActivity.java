package com.example.user.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebViewActivity extends Activity {

    private WebView mWebview;
    private EditText mEdtUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebview = (WebView) findViewById(R.id.webView);
        mEdtUrl = (EditText) findViewById(R.id.editText);

        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new MyWebViewClient());
        mWebview.setWebChromeClient(new WebBrowserClient());
        mWebview.addJavascriptInterface(new JavaScriptMethods(), "sample");
        mWebview.loadUrl("file:///android_asset/www/hello.html");
    }

    public void btnUrlMove(View view) {
        String url = mEdtUrl.getText().toString();

        if(!url.startsWith("http://")) {
            url = "http://" + url;
        }

        mWebview.loadUrl(url);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtUrl.getWindowToken(), 0);
    }

    private class MyWebViewClient extends WebViewClient {

        // url 이 로딩되는 순간 호출됨
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("TEST", url);

//            return url.indexOf("daum.net") > 0;

            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private class WebBrowserClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm(); // JS 알럿을 실행한다.
            return super.onJsAlert(view, url, message, result);
        }
    }

    private class JavaScriptMethods {

        private boolean isFace = false;

        public JavaScriptMethods() {
        }

        // html 의 javascript 에서 호출하는 Method 명
        @android.webkit.JavascriptInterface
        public void clickOnFace() {
            mWebview.post(new Runnable() {
                @Override
                public void run() {
                    // 안드로이드에서 --> HTML JS 의 메소드를 실행한다.

                    int faceInt = isFace == true?1:0;
                    mWebview.loadUrl("javascript:changeFace("+faceInt+");");
                    isFace = !isFace;
                }
            });
        }
    }
}
