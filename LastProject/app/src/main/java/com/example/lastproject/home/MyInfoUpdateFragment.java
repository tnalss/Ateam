package com.example.lastproject.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentMyInfoBinding;
import com.example.lastproject.databinding.FragmentMyInfoUpdateBinding;
import com.example.lastproject.employee.EmployeeVO;
import com.example.lastproject.login.LoginVO;
import com.example.lastproject.login.LogoutActivity;
import com.google.gson.Gson;

public class MyInfoUpdateFragment extends Fragment {
    FragmentMyInfoUpdateBinding binding;
    MainActivity activity;
    LoginVO vo = Common.loginInfo;
    private EmployeeVO emp_vo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyInfoUpdateBinding.inflate(inflater, container, false);
        activity = (MainActivity) getActivity();

        if (vo.getProfile_path() != null) {
            Glide.with(this).load(vo.getProfile_path()).error(R.drawable.error_user_profile).into(binding.ivEmpProfile);
        }

        binding.tvEmpNo.setText(vo.getEmp_no());
        binding.edtEmpName.setText(vo.getEmp_name());
        binding.tvBirth.setText(vo.getBirth().substring(0, 10));
        binding.edtEmpEmail.setText(vo.getEmail());
        binding.edtEmpPhone.setText(vo.getPhone());
        binding.tvDept.setText(vo.getDepartment_name());
        binding.tvBranch.setText(vo.getBranch_name());
        binding.tvRank.setText(vo.getRank_name());

        int div = vo.getAddress().indexOf("/");
        if (div != -1) {
            String tempAddress = vo.getAddress().substring(0, div - 1);
            binding.edtAddress.setText(tempAddress);
            binding.edtAddressDetail.setText(vo.getAddress().substring(div + 2));
        } else {
            binding.edtAddress.setText(vo.getAddress());
        }

        binding.btnEmpUpdate.setOnClickListener(v -> {
            //입력판단 입력유무판단.
            if (binding.edtEmpName.getText().length() < 1 || binding.edtEmpPhone.getText().length() < 1 ||
                    binding.edtAddress.getText().length() < 1 || binding.tvBirth.getText().length() < 1 ||
                    binding.edtAddressDetail.getText().length() < 1) {
                Toast.makeText(activity, "빈칸을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!binding.edtEmpPw.getText().toString().equals(binding.edtEmpConpw.getText().toString())){
                Toast.makeText(activity, "비밀번호와 비밀번호 확인이 다릅니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            emp_vo = new EmployeeVO();
            emp_vo.setEmp_no(vo.getEmp_no());
            emp_vo.setEmail(binding.edtEmpEmail.getText().toString());
            emp_vo.setPhone(binding.edtEmpPhone.getText().toString());
            emp_vo.setEmp_pw(binding.edtEmpPw.getText().toString());

            new CommonMethod().setParams("emp_vo",new Gson().toJson(emp_vo)).sendPost("myinfo_update.emp",(isResult, data) -> {
                Common.loginInfo.setEmail(emp_vo.getEmail());
                Common.loginInfo.setPhone(emp_vo.getPhone());
                Common.loginInfo.setEmp_pw(emp_vo.getEmp_pw());
                Intent intent = new Intent(getContext(), LogoutActivity.class);
                activity.changeFragment(new MyInfoFragment());

            });




        });

        binding.ivBack.setOnClickListener(v -> {
            activity.getSupportFragmentManager().popBackStack();
        });


        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}