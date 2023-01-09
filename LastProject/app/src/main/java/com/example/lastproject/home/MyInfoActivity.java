package com.example.lastproject.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lastproject.R;

public class MyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        getSupportActionBar().hide();
    }
}