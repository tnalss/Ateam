package com.example.lastproject.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentHomeBinding;
import com.google.android.material.datepicker.SingleDateSelector;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);

        binding.menu11.setClipToOutline(true);
        binding.menu12.setClipToOutline(true);
        binding.menu13.setClipToOutline(true);
        binding.menu21.setClipToOutline(true);
        binding.menu22.setClipToOutline(true);
        binding.menu23.setClipToOutline(true);
        //visibility 속성을 이용해서 일반회원의 경우 메뉴 몇 개를 숨겨야함..

        binding.tvEmpName.setText(Common.loginInfo.getEmp_name());
        binding.tvEmpDepRank.setText(Common.loginInfo.getDepartment_name()+" / "+Common.loginInfo.getRank_name());
        //쿼리 날려서 출퇴근 여부 파악
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attendOrNot",(isResult, data) -> {

        });

        View v = binding.getRoot();
        binding.menu11.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu1_1){
            //사원관리
        } else if ( v.getId() == R.id.menu1_2){
            //문서관리
        } else if ( v.getId() == R.id.menu1_3){
            //게시판
        } else if ( v.getId() == R.id.menu2_1){
            //지점관리
        } else if ( v.getId() == R.id.menu2_2){
            // 일정관리
        } else if ( v.getId() == R.id.menu2_3){
            // 전자결재
        }
    }
}