package com.example.lastproject.notice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.ea.FormListFragment;
import com.google.gson.Gson;

import java.io.File;

public class Insert_noActivity extends AppCompatActivity {
    ImageView img_sec_close, img_sec_file;
    EditText  edt_sec_title, edt_sec_content;
    TextView tv_sec_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_no);
        getSupportActionBar().hide();

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