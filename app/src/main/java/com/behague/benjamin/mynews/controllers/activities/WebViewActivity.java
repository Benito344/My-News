package com.behague.benjamin.mynews.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.behague.benjamin.mynews.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView;
        webView = findViewById(R.id.webview_activity_WV);

        Intent intent = getIntent();
        webView.getSettings().setJavaScriptEnabled(true);

        //It for display navigator in application
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});

        webView.loadUrl(intent.getStringExtra("URL"));
    }
}
