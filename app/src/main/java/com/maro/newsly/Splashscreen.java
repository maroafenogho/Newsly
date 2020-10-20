package com.maro.newsly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {

    private static final int TIMEOUT = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        ImageView logo = findViewById(R.id.logo);
        logo.startAnimation(fadeIn);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, NewsActivity.class);
                startActivity(intent);
                finish();
            }
        },TIMEOUT );
    }
}