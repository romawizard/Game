package com.diachenko.pokergame.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.diachenko.pokergame.R;
import com.diachenko.pokergame.webview.MyWebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity implements MyWebViewClient.WebViewClientListener {

    public static final String TAG = WebViewActivity.class.getSimpleName();

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        CookieSyncManager.createInstance(this);
        initWebView();
        initSwipeRefresh();
        if (savedInstanceState == null) {
            Uri url = getIntent().getData();
            webView.loadUrl(url.toString());
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    private void initSwipeRefresh() {
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
    }

    private void initWebView() {
        MyWebViewClient client = new MyWebViewClient();
        client.setListener(this);
        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
    }

    @Override
    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageFinished() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onReceivedError(String msg) {
        Toast.makeText(this, "Oh no! " + msg, Toast.LENGTH_SHORT).show();
        swipeRefresh.setRefreshing(false);
    }
}
