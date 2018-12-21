package com.aaufolks.android.retrofit.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.aaufolks.android.retrofit.Activity.NewsPageFragment;

/**
 * Created by michalisgratsias on 16/11/18.
 */
public class NewsPageActivity extends SingleFragmentActivity {

    private static final String EXTRA_NEWS_URL = "com.aaufolks.android.newsapp.newsurl";

    public static Intent newIntent(Context context, String newsPageUrl) {
        Intent i = new Intent(context, NewsPageActivity.class);
        i.putExtra(EXTRA_NEWS_URL, newsPageUrl);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        String url = (String) getIntent().getSerializableExtra(EXTRA_NEWS_URL);
        return NewsPageFragment.newInstance(url);
    }
}
