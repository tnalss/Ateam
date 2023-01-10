package com.example.lastproject.login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    Button btn_login_login;
    EditText edt_login_id, edt_login_pw;
    TextView tv_login_pwfind;
    CheckBox chec_id, chec_gogo;
    SharedPreferences preferences ;

    String TAG = "로그";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        edt_login_pw = findViewById(R.id.edt_login_pw);
        edt_login_id = findViewById(R.id.edt_login_id);
        btn_login_login = findViewById(R.id.btn_login_login);
        tv_login_pwfind = findViewById(R.id.tv_login_pwfind);
        chec_id = findViewById(R.id.chec_id);
        chec_gogo = findViewById(R.id.chec_gogo);
        ApiClient.setBASEURL("http://192.168.0.2/middle/");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        /* 저장소에서 값을 가져와서 체크박스 여부 확인 후 아이디값을 넣어주는곳 */
        if (preferences.getBoolean("save", false)) {
            String id = preferences.getString("email", "");
            chec_id.setChecked(true);
            edt_login_id.setText(id);
        }



        /* 체크박스 핸들러 */
        chec_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chec_id.setChecked(isChecked);
            }
        });

        /* 자동로그인 체크박스 핸들러 */
        chec_gogo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chec_gogo.setChecked(isChecked);
            }
        });


        // 로그인 버튼
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             login();

            }
        });
        if(preferences.getBoolean("auto_login", false)) {
            String id = preferences.getString("auto_login_id", "");
            String pw = preferences.getString("auto_login_pw", "");
            chec_gogo.setChecked(true);
            edt_login_id.setText(id);
            edt_login_pw.setText(pw);
            login();

        }
        /* id 버튼 비활성화 */
        btn_login_login.setEnabled(false);
        edt_login_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_login_id.length() > 0 && edt_login_pw.length() > 0) {
                    btn_login_login.setEnabled(true);
                    btn_login_login.setBackgroundColor(Color.parseColor("#71F443"));
                } else {
                    btn_login_login.setEnabled(false);
                    btn_login_login.setBackgroundColor(Color.parseColor("#D2D2D1"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /* pw 버튼 비활성화 */
        edt_login_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_login_pw.length() > 0 && edt_login_pw.length() > 0) {
                    btn_login_login.setEnabled(true);
                    btn_login_login.setBackgroundColor(Color.parseColor("#71F443"));
                } else {
                    btn_login_login.setEnabled(false);
                    btn_login_login.setBackgroundColor(Color.parseColor("#D2D2D1"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*  패스워드 찾기 */
        tv_login_pwfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PwfindingActivity.class);
                startActivity(intent);
            }
        });
    }





    // 로그인 메소드
    public void login () {
        /* 현재 체그박스 여부 확인후 저장소에 값을 넣어줌 */
        SharedPreferences.Editor editor = preferences.edit();
        if (chec_id.isChecked()) {
            editor.putBoolean("save", chec_id.isChecked());
            editor.putString("email", edt_login_id.getText().toString());
        } else {
            editor.putBoolean("save", chec_id.isChecked());
        }
        // 자동로그인
        if (chec_gogo.isChecked()) {
            editor.putBoolean("auto_login", chec_gogo.isChecked());
            editor.putString("auto_login_id", edt_login_id.getText().toString());
            editor.putString("auto_login_pw", edt_login_pw.getText().toString());
        }else {
            editor.putBoolean("auto_login", chec_gogo.isChecked());
            editor.putString("auto_login_id", "");
            editor.putString("auto_login_pw", "");
        }
        editor.commit();
        new CommonMethod().setParams("emp_no", edt_login_id.getText()).setParams
                ("emp_pw", edt_login_pw.getText()).sendPost("login", (isResult, data) -> {
            LoginVO vo = new Gson().fromJson(data, LoginVO.class);
            if (vo != null) {

                Toast.makeText(LoginActivity.this, vo.getEmp_name() + " 님 어서오세요", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("loginInfo", vo);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }

        });
    }

}