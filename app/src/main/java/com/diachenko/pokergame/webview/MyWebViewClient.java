package com.diachenko.pokergame.webview;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyWebViewClient extends WebViewClient {

    private WebViewClientListener listener;


    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        //Users will be notified in case there's an error (i.e. no internet connection)
        if (listener != null){
            listener.onReceivedError(description);
        }

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        CookieSyncManager.getInstance().sync();
        if (listener != null){
            listener.onPageFinished();
        }
    }

    public void setListener(WebViewClientListener listener) {
        this.listener = listener;
    }

    public interface WebViewClientListener{
        void onPageFinished();

        void onReceivedError(String msg);
    }
}
