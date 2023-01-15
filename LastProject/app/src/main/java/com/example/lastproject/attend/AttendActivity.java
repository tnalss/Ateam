package com.example.lastproject.attend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.al.AL_Apply_Activity;
import com.example.lastproject.al.AlVO;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class AttendActivity extends AppCompatActivity {
    ImageView back;
    RecyclerView recv_attend_apply;
    Button apply;
    ArrayList<AlVO> al_list;


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


        /*로그인한 사원의 연차.휴가 신청 현황 */
        recv_attend_apply = findViewById(R.id.recv_attend_apply);
        new CommonMethod().setParams("emp_no", Common.loginInfo.getEmp_no()).sendPost("al_list.al", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            al_list = gson.fromJson(data,
                    new TypeToken<ArrayList<AlVO>>() {
                    }.getType());
            recv_attend_apply.setAdapter(new Attend_Activity_Adapter(getLayoutInflater(), al_list, AttendActivity.this));
            recv_attend_apply.setLayoutManager(CommonMethod.getVManager(AttendActivity.this));

        });
    }




}//class