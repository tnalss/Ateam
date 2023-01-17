package com.example.lastproject.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;

public class Insert_secActivity extends AppCompatActivity {
    ImageView img_no_close, img_no_file;
    EditText edt_no_title, edt_no_content;
    TextView tv_no_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sec);
        getSupportActionBar().hide();
        img_no_close = findViewById(R.id.img_no_close);
        img_no_file = findViewById(R.id.img_no_file);
        edt_no_title = findViewById(R.id.edt_no_title);
        edt_no_content = findViewById(R.id.edt_no_content);
        tv_no_ok = findViewById(R.id.tv_no_ok);

        tv_no_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeVO vo = new NoticeVO();
                vo.setBoard_title(edt_no_title.getText().toString());
                vo.setBoard_content(edt_no_content.getText().toString());
                vo.setBoard_cate("O0");
                vo.setEmp_no(Integer.parseInt(Common.loginInfo.getEmp_no()) );

                new CommonMethod().setParams("vo", new Gson().toJson( vo )).sendPost("insert.no", (isResult, data) -> {
                    finish();
                });

            }
        });


        // 취소
        img_no_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}