package com.example.stelp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class yandAuth extends Activity {
    WebView webView;
    public static final String APP_PREFERENCES = "authTOKENS";
    public static final String APP_PREFERENCES_NAME = "TOKEN";

    private void saveToken(String token) {
        SharedPreferences mSettings;
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME, token);
        editor.apply();
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Pattern pattern = Pattern.compile("access_token=(.*?) ($|$)");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find())
            {
                saveToken(matcher.group(1));
                finish();
            }
            return true;
        }
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        mWebview = (WebView)findViewById(R.id.webView1);
        mWebview.setWebViewClient(new MyWebViewClient());
        String url = "https://oauth.yandex.ru/authorize?response_type=token&Client_id=f595d2a56efe4be18a83cf76265410ec";
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl(url);
    }
}
