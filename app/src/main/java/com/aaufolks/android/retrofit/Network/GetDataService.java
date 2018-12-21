package com.aaufolks.android.retrofit.Network;

import com.aaufolks.android.retrofit.Model.NewsData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by michalisgratsias on 23/11/18.
 */
public interface GetDataService { //Defines the possible HTTP operations eg. to get data, and endpoints

    @GET("/v2/top-headlines") //method represents an API call which returns a call object with the expected result
    Call<NewsData> getAllData(@Query("apiKey") String api, @Query("country") String country);
}
