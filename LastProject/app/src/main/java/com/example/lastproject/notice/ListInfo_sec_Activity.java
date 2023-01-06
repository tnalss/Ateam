package com.example.lastproject.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListInfo_sec_Activity extends AppCompatActivity {
    String TAG = "로그";
    ImageView img_sec_info_close;
    TextView tv_sec_info_title, tv_sec_info_content, tv_sec_info_date;
    RecyclerView recv_sec_noticeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info_sec);
        getSupportActionBar().hide();

        img_sec_info_close = findViewById(R.id.img_sec_info_close);
        tv_sec_info_title = findViewById(R.id.tv_sec_info_title);
        tv_sec_info_content = findViewById(R.id.tv_sec_info_content);
        tv_sec_info_date = findViewById(R.id.tv_sec_info_date);
        recv_sec_noticeInfo = findViewById(R.id.recv_sec_noticeInfo);

        // 익명게시판 상세목록
        new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("secinfo.no", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            NoticeVO notice = gson.fromJson(data, NoticeVO.class);
            notice.getWrite_date();
            notice.getBoard_no();
            tv_sec_info_title.setText("제목 : " +notice.getBoard_title());
            tv_sec_info_content.setText("내용 : " +notice.getBoard_content());
            tv_sec_info_date.setText("작성일 : " +notice.getWrite_date());
        });

        /* 취소 / 뒤로가기 */
        img_sec_info_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}