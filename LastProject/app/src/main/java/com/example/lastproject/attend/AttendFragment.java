package com.example.lastproject.attend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lastproject.R;
import com.example.lastproject.common.Common;


import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendFragment extends Fragment {

    Button on, off;
    RecyclerView recv_attend_record;
    TextView current_time,emp_name,emp_name_1,emp_dep_rank;
    Button workday;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_attend_main, container, false);
        /*현재시각 표시*/
        current_time = v.findViewById(R.id.current_time);
        current_time.setText(getCurrentTime());

        /*로그인한 사원의 이름*/
        emp_name = v.findViewById(R.id.emp_name);
        emp_name.setText(Common.loginInfo.getEmp_name());

        emp_name_1 = v.findViewById(R.id.emp_name_1);
        emp_name_1.setText(Common.loginInfo.getEmp_name()+"님 안녕하세요");

        /*로그인한 사원의 부서명, 직급*/
        emp_dep_rank = v.findViewById(R.id.emp_dep_rank);
        emp_dep_rank.setText(Common.loginInfo.getDepartment_name()+" / "+Common.loginInfo.getRank_name());
        /*출근 버튼 클릭*/

        /*퇴근 버튼 클릭*/


        /*리사이클러뷰_출퇴근기록*/
        recv_attend_record = v.findViewById(R.id.recv_attend_record);

        /*근무현황조회 클릭시 attend_activity로 화면전환*/
        workday = v.findViewById(R.id.workday);
        workday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd \n  hh:mm:ss");
        String getTime = dateFormat.format(date);
        return getTime;
    }
}