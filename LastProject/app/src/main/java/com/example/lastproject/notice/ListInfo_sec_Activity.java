package com.example.lastproject.notice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ListInfo_sec_Activity extends AppCompatActivity {
    String TAG = "로그";
    ImageView img_sec_info_close;
    TextView tv_sec_info_title, tv_sec_info_content, tv_sec_info_date, tv_sec_reply;
    Button btn_sec_delete, btn_sec_update, btn_sec_reply;
    EditText edt_sec_reply;
    RecyclerView recv_sec_noticeInfo, recv_no_info_file;
    NoticeVO notice;
    ArrayList<ReplyVO> reply;
    List<NoticeFileVO> filevo;
    noInfoAdapter adapter;
    ArrayList<String> path_list;
    int bo = 0;

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info_sec);
        getSupportActionBar().hide();

        img_sec_info_close = findViewById(R.id.img_sec_info_close);
        tv_sec_info_title = findViewById(R.id.tv_sec_info_title);
        tv_sec_info_content = findViewById(R.id.tv_sec_info_content);
        tv_sec_info_date = findViewById(R.id.tv_sec_info_date);
        tv_sec_info_date = findViewById(R.id.tv_sec_info_date);
        tv_sec_reply = findViewById(R.id.tv_sec_reply);
        edt_sec_reply = findViewById(R.id.edt_sec_reply);
        btn_sec_reply = findViewById(R.id.btn_sec_reply);
        btn_sec_update = findViewById(R.id.btn_sec_update);
        btn_sec_delete = findViewById(R.id.btn_sec_delete);
        recv_sec_noticeInfo = findViewById(R.id.recv_sec_noticeInfo);
        recv_no_info_file = findViewById(R.id.recv_no_info_file);
        new Common().checkDangerousPermissions(this);

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

        /* 익명게시판 글수정 이동 */
        btn_sec_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListInfo_sec_Activity.this, Update_sec_Activity.class);
                intent.putExtra("notice", notice);
                startActivity(intent);
            }
        });

        /* 댓글작성 클릭 */
        tv_sec_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_sec_reply.setVisibility(View.VISIBLE);
                edt_sec_reply.setVisibility(View.VISIBLE);
            }
        });
        /* 댓글 작성 */
        btn_sec_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplyVO re = new ReplyVO();
                re.setBoard_no(bo);
                re.setReply_content(edt_sec_reply.getText().toString());
                re.setEmp_no(Integer.parseInt(Common.loginInfo.getEmp_no()));
                new CommonMethod().setParams("re", new Gson().toJson(re)).sendPost("re_insert.no", (isResult, data) -> {
                    btn_sec_reply.setVisibility(View.GONE);
                    edt_sec_reply.setVisibility(View.GONE);
                    edt_sec_reply.setText("");
                    re_sec_plylist();
                });
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

    // 익명게시판 상세내용
    public void update() {

                new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("secinfo.no", (isResult, data) -> {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                notice = gson.fromJson(data, NoticeVO.class);
                notice.getWrite_date();
                notice.getBoard_no();
                bo = notice.getBoard_no();
                tv_sec_info_title.setText("제목 : " + notice.getBoard_title());
                tv_sec_info_content.setText(notice.getBoard_content());
                tv_sec_info_date.setText("작성일 : " + notice.getWrite_date());
                re_sec_plylist();
            });

            new CommonMethod().setParams("no", getIntent().getIntExtra("board_no", 0)).sendPost("imageFile.no", (isResult, data) -> {
                if (isResult){
                   if(data!=null) {

                       ArrayList<NoticeFileVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<NoticeFileVO>>() {}.getType());
                       if(list ==null)
                           return;
                       adapter = new noInfoAdapter(getLayoutInflater(), list, this);
                       recv_no_info_file.setAdapter(adapter);
                       recv_no_info_file.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


                   }
                }

            });

    }

    public void re_sec_plylist() {
        // 댓글 목록
        new CommonMethod().setParams("board_no", bo).sendPost("sec_reply.no", (isResult1, data1) -> {
            Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            reply = gson1.fromJson(data1,
                    new TypeToken<ArrayList<ReplyVO>>() {
                    }.getType());
            recv_sec_noticeInfo.setAdapter(new Reply_sec_Adapter(getLayoutInflater(), reply, ListInfo_sec_Activity.this));
            recv_sec_noticeInfo.setLayoutManager(CommonMethod.getVManager(ListInfo_sec_Activity.this));

        });
    }
}