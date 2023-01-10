package com.example.lastproject.notice;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ListInfo_no_Activity extends AppCompatActivity {
    String TAG = "로그";
    ImageView img_no_info_close;
    TextView tv_no_info_title, tv_no_info_content, tv_no_info_date, tv_no_info_name, tv_no_reply;
    Button btn_no_update, btn_no_delete, bnt_no_reply;
    RecyclerView recv_no_reply;
    EditText edt_no_reply;
    NoticeVO notice;
    ArrayList<ReplyVO> reply;


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

        img_no_info_close = findViewById(R.id.img_no_info_close);
        tv_no_info_title = findViewById(R.id.tv_no_info_title);
        tv_no_info_content = findViewById(R.id.tv_no_info_content);
        tv_no_info_date = findViewById(R.id.tv_no_info_date);
        tv_no_info_name = findViewById(R.id.tv_no_info_name);
        edt_no_reply = findViewById(R.id.edt_no_reply);
        tv_no_reply = findViewById(R.id.tv_no_reply);
        btn_no_update = findViewById(R.id.btn_no_update);
        btn_no_delete = findViewById(R.id.btn_no_delete);
        bnt_no_reply = findViewById(R.id.bnt_no_reply);
        recv_no_reply = findViewById(R.id.recv_no_reply);

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

        /* 댓글작성 클릭 */
        tv_no_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bnt_no_reply.setVisibility(View.VISIBLE);
                edt_no_reply.setVisibility(View.VISIBLE);
            }
        });
        /* 댓글 작성 */
        bnt_no_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplyVO re = new ReplyVO();
                re.setBoard_no(bo);
                re.setReply_content(edt_no_reply.getText().toString());
                re.setEmp_no(Integer.parseInt(Common.loginInfo.getEmp_no()));
        new CommonMethod().setParams("re", new Gson().toJson( re )).sendPost("re_insert.no", (isResult, data) -> {
            replylist();
        });
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
        new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("info.no", (isResult, data) -> {

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            notice = gson.fromJson(data, NoticeVO.class);
            bo = notice.getBoard_no();
            tv_no_info_name.setText("작성자 : " + notice.getEmp_name());
            tv_no_info_title.setText("제목 : " + notice.getBoard_title());
            tv_no_info_content.setText("내용 : " + notice.getBoard_content());
            tv_no_info_date.setText("작성일 : " + notice.getWrite_date());

        replylist();

        });

    }
    public void replylist() {
        // 댓글 목록
        new CommonMethod().setParams("board_no", bo).sendPost("reply.no", (isResult1, data1) -> {
            Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            reply = gson1.fromJson(data1,
                    new TypeToken<ArrayList<ReplyVO>>() {
                    }.getType());
            recv_no_reply.setAdapter(new ReplyAdapter(getLayoutInflater(), reply, ListInfo_no_Activity.this));
            recv_no_reply.setLayoutManager(CommonMethod.getVManager(ListInfo_no_Activity.this));

        });
    }

}