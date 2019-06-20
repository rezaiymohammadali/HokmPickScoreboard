package com.example.mar.hokmpick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mar.hokmpick.R;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashActivity extends AppCompatActivity {

    private pl.droidsonroids.gif.GifImageView gifImageView;
    private AVLoadingIndicatorView aviProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        aviProgressBar = findViewById(R.id.aviProgressBar);
        aviProgressBar.show();

//        gifImageView = findViewById(R.id.gifImageView);
//
//        //set GIFImageView Resource
//        try {
//            InputStream inputStream = getAssets().open("splash_screen.gif");
//            byte[] bytes = IOUtils.toByteArray(inputStream);
//            gifImageView.setBytes(bytes);
//            gifImageView.startAnimation();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //Wait for 3 seconds and start Activity Main
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                SplashActivity.this.finish();
//            }
//        }, 15000); // 3000= 3 seconds

        gifImageView = findViewById(R.id.gifImageView);

//        try {
//            GifDrawable gifFromAssets = new GifDrawable( getAssets(), "splash_screen.gif" );
//            gifFromAssets.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        });

    }
}
