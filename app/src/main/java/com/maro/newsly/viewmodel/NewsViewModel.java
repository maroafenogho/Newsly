package com.maro.newsly.viewmodel;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.maro.newsly.RecyclerAdapter;
import com.maro.newsly.api.ApiClient;
import com.maro.newsly.api.ApiInterface;
import com.maro.newsly.data.News;
import com.maro.newsly.data.Results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<Results>> result;
    String newsUrl;

    public LiveData<List<Results>> getResults(){
        if (result == null){
            result = new MutableLiveData<List<Results>>();
            loadResults();
        }
        return result;
    }

    public LiveData<List<Results>> getNigeria(){
        if (result == null){
            result = new MutableLiveData<List<Results>>();
            loadNigeria();
        }
        return result;
    }

    private void loadNigeria(){
        ApiInterface apiInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> filters = new HashMap<>();
        filters.put("tag", "world");
        filters.put("api-key","ff84138e-d857-4b98-a219-65a89a47f6f2");

        Call<News> call = apiInterface.getNigerianNews(filters);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                assert response.body() != null;
                result.setValue(response.body().getResponse().getResults());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.toString());
            }
        });
    }

    private void loadResults() {
        ApiInterface apiInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<News> call = apiInterface.getNews("ff84138e-d857-4b98-a219-65a89a47f6f2");
        call.enqueue(new Callback<News>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                assert response.body() != null;
                result.setValue(response.body().getResponse().getResults());
                Log.d("TAG", "onResponse: response" + response.body());
                Log.i("TAG", "onResponse: "  + response.body().toString());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.toString());
            }
        });
    }
}
