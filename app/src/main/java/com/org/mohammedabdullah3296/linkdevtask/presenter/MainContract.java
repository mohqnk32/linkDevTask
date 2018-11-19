package com.org.mohammedabdullah3296.linkdevtask.presenter;


import com.org.mohammedabdullah3296.linkdevtask.model.Article;

import java.util.ArrayList;


public interface MainContract {


    interface presenter{

        void onDestroy();
        void requestDataFromServer();

    }


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Article> noticeArrayList);

        void onResponseFailure(Throwable throwable);
        void onInternetFailure(String msg);

        void onDataFailure(String no_articles_available);
    }

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Article> noticeArrayList);
            void onFailure(Throwable t);
            void onNoInternet(String m);
            void onNoArticles(String no_articles_available);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
}
