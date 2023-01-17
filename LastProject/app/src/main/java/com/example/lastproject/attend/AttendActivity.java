package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.al.AlVO;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class AttendActivity extends AppCompatActivity {
    ImageView back;
    RecyclerView   recv_attend_record;
    ArrayList<AttendVO> list;


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


        /*로그인한 사원의 전체 출퇴근 현황    */
          recv_attend_record = findViewById(R.id.recv_attend_record);
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("list_emp_since.at",(isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<AttendVO>>(){}.getType());
            recv_attend_record.setAdapter(new Attend_Main_Adapter(getLayoutInflater(),list,AttendActivity.this));
            recv_attend_record.setLayoutManager(CommonMethod.getVManager(AttendActivity.this));

        });





    }




}//class