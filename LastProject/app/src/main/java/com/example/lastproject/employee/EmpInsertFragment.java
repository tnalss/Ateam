package com.example.lastproject.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentEmpInsertBinding;
import com.example.lastproject.databinding.FragmentManageEmpBinding;
import com.google.gson.Gson;


public class EmpInsertFragment extends Fragment {
    FragmentEmpInsertBinding binding;
    MainActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentEmpInsertBinding.inflate(inflater,container,false);
        //뒤로가기
        binding.ivBack.setOnClickListener(v -> {
           activity.onBackPressed();
        });
        // 신규사원 저장하는 부분인데 비밀번호를 난수로 이메일로 보내줘야할듯?


        binding.btnEmpInsert.setOnClickListener(v -> {
            //입력판단해야됨 길이로?

            EmployeeVO vo = new EmployeeVO();
            //문자열로변환필요
            vo.setEmp_name(binding.edtEmpName.getText().toString());
            if(! binding.radioMale.isChecked())
                vo.setGender("여");
            else
                vo.setGender("남");
            vo.setEmail(binding.edtEmpEmail.getText().toString());
            vo.setPhone(binding.edtEmpPhone.getText().toString());
            //다른정보들도 추가 요함

            new CommonMethod().setParams("param",vo).sendPostFile("insert.emp",null,(isResult, data) -> {
                if(isResult) {
                    Bundle bundle = new Bundle();
                    bundle.putString("emp_no",data);
                    Fragment fragment = new EmpDetailFragment();
                    fragment.setArguments(bundle);
                    activity.changeFragment(fragment);
                }
            });
        });


        View v = binding.getRoot();
        return v;
    }
}