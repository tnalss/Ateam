package com.example.lastproject.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Update_sec_Activity extends AppCompatActivity {
    ImageView img_sec_up_close, img_sec_up_file;
    TextView tv_sec_up_ok, edt_sec_up_title, edt_sec_up_content;
    NoticeVO notice;
    int bo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sec);
        getSupportActionBar().hide();
        img_sec_up_close = findViewById(R.id.img_sec_up_close);
        img_sec_up_file = findViewById(R.id.img_sec_up_file);
        tv_sec_up_ok = findViewById(R.id.tv_sec_up_ok);
        edt_sec_up_title = findViewById(R.id.edt_sec_up_title);
        edt_sec_up_content = findViewById(R.id.edt_sec_up_content);

        // 익명게시판 수정
        Intent intent = getIntent();
        notice = (NoticeVO) intent.getSerializableExtra("notice");
        edt_sec_up_title.setText(notice.getBoard_title());
        edt_sec_up_content.setText(notice.getBoard_content());
        notice.getBoard_no();
        bo = notice.getBoard_no();
        tv_sec_up_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setBoard_title(edt_sec_up_title.getText().toString());
                notice.setBoard_content(edt_sec_up_content.getText().toString());
                new CommonMethod().setParams("no", new Gson().toJson(notice)).sendPost("secupdate.no", (isResult, data) -> {
                    finish();
                });
            }
        });



        /* 취소 / 뒤로가기 */
        img_sec_up_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }




}