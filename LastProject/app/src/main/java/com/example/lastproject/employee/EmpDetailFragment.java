package com.example.lastproject.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentEmpDetailBinding;


public class EmpDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tv_emp_name,tv_emp_birth,tv_emp_address,tv_emp_branch,tv_emp_dept,tv_emp_hire_date
                ,tv_emp_rank,tv_emp_no,tv_emp_gender,tv_emp_email,tv_emp_phone,tv_emp_admin;
        Button btn_emp_edit, btn_emp_fire;
        ImageView iv_back;

        View v = inflater.inflate(R.layout.fragment_emp_detail, container, false);
        Bundle bundle = getArguments();
        EmployeeVO vo = (EmployeeVO) bundle.getSerializable("vo");

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
        iv_back = v.findViewById(R.id.iv_back);

        tv_emp_name.setText(vo.getEmp_name());
        tv_emp_birth.setText(vo.getBirth());
        tv_emp_address.setText(vo.getAddress());
        tv_emp_branch.setText(vo.getBranch_name());
        tv_emp_dept.setText(vo.getDepartment_name());
        tv_emp_rank.setText(vo.getRank_name());
        tv_emp_no.setText(vo.getEmp_no());
        tv_emp_gender.setText(vo.getGender());
        tv_emp_email.setText(vo.getEmail());
        tv_emp_phone.setText(vo.getPhone());
        tv_emp_admin.setText(vo.getAdmin());
        tv_emp_hire_date.setText(vo.getHire_date());

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_emp_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_emp_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}