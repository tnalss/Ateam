package com.example.lastproject.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.ea.FormListFragment;
import com.google.gson.Gson;

public class Insert_noActivity extends AppCompatActivity {
    ImageView img_sec_close, img_sec_file;
    EditText  edt_sec_title, edt_sec_content;
    TextView tv_sec_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_no);
        getSupportActionBar().hide();
        ApiClient.setBASEURL("http://192.168.0.28/middle/");
        img_sec_close = findViewById(R.id.img_sec_close);
        img_sec_file = findViewById(R.id.img_sec_file);
        tv_sec_ok = findViewById(R.id.tv_sec_ok);
        edt_sec_title = findViewById(R.id.edt_sec_title);
        edt_sec_content = findViewById(R.id.edt_sec_content);

        tv_sec_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeVO vo = new NoticeVO();
                vo.setBoard_title(edt_sec_title.getText().toString());
                vo.setBoard_content(edt_sec_content.getText().toString());
                vo.setBoard_cate("O1");
                vo.setEmp_no(Integer.parseInt(Common.loginInfo.getEmp_no()) );

                new CommonMethod().setParams("vo", new Gson().toJson( vo )).sendPost("insert.no", (isResult, data) -> {
                    Log.d("로그", "onClick: " + data);
                    finish();
                });

            }
        });















        // 취소
        img_sec_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}