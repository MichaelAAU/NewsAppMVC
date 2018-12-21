package com.aaufolks.android.retrofit.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by michalisgratsias on 23/11/18.
 */
public class RetrofitClientInstance { // HTTP-REST Client for Java/Android

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org";

    public static Retrofit getRetrofitInstance() {  //uses Builder and Interface to define
        if (retrofit == null) {                     //the URL endpoint for HTTP operations
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //gson converter
                    .build();      //for parsing JSON data to Java objects and vise versa
        }
        return retrofit;
    }
}
