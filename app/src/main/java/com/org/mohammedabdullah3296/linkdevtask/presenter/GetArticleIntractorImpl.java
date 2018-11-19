package com.org.mohammedabdullah3296.linkdevtask.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.org.mohammedabdullah3296.linkdevtask.interfaces.GetNewsDataService;
import com.org.mohammedabdullah3296.linkdevtask.model.Article;
import com.org.mohammedabdullah3296.linkdevtask.model.ArticlesResponse;
import com.org.mohammedabdullah3296.linkdevtask.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetArticleIntractorImpl implements MainContract.GetNoticeIntractor {

    Context context;

    public GetArticleIntractorImpl(Context context1) {
        this.context = context1;
    }

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            GetNewsDataService service = RetrofitInstance.getRetrofitInstance().create(GetNewsDataService.class);
            //d80df8fef29b4abb87565d639d7b1d79
            //533af958594143758318137469b41ba9
            Call<ArticlesResponse> call = service.getNewsDataData("the-next-web",
                    "533af958594143758318137469b41ba9");
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<ArticlesResponse>() {
                @Override
                public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                    if (response.body() != null)
                        onFinishedListener.onFinished((ArrayList<Article>) response.body().getArticles());
                    else
                        onFinishedListener.onNoArticles("No articles available");
                }

                @Override
                public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        } else {
            onFinishedListener.onNoInternet("No Internet Connection ");
        }

    }

}
