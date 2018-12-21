package com.aaufolks.android.retrofit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by michalisgratsias on 23/11/18.
 */
public class NewsData { //model class for json data

    @SerializedName("status")
    private String mStatus;
    @SerializedName("totalResults")
    private int mTotalResults;
    @SerializedName("articles")
    private ArrayList<Articles> mArticles;

    public NewsData(String status, int totalResults, ArrayList<Articles> articles) {
        this.mStatus = status;
        this.mTotalResults = totalResults;
        this.mArticles = articles;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public ArrayList<Articles> getArticles() {
        return mArticles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        mArticles = articles;
    }
}
