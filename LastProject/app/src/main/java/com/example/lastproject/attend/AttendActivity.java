package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import com.example.lastproject.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;



public class AttendActivity extends AppCompatActivity {
    ImageView back;
    RecyclerView recv_attend_apply;
    private MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);

        calendarView = findViewById(R.id.calendarview);



        /*뒤로가기*/
        back = findViewById(R.id.back);
            back.setOnClickListener(v -> {
                finish();
            });
        getSupportActionBar().hide();

        /*리사이클러뷰_근태관리*/
        recv_attend_apply =findViewById(R.id.recv_attend_apply);


    }//oncreate
}//class