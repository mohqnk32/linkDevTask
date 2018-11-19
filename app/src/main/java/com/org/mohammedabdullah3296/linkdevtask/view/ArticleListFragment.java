package com.org.mohammedabdullah3296.linkdevtask.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.org.mohammedabdullah3296.linkdevtask.R;
import com.org.mohammedabdullah3296.linkdevtask.adapter.ArticleAdapter;
import com.org.mohammedabdullah3296.linkdevtask.interfaces.ArticleClick;
import com.org.mohammedabdullah3296.linkdevtask.model.Article;
import com.org.mohammedabdullah3296.linkdevtask.presenter.GetArticleIntractorImpl;
import com.org.mohammedabdullah3296.linkdevtask.presenter.MainContract;
import com.org.mohammedabdullah3296.linkdevtask.presenter.MainPresenterImpl;

import java.util.ArrayList;

public class ArticleListFragment extends Fragment implements MainContract.MainView,
        ArticleClick {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MainContract.presenter presenter;
    public   ArrayList<Article> articles;

    OnStepClickListener mCallback;

    public interface OnStepClickListener {
        void OnArticleClickListener(int position);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArticleListFragment() {
        Log.e(">>>>>", "Hello mohammed !");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_article_list, container, false);
        initializeToolbarAndRecyclerView(rootView);
        initProgressBar(rootView);
        presenter = new MainPresenterImpl(this, new GetArticleIntractorImpl(getContext()));
        presenter.requestDataFromServer();
        return rootView;
    }

    private void initializeToolbarAndRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.main_news);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initProgressBar(View view) {
        progressBar = view.findViewById(R.id.loading_indicator);
        progressBar.setIndeterminate(true);
       // progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setDataToRecyclerView(ArrayList<Article> articleArrayList) {
        articles = articleArrayList;
       // Toast.makeText(getContext(), articles.get(0).getPublishedAt(), Toast.LENGTH_SHORT).show();
        ArticleAdapter adapter = new ArticleAdapter(this);
        adapter.setArticleData(articleArrayList, getContext());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(),
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInternetFailure(String msg) {
        Toast.makeText(getContext(),
                "Something went wrong...Error message: " + msg,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDataFailure(String no_articles_available) {
        Toast.makeText(getContext(),
                "Error message: " + no_articles_available,
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        mCallback.OnArticleClickListener(clickedItemIndex);
    }
}
