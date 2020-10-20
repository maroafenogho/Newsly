package com.maro.newsly.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.maro.newsly.api.ApiClient;
import com.maro.newsly.api.ApiInterface;
import com.maro.newsly.data.News;
import com.maro.newsly.data.Results;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repo {
    private static Repo repo;
    public static Repo getInstance(){
        if (repo == null){
            repo = new Repo();
        }
        return  repo;
    }
    private ApiInterface apiInterface;
    public  Repo(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<News>  getNews(String key) {
        MutableLiveData<News> newsData = new MutableLiveData<>();
        apiInterface.getNews("ff84138e-d857-4b98-a219-65a89a47f6f2").enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.toString());
            }
        });
        return newsData;
    }
}
