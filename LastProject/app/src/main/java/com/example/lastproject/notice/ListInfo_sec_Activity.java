package com.example.lastproject.notice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button btn_sec_delete, btn_sec_update;
    RecyclerView recv_sec_noticeInfo;
    int bo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info_sec);
        getSupportActionBar().hide();

        img_sec_info_close = findViewById(R.id.img_sec_info_close);
        tv_sec_info_title = findViewById(R.id.tv_sec_info_title);
        tv_sec_info_content = findViewById(R.id.tv_sec_info_content);
        tv_sec_info_date = findViewById(R.id.tv_sec_info_date);
        btn_sec_update = findViewById(R.id.btn_sec_update);
        btn_sec_delete = findViewById(R.id.btn_sec_delete);
        recv_sec_noticeInfo = findViewById(R.id.recv_sec_noticeInfo);

        // 익명게시판 상세목록
        new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("secinfo.no", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            NoticeVO notice = gson.fromJson(data, NoticeVO.class);
            notice.getWrite_date();
            notice.getBoard_no();
            bo = notice.getBoard_no();
            tv_sec_info_title.setText("제목 : " +notice.getBoard_title());
            tv_sec_info_content.setText("내용 : " +notice.getBoard_content());
            tv_sec_info_date.setText("작성일 : " +notice.getWrite_date());
        });


        /* 익명게시판 글삭제 */
        btn_sec_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ListInfo_sec_Activity.this).setTitle("확인").setMessage("게시글을 삭제하겠습니까")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            new CommonMethod().setParams("board_no", bo).sendPost("delete.no", (isResult, data) -> {
                                onBackPressed();
                            });
                        }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                        }).show();
            }
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