package com.example.lastproject.al;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.ea.EaCodeVO;
import com.example.lastproject.ea.WriteEaFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;


public class AL_Apply_Fragment extends Fragment {
    ImageView back;
    TextView date_start,date_end, result,result_end;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Button apply_al;
    MainActivity activity;
    Spinner spinner2;
    DatePickerDialog datePickerDialog;
    RecyclerView recv_al_apply;
    ArrayList<AlVO> al_list;
    EaCodeVO evo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a_l__apply_, container, false);
        activity = (MainActivity)getActivity();
        /*뒤로가기*/
        back = v.findViewById(R.id.back);
        back.setOnClickListener(v1 -> {
            activity.onBackPressed();
        });


        AlVO vo = new AlVO();
        selectList();

        date_start = v.findViewById(R.id.date_start);
        date_end = v.findViewById(R.id.date_end);

        result = v.findViewById(R.id.result);
        result_end = v.findViewById(R.id.result_end);

        /*날짜(date) 선택하면 datepicker -> result에 값 담기게 */
        date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //오늘 날짜(년,월,일) 변수에 담기
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR); //년
                int pMonth = calendar.get(Calendar.MONTH);//월
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(getContext(),
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

                datePickerDialog = new DatePickerDialog(getContext(),
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
        apply_al = v.findViewById(R.id.apply_al);


        /*휴가 타입 선택 스피너 */
        arrayList = new ArrayList<>();
        arrayList.add("연차 사용 선택");  //0
        arrayList.add("반차"); //1
        arrayList.add("휴가"); //2
        arrayAdapter = new ArrayAdapter<>(getContext().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner2 = (Spinner)v.findViewById(R.id.spinner);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    apply_al.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*반차 V1 */
                            vo.setAl_start_date(date_start.getText().toString());
                            vo.setAl_end_date(date_end.getText().toString());
                            vo.setAl_code_value("반차");
                            vo.setAl_code("V1");
                            Bundle bundle = new Bundle();
                            Fragment f = new WriteEaFragment();
                            evo = new EaCodeVO();
                            evo.setCode_value("휴가신청서");
                            bundle.putSerializable("form", evo);
                            bundle.putSerializable("al", vo);
                            f.setArguments(bundle);
                            activity.changeFragment(f);

//                            new CommonMethod().setParams("emp_no", Common.loginInfo.getEmp_no())
//                                    .setParams("al_start_date",date_start.getText().toString())
//                                    .setParams("al_end_date",date_end.getText().toString())
//                                    .setParams("al_code","V1")
//                                    .sendPost("al_v_a.al",((isResult, data) -> {
//                                        if(isResult) {
//
//
//                                        }
//                                    }));
                            selectList();

                        }
                    });

                }else if(i ==2) {
                    apply_al.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vo.setAl_start_date(date_start.getText().toString());
                            vo.setAl_end_date(date_end.getText().toString());
                            vo.setAl_code_value("휴가");
                            vo.setAl_code("V0");
                            Bundle bundle = new Bundle();
                            Fragment f = new WriteEaFragment();
                            evo = new EaCodeVO();
                            evo.setCode_value("휴가신청서");
                            bundle.putSerializable("form", evo);
                            bundle.putSerializable("al", vo);
                            f.setArguments(bundle);
                            activity.changeFragment(f);
                            /*휴가  V0*/

//                            new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no())
//                                    .setParams("al_start_date",date_start.getText().toString())
//                                    .setParams("al_end_date",date_end.getText().toString())
//                                    .setParams("al_code","V0")
//                                    .sendPost("al_v_a.al",((isResult, data) -> {
//                                        if(isResult) {
//
//                                        }
//                                    }));
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
        recv_al_apply= v.findViewById(R.id.recv_al_apply);

        return v;
    }

    public void selectList(){
        new CommonMethod().setParams("emp_no", Common.loginInfo.getEmp_no()).sendPost("al_list.al", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            al_list = gson.fromJson(data,
                    new TypeToken<ArrayList<AlVO>>() {
                    }.getType());
            recv_al_apply.setAdapter(new Al_Apply_Adapter(getLayoutInflater(), al_list));
            recv_al_apply.setLayoutManager(CommonMethod.getVManager(getContext()));

        });

    }
}