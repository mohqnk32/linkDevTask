package com.org.mohammedabdullah3296.linkdevtask.network;

import com.org.mohammedabdullah3296.linkdevtask.utils.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org/";
    private static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


