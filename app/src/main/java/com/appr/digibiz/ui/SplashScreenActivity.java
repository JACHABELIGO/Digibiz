package com.appr.digibiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.appr.digibiz.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //we are loading the layout from the manifest file
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}