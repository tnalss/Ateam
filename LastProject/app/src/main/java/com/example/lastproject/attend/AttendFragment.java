package com.example.lastproject.attend;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.home.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AttendFragment extends Fragment {

    RelativeLayout location_now;
    Button on, off;
    RecyclerView recv_attend_record;
    TextView current_time,emp_name,emp_name_1,emp_dep_rank,location_tv;
    Button workday;
    ImageView home;
    MainActivity activity;
    ArrayList<AttendVO> list;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_attend, container, false);

        /*홈으로 가기*/
        home = v.findViewById(R.id.home);
        activity = (MainActivity) getActivity();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new HomeFragment());
            }
        });

        /*현재위치*/
        location_now = v.findViewById(R.id.location_now);
        location_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LocationNowActivity.class);
               startActivity(intent);
            }
        });

        /*현재위치 주소값*/
        //location_tv = v.findViewById(R.id.location_tv);

        /*현재시각 표시*/
        current_time = v.findViewById(R.id.current_time);
        current_time.setText(getCurrentTime());

        /*로그인한 사원의 이름*/
        emp_name = v.findViewById(R.id.emp_name);
        emp_name.setText(Common.loginInfo.getEmp_name());

        emp_name_1 = v.findViewById(R.id.emp_name_1);
        emp_name_1.setText(Common.loginInfo.getEmp_name()+"님 좋은 하루 되세요 😊");

        /*로그인한 사원의 부서명, 직급*/
        emp_dep_rank = v.findViewById(R.id.emp_dep_rank);
        emp_dep_rank.setText(Common.loginInfo.getDepartment_name()+" / "+Common.loginInfo.getRank_name());

        /*로그인한 사원의 출퇴근 상태 */



        /*출근 버튼 클릭*/
        on = v.findViewById(R.id.on);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog_on();

                /*출근 버튼 클릭시 출근 버튼 비활성화 처리*/
               // on.setVisibility(View.INVISIBLE);

                /* 출근 버튼 클릭시 근무 상태(출근/지각) 바뀌게 처리 */
            }
        });
        /*퇴근 버튼 클릭*/
        off = v.findViewById(R.id.off);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog_off();
                /*퇴근 버튼 클릭시 출근 버튼 비활성화 처리*/

                /*퇴근 버튼 클릭시 근무 상태 퇴근으로 바뀌게 처리*/

            }
        });



        /*리사이클러뷰_출퇴근기록*/
        recv_attend_record = v.findViewById(R.id.recv_attend_record);
        recv_attend_record.setAdapter(new Attend_Main_Adapter(inflater,list,getContext()));
        recv_attend_record.setLayoutManager(CommonMethod.getVManager(getContext()));

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

    //현재시각 가져오는 메소드
    private String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd \n  hh:mm:ss");
        String getTime = dateFormat.format(date);
        return getTime;
    }



    // dialog_on을 디자인하는 함수
    public void showDialog_on() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("출근")
                .setMessage("출근하시겠습니까? \n ")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "출근 처리되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "취소되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }

    // dialog_off을 디자인하는 함수
    public void showDialog_off() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("퇴근")
                .setMessage("퇴근하시겠습니까? \n퇴근 시 앱이 자동으로 종료됩니다. ")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "퇴근 처리되었습니다", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "취소되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}