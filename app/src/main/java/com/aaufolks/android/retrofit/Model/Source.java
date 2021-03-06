package com.aaufolks.android.retrofit.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michalisgratsias on 23/11/18.
 */
public class Source {

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public Source(String id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
