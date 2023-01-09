package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.io.IOException;
import java.util.List;


public class AttendActivity extends AppCompatActivity {
    ImageView back;
    RecyclerView recv_attend_apply;
    private MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);


        calendarView = findViewById(R.id.calendarview_monthly);



        /*뒤로가기*/
        back = findViewById(R.id.back);
            back.setOnClickListener(v -> {
                finish();
            });
        getSupportActionBar().hide();



        /*리사이클러뷰_로그인한 사원의 연차.휴가 신청 현황 */
        recv_attend_apply =findViewById(R.id.recv_attend_apply);



    }//oncreate



}//class