package com.example.lastproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.R;

public class PwfindingActivity extends AppCompatActivity {
    String TAG = "로그";
    Button btn_cancel, btn_pw_ok;
    EditText edt_pwf_name, edt_pwf_no, edt_pwf_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwfinding);
        getSupportActionBar().hide();
        ApiClient.setBASEURL("192.168.0.23:3303/middle");

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_pw_ok = findViewById(R.id.btn_pw_ok);
        edt_pwf_name = findViewById(R.id.edt_pwf_name);
        edt_pwf_no = findViewById(R.id.edt_pwf_no);
        edt_pwf_email = findViewById(R.id.edt_pwf_email);

        new CommonMethod().sendPost("pwfind", (isResult, data) -> {
            btn_pw_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        });


        /* 패스워드 찾기 취소  */
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}