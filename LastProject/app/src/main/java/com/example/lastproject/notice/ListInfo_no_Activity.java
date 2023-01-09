package com.example.lastproject.notice;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.login.LoginVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListInfo_no_Activity extends AppCompatActivity {
    String TAG = "로그";
    ImageView img_no_info_close;
    TextView tv_no_info_title, tv_no_info_content, tv_no_info_date, tv_no_info_name;
    Button btn_no_update, btn_no_delete;
    RecyclerView recv_no_noticeInfo;
    NoticeVO notice;
    int bo = 0;

    @Override
    public void onResume() {
        super.onResume();
        update();

    }
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
        tv_no_info_name = findViewById(R.id.tv_no_info_name);
        btn_no_update = findViewById(R.id.btn_no_update);
        btn_no_delete = findViewById(R.id.btn_no_delete);
        recv_no_noticeInfo = findViewById(R.id.recv_no_noticeInfo);



        /* 공지사항 글수정 이동 */
        btn_no_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListInfo_no_Activity.this, Update_no_Activity.class);
                intent.putExtra("notice", notice);
                startActivity(intent);
            }
        });


        /* 공지사항 글삭제 */
        btn_no_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new AlertDialog.Builder(ListInfo_no_Activity.this).setTitle("확인").setMessage("공지글을 삭제하겠습니까")
                            .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                      new CommonMethod().setParams("board_no", bo).sendPost("delete.no", (isResult, data) -> {
                        onBackPressed();
                });
                            }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                            }).show();
            }
        });





        /* 취소 / 뒤로가기 */
        img_no_info_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    // 공지사항 상세내용
    public void update() {
        new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("secinfo.no", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            notice = gson.fromJson(data, NoticeVO.class);
            notice.getWrite_date();
            notice.getBoard_no();
            bo = notice.getBoard_no();
            tv_no_info_name.setText("작성자 : " + notice.getEmp_name());
            tv_no_info_title.setText("제목 : " +notice.getBoard_title());
            tv_no_info_content.setText("내용 : " +notice.getBoard_content());
            tv_no_info_date.setText("작성일 : " +notice.getWrite_date());
        });
    }
}