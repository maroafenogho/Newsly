package com.maro.newsly.api;

import com.maro.newsly.data.News;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
//    https://content.guardianapis.com/search?api-key=ff84138e-d857-4b98-a219-65a89a47f6f2
//
    @GET("world")
    Call<News> getNews(
            @Query("api-key") String apiKey
    );
    @GET("world/nigeria")
    Call<News> getNigerianNews(
//            @Query("tag")String tag,
            @QueryMap HashMap<String, String> filters
            );
}
