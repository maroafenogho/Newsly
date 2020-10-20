package com.maro.newsly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.maro.newsly.data.News;
import com.maro.newsly.data.Results;

import java.util.ArrayList;
import java.util.List;

import com.maro.newsly.api.ApiClient;
import com.maro.newsly.api.ApiInterface;
import com.maro.newsly.viewmodel.NewsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity{

    private WebView myWebView;
    private TextView text;
    RecyclerView recyclerView;
    List<Results> results;
    RecyclerAdapter recyclerAdapter;
    NewsViewModel newsViewModel;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        recyclerView = findViewById(R.id.recycler);
        myWebView= findViewById(R.id.webView);
        myWebView.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(results, getApplicationContext());
        results = new ArrayList<>();

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getResults().observe(this, results1 ->{
            recyclerAdapter.setResultsList(results1);
                recyclerView.setAdapter(recyclerAdapter);
                });

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    goBack();
                }
                super.handleMessage(msg);
            }
        };

//        ApiInterface apiInterface;
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<News> call = apiInterface.getNews("ff84138e-d857-4b98-a219-65a89a47f6f2");
//        call.enqueue(new Callback<News>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                assert response.body() != null;
//                results =  response.body().getResponse().getResults();
//                recyclerAdapter.setResultsList(results);
//                recyclerView.setAdapter(recyclerAdapter);
//                Log.d("TAG", "onResponse: response" + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//            text.setText(""+ t);
//            }
//        });

        recyclerAdapter.setmOnNewsListener(new RecyclerAdapter.OnNewsListener() {
            @Override
            public void onNewsClick(Results results) {
                String news = results.getWebUrl();
                Uri newsUrl = Uri.parse(news);
                myWebView.loadUrl(String.valueOf(newsUrl));
                myWebView.setVisibility(View.VISIBLE);
            }
        });

        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(1);
                    return  true;
                }
                return false;
            }
        });

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

    }
    private void goBack(){
        myWebView.goBack();
    }

}