package com.example.bookrecommendationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(()->{
            FirebaseAuth auth = FirebaseAuth.getInstance();
            if(auth.getCurrentUser()!=null)
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            else
                startActivity(new Intent(SplashScreen.this,LoginActivity.class));
            finish();
        },2000);
    }
}