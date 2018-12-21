package com.aaufolks.android.retrofit.Model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by michalisgratsias on 23/11/18.
 */
public class Articles {

    @SerializedName("source")
    private Source mSource;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("urlToImage")
    private String mUrlToImage;
    @SerializedName("publishedAt")
    private String mPublishedAt;
    @SerializedName("content")
    private String mContent;

    public Articles(Source source, String author, String title, String description,
                    String url, String urlToImage, String publishedAt, String content) {
        this.mSource = source;
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mUrlToImage = urlToImage;
        this.mPublishedAt = publishedAt;
        this.mContent = content;
    }

    public Source getSource() {
        return mSource;
    }

    public void setSource(Source source) {
        mSource = source;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        mUrlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}


