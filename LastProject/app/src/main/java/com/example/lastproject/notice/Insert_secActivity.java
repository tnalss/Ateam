package com.example.lastproject.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Insert_secActivity extends AppCompatActivity {
    ImageView img_no_close, img_no_file;
    EditText edt_no_title, edt_no_content;
    TextView tv_no_ok;
    String path_list;
    ArrayList<String> name_list;
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
                if (path_list == null) {
                    new CommonMethod().setParams("vo", new Gson().toJson(vo)).sendPost("insert.no", (isResult, data) -> {
                        if (isResult) {
                            Toast.makeText(Insert_secActivity.this, "게시판 등록 완료", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.d("로그", "실패 ");

                        }
                    });

                } else {
                    new CommonMethod().setParams("vo", vo).sendPostFile("insert.fi", path_list, (isResult, data) -> {
                        if (isResult) {
                            Toast.makeText(Insert_secActivity.this, "게시글 등록완료", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.d("로그", "실패");
                        }

                        img_no_file.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                galleryMethod();
                            }
                        });
                    });

                }
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
    //파일 선택시 실행 메소드
    public void fileMethod(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
//        intent.putExtra(Intent.)

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "파일 선택"), Common.FILE_CODE);
    }

    //갤러리 선택시 실행 메소드
    public void galleryMethod(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //2023-01-11 사진 선택을 여러개 할수있게
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "사진 선택"), Common.GALLERY_CODE);
    }
}