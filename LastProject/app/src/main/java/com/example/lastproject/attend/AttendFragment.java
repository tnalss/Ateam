package com.example.lastproject.attend;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.al.AL_Apply_Fragment;
import com.example.lastproject.common.Common;
import com.example.lastproject.home.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.NaverMap;


import org.apache.poi.util.StringUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendFragment extends Fragment {

    RelativeLayout location_now;
    Button on, off;
    RecyclerView recv_attend_record;
    TextView current_time,emp_name,emp_name_1,emp_dep_rank,location_tv,now;
    Button workday, apply;
    ImageView  iv_emp_profile, to_att_act,qr;
    MainActivity activity;
    ArrayList<AttendVO> list;
    AttendVO vo = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_attend, container, false);
        activity = (MainActivity)getActivity();

        /*????????????*/
        location_now = v.findViewById(R.id.location_now);
        location_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LocationNowActivity.class);
               startActivity(intent);
            }
        });

        /*???????????? ?????????*/
        location_tv = v.findViewById(R.id.location_tv);



//?????????????????? ??????????????? ???????????? ??????????
        /*???????????? ??????*/
        current_time = v.findViewById(R.id.current_time);
        current_time.setText(getCurrentTime());

        /*???????????? ????????? ??????*/
        emp_name = v.findViewById(R.id.emp_name);
        emp_name.setText(Common.loginInfo.getEmp_name());

        emp_name_1 = v.findViewById(R.id.emp_name_1);
        emp_name_1.setText(Common.loginInfo.getEmp_name()+"??? ?????? ?????? ????????? ????");

        /*???????????? ????????? ?????????, ??????*/
        emp_dep_rank = v.findViewById(R.id.emp_dep_rank);
        emp_dep_rank.setText(Common.loginInfo.getDepartment_name()+" / "+Common.loginInfo.getRank_name());

        /*???????????? ????????? ????????? ?????????*/
        iv_emp_profile = v.findViewById(R.id.iv_emp_profile);
        if(Common.loginInfo.getProfile_path()!=null){
            Glide.with(this).load(Common.loginInfo.getProfile_path()).error(R.drawable.error_user_profile).into(iv_emp_profile);
        }
        /*???????????? ????????? ?????? ??????*/
        now = v.findViewById(R.id.now);
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attend_today.at",(isResult, data) -> {
             vo =new Gson().fromJson(data,AttendVO.class);
            if(vo != null && vo.getAtt_state() != null){
            now.setText(vo.getAtt_state());
            }
        });

        to_att_act = v.findViewById(R.id.to_att_act);
        to_att_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AttendActivity.class);
                startActivity(intent);
            }
        });

        /*????????????
        qr = v.findViewById(R.id.qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ScanActivity.class);
                startActivity(intent);
            }
        });*/

        /*?????? ?????? ??????*/

        on = v.findViewById(R.id.on);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(vo != null && vo.getAttend_on() != null) {
                   Toast.makeText(getActivity(), "?????? ????????? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
               }else {
                   showDialog_on();
               }
                on.setEnabled(false);
                selectList();
            }
        });



        /*?????? ?????? ??????*/
        off = v.findViewById(R.id.off);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vo != null && vo.getAttend_off() != null) {
                    Toast.makeText(getActivity(), "?????? ????????? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
                }else {
                    showDialog_off();
                }
                off.setEnabled(false);
                selectList();
                getnow();
            }
        });

        /*????????? ?????? ??????*/
        apply = v.findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new AL_Apply_Fragment());
            }
        });


        /*??????????????????_???????????? ????????? ?????? ???????????????*/
        recv_attend_record = v.findViewById(R.id.recv_attend_record);
        selectList();

        return v;
    }

    //???????????? ???????????? ?????????
    private String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd \n  hh:mm:ss");
        String getTime = dateFormat.format(date);
        return getTime;
    }



    // dialog_on??? ??????????????? ??????
    public void showDialog_on() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("??????")
                .setMessage("????????????????????????? \n ")
                .setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    /*????????????*/
                        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attend_on.at",(isResult, data) -> {
                              AttendVO vo =new Gson().fromJson(data,AttendVO.class);
                            selectList();
                                now.setText(vo.getAtt_state());

                        });
                        Toast.makeText(getActivity(), "?????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                        selectList(); //1/11 csm ????????? ?????? ??????????????? ??????
                    }
                })
                .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }

    // dialog_off??? ??????????????? ??????
    public void showDialog_off() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("??????")
                .setMessage("?????????????????????????  ")
                .setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*??????  ??????*/
                        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attend_off.at",(isResult, data) -> {
                            AttendVO vo =new Gson().fromJson(data,AttendVO.class);
                            selectList();

                            now.setText(vo.getAtt_state());
                        });
                        Toast.makeText(getActivity(), "?????? ?????????????????????", Toast.LENGTH_SHORT).show();
                        selectList();//1/11 csm ????????? ?????? ??????????????? ??????
                    }
                })
                .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }

    /*????????? ?????? ???????????? */
    public void selectList(){
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("list_emp_since.at",(isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<AttendVO>>(){}.getType());
            recv_attend_record.setAdapter(new Attend_Main_Adapter(getLayoutInflater(),list,activity));
            recv_attend_record.setLayoutManager(CommonMethod.getVManager(getContext()));

        });

    }

    public  void getnow(){
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attend_today.at",(isResult, data) -> {
            vo =new Gson().fromJson(data,AttendVO.class);
            if(vo != null && vo.getAtt_state() != null){
                now.setText(vo.getAtt_state());
            }
        });
    }

}