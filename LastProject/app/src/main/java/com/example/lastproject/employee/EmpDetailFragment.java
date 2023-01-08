package com.example.lastproject.employee;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;


public class EmpDetailFragment extends Fragment {
    private EmployeeVO vo;
    private MainActivity activity;

    //상세화면 오기전에 해당회원의 정보를 조회해서 번들에 담아줘서 보내줘야합니다!
    //출근상태 status 변수를 활용해야합니다 프로필 위에 뭔가를 표시할까?

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tv_emp_name, tv_emp_birth, tv_emp_address, tv_emp_branch, tv_emp_dept, tv_emp_hire_date, tv_emp_rank, tv_emp_no, tv_emp_gender, tv_emp_email, tv_emp_phone, tv_emp_admin, tv_emp_salary, tv_emp_comm_pct;
        Button btn_emp_edit, btn_emp_fire;
        ImageView iv_back,iv_emp_profile;

        View v = inflater.inflate(R.layout.fragment_emp_detail, container, false);
        activity = (MainActivity) getActivity();
        Bundle bundle = getArguments();
        vo = (EmployeeVO) bundle.getSerializable("vo");
        String status = bundle.getString("status");

        tv_emp_name = v.findViewById(R.id.tv_emp_name);
        tv_emp_birth = v.findViewById(R.id.tv_emp_birth);
        tv_emp_address = v.findViewById(R.id.tv_emp_address);
        tv_emp_branch = v.findViewById(R.id.tv_emp_branch);
        tv_emp_dept = v.findViewById(R.id.tv_emp_dept);
        tv_emp_hire_date = v.findViewById(R.id.tv_emp_hire_date);
        tv_emp_no = v.findViewById(R.id.tv_emp_no);
        tv_emp_gender = v.findViewById(R.id.tv_emp_gender);
        tv_emp_email = v.findViewById(R.id.tv_emp_email);
        tv_emp_phone = v.findViewById(R.id.tv_emp_phone);
        tv_emp_admin = v.findViewById(R.id.tv_emp_admin);
        tv_emp_rank = v.findViewById(R.id.tv_emp_rank);
        btn_emp_edit = v.findViewById(R.id.btn_emp_edit);
        btn_emp_fire = v.findViewById(R.id.btn_emp_fire);
        tv_emp_comm_pct = v.findViewById(R.id.tv_emp_comm_pct);
        tv_emp_salary = v.findViewById(R.id.tv_emp_salary);
        iv_back = v.findViewById(R.id.iv_back);
        iv_emp_profile = v.findViewById(R.id.iv_emp_profile);

        //사번으로 조회
        tv_emp_name.setText(vo.getEmp_name());
        tv_emp_birth.setText(vo.getBirth().substring(0,10));
        tv_emp_address.setText(vo.getAddress().replace("/",""));
        tv_emp_branch.setText(vo.getBranch_name());
        tv_emp_dept.setText(vo.getDepartment_name());
        tv_emp_rank.setText(vo.getRank_name());
        tv_emp_no.setText(vo.getEmp_no());
        tv_emp_gender.setText(vo.getGender());
        tv_emp_email.setText(vo.getEmail());
        tv_emp_phone.setText(vo.getPhone());
        if (vo.getAdmin().equals("L0")) {
            tv_emp_admin.setText("일반 사용자");
        } else if (vo.getAdmin().equals("L1")) {
            tv_emp_admin.setText("관리자");
        } else if (vo.getAdmin().equals("X0")) {
            tv_emp_admin.setText("퇴사자");
        }
        tv_emp_hire_date.setText(vo.getHire_date().substring(0,10));
        if (vo.getCommission_pct() != 0) {
            tv_emp_comm_pct.setText(vo.getCommission_pct() * 100 + "%");
        } else {
            tv_emp_comm_pct.setText("0");
        }
        tv_emp_salary.setText(vo.getSalary() + "");

        if(vo.getProfile_path()!=null){
            Glide.with(this).load(vo.getProfile_path()).into(iv_emp_profile);
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
//수정
        btn_emp_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Fragment fr = new EmpUpdateFragment();
            fr.setArguments(bundle);
            activity.changeFragment(fr);
            }
        });
//퇴사
        btn_emp_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vo.getAdmin().equals("X0")) {
                    new AlertDialog.Builder(getContext()).setTitle("확인").setMessage("이미 퇴사한 사원입니다.")
                            .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                                new CommonMethod().setParams("emp_no", vo.getEmp_no()).sendPost("fire.emp", (isResult, data) -> {

                                });
                            }).show();
                    return;
                }
                new AlertDialog.Builder(getContext()).setTitle("확인").setMessage("퇴사 시키시겠습니까?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            new CommonMethod().setParams("emp_no", vo.getEmp_no()).sendPost("fire.emp", (isResult, data) -> {

                            });
                        }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                        }).show();
            }
        });

        return v;
    }
}