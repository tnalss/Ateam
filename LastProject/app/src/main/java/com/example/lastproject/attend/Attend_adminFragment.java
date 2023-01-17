package com.example.lastproject.attend;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.lastproject.R;
import com.example.lastproject.al.AL_Apply_Activity;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;

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
    NiceSpinner spinner;
    TextView d;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attend_admin, container, false);


        /*리사이클러뷰 - 사원 전체의 출퇴근 기록 */
        recv_attend_admin = v.findViewById(R.id.recv_attend_admin);
        new CommonMethod().sendPost("all.at",(isResult, data) -> {
            list = new Gson().fromJson(data, new TypeToken<ArrayList<AttendAdminVO>>(){}.getType());
            recv_attend_admin.setAdapter(new Attend_Admin_Adapter(getLayoutInflater(),list,getContext(),activity));
            recv_attend_admin.setLayoutManager(CommonMethod.getVManager(getContext()));
        });

        datepicker = v.findViewById(R.id.datepicker);
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
                                String date = year + "/" + month + "/" + day;
                                d.setText(date);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();


            } //onClick

        });


        return v;
    }


}