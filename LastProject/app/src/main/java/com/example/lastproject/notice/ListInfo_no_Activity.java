package com.example.lastproject.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ListInfo_no_Activity extends AppCompatActivity {
    String TAG = "로그";
    ImageView img_no_info_close;
    TextView tv_no_info_title, tv_no_info_content, tv_no_info_date;
    RecyclerView recv_no_noticeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info_no);
        getSupportActionBar().hide();
        ApiClient.setBASEURL("http://192.168.0.28/middle/");

        img_no_info_close = findViewById(R.id.img_no_info_close);
        tv_no_info_title = findViewById(R.id.tv_no_info_title);
        tv_no_info_content = findViewById(R.id.tv_no_info_content);
        tv_no_info_date = findViewById(R.id.tv_no_info_date);
        recv_no_noticeInfo = findViewById(R.id.recv_no_noticeInfo);

        // 공지사항 상세목록
        new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("info.no", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            NoticeVO notice = gson.fromJson(data, NoticeVO.class);
            notice.getWrite_date();
            notice.getBoard_no();
            tv_no_info_title.setText(notice.getBoard_title());
            tv_no_info_content.setText(notice.getBoard_content());
            tv_no_info_date.setText(notice.getWrite_date());
        });








        /* 취소 / 뒤로가기 */
        img_no_info_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}