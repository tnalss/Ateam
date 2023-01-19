package com.example.lastproject.attend;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.Org_Chart.OrgVO;
import com.example.lastproject.Org_Chart.Org_all_adapter;
import com.example.lastproject.R;

import com.example.lastproject.common.Common;
import com.example.lastproject.common.SimpleCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Attend_adminFragment extends Fragment {
    RecyclerView recv_attend_admin;
    MainActivity activity;
    ArrayList<AttendAdminVO> list;
    DatePickerDialog datePickerDialog;
    LinearLayout datepicker;
    NiceSpinner spinner_branch, spinner_dept, spinner_rank;
    TextView d;
    ArrayList<SimpleCode> branch_list,dep_list,rank_list;
    AttendAdminVO vo = new AttendAdminVO();
    ImageView back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attend_admin, container, false);

        /*리사이클러뷰 - 사원 전체의 출퇴근 기록 */
        recv_attend_admin = v.findViewById(R.id.recv_attend_admin);
        new CommonMethod().sendPost("worktime_day.at",(isResult, data) -> {
            list = new Gson().fromJson(data, new TypeToken<ArrayList<AttendAdminVO>>(){}.getType());
            recv_attend_admin.setAdapter(new Attend_Admin_Adapter(getLayoutInflater(),list,getContext(),activity));
            recv_attend_admin.setLayoutManager(CommonMethod.getVManager(getContext()));

        });


        datepicker = v.findViewById(R.id.datepicker);
        d = v.findViewById(R.id.d);
        datepicker.setOnClickListener(new View.OnClickListener() {
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
                                String date = year + "년" + month + "월" + day;
                                d.setText(date);
                                vo.setAttend_date(date);
                                selectlist();
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();

            } //onClick

        });

        vo.setAttend_date( new SimpleDateFormat("yyyy/MM/dd").format(new Date()) );


        /*지점별 스피너*/
        spinner_branch = v.findViewById(R.id.s1);
        new CommonMethod().setParams("top_code","B").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                branch_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> b = new ArrayList<>();
                for (int i = 0 ; i < branch_list.size() ; i++){
                    b.add(branch_list.get(i).getCode_value());
                }
                spinner_branch.attachDataSource(b);

            }
        });
        spinner_branch.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int i, long id) {
                vo.setBranch_name(branch_list.get(i).getCode_value());
                   selectlist();
            }
        });



        /*부서별 스피너*/
        spinner_dept = v.findViewById(R.id.s2);
        new CommonMethod().setParams("top_code","D").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> d = new ArrayList<>();
                for (int i = 0 ; i < dep_list.size() ; i++){
                    d.add(dep_list.get(i).getCode_value());
                }
                spinner_dept.attachDataSource(d);
            }
        });
        spinner_dept.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int i, long id) {
                vo.setDepartment_name(dep_list.get(i).getCode_value());
                selectlist();
            }
        });



        /*직급별 스피너*/
        spinner_rank = v.findViewById(R.id.s3);
        new CommonMethod().setParams("top_code","R").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                rank_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> r = new ArrayList<>();
                for (int i = 0 ; i < rank_list.size() ; i++){
                    r.add(rank_list.get(i).getCode_value());
                }
                spinner_rank.attachDataSource(r);
            }
        });

        spinner_rank.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int i, long id) {
               vo.setRank_name(rank_list.get(i).getCode_value());
               selectlist();
            }
        });





        return v;
    }

    public  void selectlist(){
        new CommonMethod().setParams("param",new Gson().toJson(vo)).sendPost("worktime_all.at",(isResult, data) -> {
            if(isResult) {
            list = new Gson().fromJson(data, new TypeToken<ArrayList<AttendAdminVO>>(){}.getType());
                recv_attend_admin.setAdapter(new Attend_Admin_Adapter(getLayoutInflater(), list, getContext(), activity));
                recv_attend_admin.setLayoutManager(CommonMethod.getVManager(getContext()));
            }
        });
    }

}