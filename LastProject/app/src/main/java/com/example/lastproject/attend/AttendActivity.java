package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lastproject.R;
import com.example.lastproject.al.AL_Apply_Activity;


public class AttendActivity extends AppCompatActivity {
    ImageView back;
    RecyclerView recv_attend_apply;
    Button apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);

        /*뒤로가기*/
        back = findViewById(R.id.back);
            back.setOnClickListener(v -> {
                finish();
            });
        getSupportActionBar().hide();

        /*휴가및 연차 신청*/
         apply = findViewById(R.id.apply);
         apply.setOnClickListener(v -> {
             Intent intent = new Intent(this, AL_Apply_Activity.class);
             startActivity(intent);
         });


        /*리사이클러뷰_로그인한 사원의 연차.휴가 신청 현황 */
        recv_attend_apply =findViewById(R.id.recv_attend_apply);



    }//oncreate



}//class