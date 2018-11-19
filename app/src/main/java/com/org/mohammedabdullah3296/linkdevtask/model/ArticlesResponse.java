package com.org.mohammedabdullah3296.linkdevtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse {
    @SerializedName("status")
    String status ;
    @SerializedName("source")
    String source ;
    @SerializedName("sortBy")
    String sortBy ;
    @SerializedName("articles")
    List<Article> articles ;

    public ArticlesResponse(String status, String source, String sortBy, List<Article> articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
