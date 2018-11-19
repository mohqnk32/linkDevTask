package com.org.mohammedabdullah3296.linkdevtask.presenter;


import com.org.mohammedabdullah3296.linkdevtask.model.Article;

import java.util.ArrayList;


public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }



    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getNoticeArrayList(this);
    }


    @Override
    public void onFinished(ArrayList<Article> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

    @Override
    public void onNoInternet(String m) {
        if(mainView != null){
            mainView.onInternetFailure(m);
            mainView.hideProgress();
        }
    }

    @Override
    public void onNoArticles(String no_articles_available) {
        if(mainView != null){
            mainView.onDataFailure(no_articles_available);
            mainView.hideProgress();
        }
    }
}
