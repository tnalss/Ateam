package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lastproject.R;

public class LocationNowActivity extends AppCompatActivity {
    LocationFragment LocationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_now);
      LocationFragment = new LocationFragment();


      /*LocationNow activity 내부의 프레임레이아웃에 locationfragment를 붙이는 처리*/
      getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,LocationFragment).commit();


    }
}