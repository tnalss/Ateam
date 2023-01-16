package com.example.lastproject.al;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;

public class AL_Apply_Activity extends AppCompatActivity {
        ImageView back;
        TextView date_start,date_end, result,result_end;
        ArrayList<String> arrayList;
        ArrayAdapter<String> arrayAdapter;
        Button apply_al;
        Spinner spinner2;
        DatePickerDialog datePickerDialog;
        RecyclerView recv_al_apply;
        ArrayList<AlVO> al_list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_apply);
        getSupportActionBar().hide();

        /*뒤로가기*/
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });


        AlVO vo = new AlVO();
        selectList();

        date_start = findViewById(R.id.date_start);
        date_end = findViewById(R.id.date_end);

        result = findViewById(R.id.result);
        result_end = findViewById(R.id.result_end);

        /*날짜(date) 선택하면 datepicker -> result에 값 담기게 */
        date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //오늘 날짜(년,월,일) 변수에 담기
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR); //년
                int pMonth = calendar.get(Calendar.MONTH);//월
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(AL_Apply_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                //1월은 0부터 시작하기 때문에 +1을 해준다.
                                month = month + 1;
                                String date = year + "/" + month + "/" + day;
                                result.setText(date);
                                date_start.setText(date);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();

            } //onClick
        });


        /*날짜2(date_end) 선택하면 datepicker -> result에 값 담기게 */
        date_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //오늘 날짜(년,월,일) 변수에 담기
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR); //년
                int pMonth = calendar.get(Calendar.MONTH);//월
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(AL_Apply_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                //1월은 0부터 시작하기 때문에 +1을 해준다.
                                month = month + 1;
                                String date = year + "/" + month + "/" + day;
                                result_end.setText(" ~ "+date);
                                date_end.setText(date);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
            } //onClick
        });


        /*신청 버튼*/
        apply_al = findViewById(R.id.apply_al);


        /*휴가 타입 선택 스피너 */
        arrayList = new ArrayList<>();
        arrayList.add("연차 사용 선택");  //0
        arrayList.add("반차"); //1
        arrayList.add("휴가"); //2
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner2 = (Spinner)findViewById(R.id.spinner);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    apply_al.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*반차 V1 */

                            Toast.makeText(AL_Apply_Activity.this, "반차 신청 완료", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else if(i ==2) {
                    apply_al.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*휴가  V0*/
                            new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no())
                                    .setParams("al_start_date",date_start.getText())
                                    .setParams("al_end_date",date_end.getText())
                                    .sendPost("al_v_a.al",((isResult, data) -> {

                            }));
                            Toast.makeText(AL_Apply_Activity.this, "휴가 신청 완료", Toast.LENGTH_SHORT).show();
                            selectList();
                        }
                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    /*로그인한 사원의 연차.휴가 신청 현황 */
     recv_al_apply= findViewById(R.id.recv_al_apply);


    }

    public void selectList(){
     new CommonMethod().setParams("emp_no", Common.loginInfo.getEmp_no()).sendPost("al_list.al", (isResult, data) -> {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        al_list = gson.fromJson(data,
                new TypeToken<ArrayList<AlVO>>() {
                }.getType());
        recv_al_apply.setAdapter(new Al_Apply_Adapter(getLayoutInflater(), al_list,AL_Apply_Activity.this));
        recv_al_apply.setLayoutManager(CommonMethod.getVManager(AL_Apply_Activity.this));

    });
    }
}
