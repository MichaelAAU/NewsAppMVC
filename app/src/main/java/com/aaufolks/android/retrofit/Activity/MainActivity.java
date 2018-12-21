package com.aaufolks.android.retrofit.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaufolks.android.retrofit.Model.Articles;
import com.aaufolks.android.retrofit.Model.NewsData;
import com.aaufolks.android.retrofit.Network.GetDataService;
import com.aaufolks.android.retrofit.Network.RetrofitClientInstance;
import com.aaufolks.android.retrofit.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by michalisgratsias on 23/11/18.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CustomAdapter1 adapter;
    private RecyclerView recyclerView;
    ProgressDialog mProgressDialog;
    private static final String API_KEY = "97335f9ae4c44ca5826bf2cf59e87824";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<NewsData> call = service.getAllData(API_KEY, "us");
        call.enqueue(new Callback<NewsData>() { //asynchronously sends the request and notifies your app
            @Override                                   // with a callback when a response comes back.
            public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                mProgressDialog.dismiss();
                Log.i(TAG, "Got a response: " + response);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<NewsData> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(NewsData newsData) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter1(this, newsData.getArticles());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.CustomViewHolder> {

        private Context context;
        private List<Articles> articles;

        public CustomAdapter1(Context context, List<Articles> articles){
            this.context = context;
            this.articles = articles;
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public final View mView;
            private Articles mNewsItem;
            private TextView txtTitle;
            private ImageView coverImage;

            CustomViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                mView = itemView;
                txtTitle = mView.findViewById(R.id.title);
                coverImage = mView.findViewById(R.id.coverImage);
            }

            public void bindNewsItem(Articles item) {
                mNewsItem = item;
            }

            @Override
            public void onClick(View v) {
                Log.i(TAG, "View clicked");
                Intent i = NewsPageActivity.newIntent(context, mNewsItem.getUrl());
                context.startActivity(i);
            }
        }

        @Override
        public CustomAdapter1.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
            return new CustomAdapter1.CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter1.CustomViewHolder holder, int position) {
            holder.txtTitle.setText(articles.get(position).getTitle());
            holder.bindNewsItem(articles.get(position));

            Picasso.Builder builder = new Picasso.Builder(context); //for async downloading
            builder.downloader(new OkHttp3Downloader(context)); //efficient library for HTTP requests
            builder.build().load(articles.get(position).getUrlToImage()) //url of image to download
                    .placeholder((R.drawable.breaking_news)) //display until download
                    .error(R.drawable.breaking_news)
                    .into(holder.coverImage); //view to load image into

        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }
}
