package com.example.lastproject.ea;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.employee.EmployeeVO;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EaInfoAdapter extends RecyclerView.Adapter<EaInfoAdapter.ViewHolder> {
    int temp;
    LayoutInflater inflater;
    ArrayList<EaVO> ea_list;
    EmployeeVO vo;
    Context context;
    AlertDialog.Builder my_alert;
    MainActivity activity;


    public EaInfoAdapter(LayoutInflater inflater, ArrayList<EaVO> ea_list, Context context, int temp, MainActivity activity) {
        this.inflater = inflater;
        this.ea_list = ea_list;
        this.context = context;
        this.temp = temp;
        this.activity = activity;
        my_alert = new AlertDialog.Builder(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_info_line, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        Log.d("TAG", "onBindViewHolder: " + i);
        if (temp == 1) {
            if (i == 0) {
                new CommonMethod().setParams("emp_no", ea_list.get(i).getEmp_no()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setText(ea_list.get(i).getEa_date().toString());
                });
            } else {

                new CommonMethod().setParams("emp_no", ea_list.get(i - 1).getEa_receiver()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_fixed.setText("결재");
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());


                    if (Common.loginInfo.getEmp_no().equals(vo.getEmp_no()) && !(ea_list.get(i - 1).getEa_r_statuas().equals("결재완료"))) {
                        h.line_btn_sign.setVisibility(View.VISIBLE);
                        h.tv_info_status.setText(ea_list.get(i - 1).getEa_r_statuas());
                        h.tv_info_status.setVisibility(View.VISIBLE);
                        h.tv_info_date.setVisibility((View.GONE));
                    } else {
                        if (ea_list.get(i - 1).getEa_r_statuas().equals("결재완료")) {
                            h.imgv_stamp.setVisibility(View.VISIBLE);
                            h.line_btn_sign.setVisibility(View.GONE);
                            h.tv_info_date.setText(ea_list.get(i - 1).getEa_a_date().toString());
                        } else {
                            h.tv_info_date.setVisibility((View.GONE));
                            h.tv_info_status.setText(ea_list.get(i - 1).getEa_r_statuas());
                            h.tv_info_status.setVisibility(View.VISIBLE);
                        }

                    }


                    //결재 버튼 클릭시
                    h.btn_approved.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            my_alert.setTitle("알림");
                            my_alert.setMessage("결재하시겠습니까?");
                            my_alert.setPositiveButton("결재하기", (dialog, which) -> {
                                Toast.makeText(context, "결재됨.", Toast.LENGTH_SHORT).show();

                                // 마지막 결재자인 경우 문서의 ea_status를 E7으로 변경
                                new CommonMethod().setParams("ea_num", ea_list.get(0).getEa_num()).sendPost("howManySigned.ea", (isResult1, data1) -> {
                                    if (isResult1) {
                                        if(data1!=null) {
                                            if (new Gson().fromJson(data1, Integer.class) == ea_list.size()) {
                                                new CommonMethod().setParams("ea_num", ea_list.get(0).getEa_num()).sendPost("allSignComplete.ea", (isResult2, data2) -> {

                                                });
                                            }
                                        }
                                    }
                                });

//                              count_r_statuas++;
//                               if(count_r_statuas==ea_list.size()){
//                                    new CommonMethod().setParams("ea_num", ea_list.get(0).getEa_num()).sendPost("allSignComplete.ea",(isResult1, data1) -> {
//
//                                    });
//                               }
//                               어댑터에서 결재완료상태가 몇명인지 셈
//                               결재완료자 수가 리스트 사이즈랑 같다면 마지막 결재자가 결재할때
//                               대기인 코드를 결재완료 코드로 바꿈!
//                                       (안됨 리사이클러뷰라서..........)
// 몇개인지 쿼리를 날리고 사이즈와 비교

                                new CommonMethod().setParams("ea_status", "E7").setParams("emp_no", Common.loginInfo.getEmp_no()).setParams("ea_num", ea_list.get(0).getEa_num()).sendPost("sign_status.ea", (isResult, data) -> {
                                    Fragment f = new EaInfoFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("ea_num", ea_list.get(0).getEa_num());
                                    bundle.putInt("no", 1);
                                    f.setArguments(bundle);
                                    activity.changeFragment(f);
                                });
                            });
                            my_alert.setNegativeButton("취소", (dialog, which) -> {
                                Toast.makeText(context, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                            });
                            my_alert.show();
                        }
                    });

                    //반려 버튼 클릭시
                    h.btn_denied.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                });
            }
        } else if (temp == 0) {
            if (i == 0) {
                new CommonMethod().setParams("emp_no", ea_list.get(i).getEmp_no()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setText(ea_list.get(i).getEa_date().toString());
                });
            } else {
                new CommonMethod().setParams("emp_no", ea_list.get(i - 1).getEa_receiver()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_fixed.setText("결재");
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setVisibility(View.GONE);
                    h.tv_info_status.setVisibility(View.VISIBLE);
                    h.tv_info_status.setText(ea_list.get(i - 1).getEa_r_statuas());
                });
            }
        } else if (temp == 2) {
            if (i == 0) {
                new CommonMethod().setParams("emp_no", ea_list.get(i).getEmp_no()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setText(ea_list.get(i).getEa_date().toString());
                });
            } else {
                new CommonMethod().setParams("emp_no", ea_list.get(i - 1).getEa_receiver()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_fixed.setText("결재");
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setVisibility(View.GONE);
                    h.tv_info_status.setVisibility(View.VISIBLE);
                    h.tv_info_status.setText(ea_list.get(i - 1).getEa_r_statuas());

                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return ea_list.size() + 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout line_btn_sign;
        Button btn_approved, btn_denied;
        ImageView imgv_stamp;
        TextView tv_info_name, tv_info_email, tv_info_dep, tv_info_date, tv_fixed, tv_info_status;

        public ViewHolder(@NonNull View v) {
            super(v);
            line_btn_sign = v.findViewById(R.id.line_btn_sign);
            btn_approved = v.findViewById(R.id.btn_approved);
            btn_denied = v.findViewById(R.id.btn_denied);
            imgv_stamp = v.findViewById(R.id.imgv_stamp);
            tv_info_name = v.findViewById(R.id.tv_info_name);
            tv_info_email = v.findViewById(R.id.tv_info_email);
            tv_info_dep = v.findViewById(R.id.tv_info_dep);
            tv_info_date = v.findViewById(R.id.tv_info_date);
            tv_fixed = v.findViewById(R.id.tv_fixed);
            tv_info_status = v.findViewById(R.id.tv_info_status);
        }
    }


}
