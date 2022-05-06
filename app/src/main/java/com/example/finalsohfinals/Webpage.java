package com.example.finalsohfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webpage extends AppCompatActivity {

    Intent url;
    WebView view;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        Intent information = getIntent();

        String url = information.getStringExtra("url");

        //Log.e("URL Received:", url);

        view = (WebView) findViewById(R.id.webpageView);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient( new WebViewClient());
        view.loadUrl(url);

    }
}