package com.aaufolks.android.retrofit.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.aaufolks.android.retrofit.R;

/**
 * Created by michalisgratsias on 16/11/18.
 */
public class NewsPageFragment extends Fragment {

    private static final String ARG_URL = "news_page_url";
    private String mUrl;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static NewsPageFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_URL, url);
        NewsPageFragment fragment = new NewsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = (String) getArguments().getSerializable(ARG_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_page, container, false);

        mProgressBar = (ProgressBar) v.findViewById(R.id.fragment_news_page_progress_bar);
        mProgressBar.setMax(100); //WebChromeClient reports, in range 0-100

        mWebView = (WebView)v.findViewById(R.id.fragment_news_page_web_view);

        mWebView.getSettings().setJavaScriptEnabled(true);  // enabling Java Script

        mWebView.setWebChromeClient(new WebChromeClient() { // showing progress bar
            public void onProgressChanged(WebView webView, int newProgress) {   //int 0-100
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
                else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }

            public void onReceivedTitle(WebView webView, String title) {    // showing page title on toolbar
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.getSupportActionBar().setSubtitle(title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;   // telling webview to load the url itself w/o firing implicit intent to load with browser
            }
        });
        mWebView.loadUrl(mUrl);  // loading Url

        return v; }
}
