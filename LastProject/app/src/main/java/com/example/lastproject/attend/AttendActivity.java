package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageView;
import com.example.lastproject.R;


public class AttendActivity extends AppCompatActivity {
    ImageView back;
    RecyclerView recv_attend_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);
        /*뒤로가기*/
        back = findViewById(R.id.back);
            back.setOnClickListener(v -> {
                finish();
            });

        /*리사이클러뷰_근태관리*/
        recv_attend_apply =findViewById(R.id.recv_attend_apply);


    }//oncreate
}//class