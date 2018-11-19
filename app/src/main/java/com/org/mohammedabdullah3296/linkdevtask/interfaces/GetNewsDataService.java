package com.org.mohammedabdullah3296.linkdevtask.interfaces;

import com.org.mohammedabdullah3296.linkdevtask.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetNewsDataService {

    @GET("v1/articles")
    Call<ArticlesResponse> getNewsDataData(@Query("source") String sources, @Query("apiKey") String apiKey);

}