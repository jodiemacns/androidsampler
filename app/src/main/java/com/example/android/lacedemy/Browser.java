package com.example.android.lacedemy;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by jodie on 20/01/17.
 */

public class Browser extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        WebView web = (WebView)findViewById(R.id.webView);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://www.google.ca");
    }
}
