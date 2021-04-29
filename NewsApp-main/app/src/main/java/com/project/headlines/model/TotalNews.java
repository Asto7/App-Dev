package com.project.headlines.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TotalNews {

    @NonNull
    @SerializedName("articles")
    private List<News> newsList;

    public TotalNews() {
    }

    @NonNull
    public List<News> getNewsList() {
        return newsList;
    }
}
