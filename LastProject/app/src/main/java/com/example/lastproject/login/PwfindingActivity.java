package com.example.lastproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.employee.EmployeeVO;
import com.google.gson.Gson;

public class PwfindingActivity extends AppCompatActivity {
    Button btn_cancel, btn_pw_ok,btn_id_ok ,btn_id_cancel;
    EditText edt_pwf_name, edt_pwf_no, edt_pwf_email,edt_id_name,edt_id_email;
    TextView tv_found_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwfinding);
        getSupportActionBar().hide();

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_pw_ok = findViewById(R.id.btn_pw_ok);
        btn_id_ok = findViewById(R.id.btn_id_ok);
        edt_pwf_name = findViewById(R.id.edt_pwf_name);
        edt_pwf_no = findViewById(R.id.edt_pwf_no);
        edt_pwf_email = findViewById(R.id.edt_pwf_email);
        btn_id_cancel = findViewById(R.id.btn_id_cancel);
        edt_pwf_email = findViewById(R.id.edt_pwf_email);
        edt_id_name = findViewById(R.id.edt_id_name);
        edt_id_email = findViewById(R.id.edt_id_email);
        tv_found_id = findViewById(R.id.tv_found_id);

        /* 아이디 찾기 */
        btn_id_ok.setOnClickListener(v -> {
            if(edt_id_name.getText().toString().trim().length()==0 || edt_id_email.getText().toString().trim().length()==0){
                tv_found_id.setText("이름과 이메일을 입력해주세요.");
                return;
            }
            EmployeeVO vo = new EmployeeVO();
            vo.setEmp_name(edt_id_name.getText().toString().trim());
            vo.setEmail(edt_id_email.getText().toString().trim());

            new CommonMethod().setParams("vo",new Gson().toJson(vo)).sendPost("findId.emp",(isResult, data) -> {
                if(isResult){
                    if(data.length()>0){
                        tv_found_id.setText(vo.getEmp_name()+"님의 ID는 "+ data + " 입니다.");
                        edt_pwf_no.setText(data);
                        edt_pwf_name.setText(edt_id_name.getText().toString().trim());
                        edt_pwf_email.setText(edt_id_email.getText().toString().trim());
                    }else{
                        tv_found_id.setText("실패) 이름과 이메일을 다시 확인해주세요.");
                    }
                } else {
                    tv_found_id.setText("실패) 인터넷 연결을 확인해주세요.");
                }

            });
        });

        /* 패스워드 찾기 */
        btn_pw_ok.setOnClickListener(v -> {
            EmployeeVO vo = new EmployeeVO();
            if(edt_pwf_name.getText().toString().trim().length()==0 ||edt_pwf_no.getText().toString().trim().length()==0|| edt_pwf_email.getText().toString().trim().length()==0){
                tv_found_id.setText("이름, 사번, 이메일을 입력해주세요.");
                return;
            }
            EmployeeVO vo2 = new EmployeeVO();
            vo2.setEmp_name(edt_pwf_name.getText().toString().trim());
            vo2.setEmail(edt_pwf_email.getText().toString().trim());
            vo2.setEmp_no(edt_pwf_no.getText().toString().trim());

            new CommonMethod().setParams("vo",new Gson().toJson(vo2)).sendPost("findPw.emp", (isResult, data) -> {
                if(isResult){
                    tv_found_id.setText("메일로 임시 비밀번호가 발급되었습니다.");
                }

            });
        });

        /* 아이디 찾기 취소    */
        btn_id_cancel.setOnClickListener(v -> { finish(); });

        /* 패스워드 찾기 취소  */
        btn_cancel.setOnClickListener(v -> { finish(); });

    }

}