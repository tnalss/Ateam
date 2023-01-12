package com.example.lastproject.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.attend.AttendVO;
import com.example.lastproject.calendar.ScheduleVO;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentMyInfoBinding;
import com.example.lastproject.employee.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class MyInfoFragment extends Fragment {

    FragmentMyInfoBinding binding;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMyInfoBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();
        if(Common.loginInfo.getProfile_path()!=null){
            Glide.with(this).load(Common.loginInfo.getProfile_path()).error(R.drawable.error_user_profile).into(binding.cvEmpProfile);
        }

        binding.tvEmpName.setText(Common.loginInfo.getEmp_name());
        binding.tvBranchName.setText(Common.loginInfo.getBranch_name());
        binding.tvDeptRankName.setText(Common.loginInfo.getDepartment_name()+" / " + Common.loginInfo.getRank_name());
        binding.tvEmpEmail.setText(Common.loginInfo.getEmail());
        binding.ivBack.setOnClickListener(v -> {
            activity.getSupportFragmentManager().popBackStack();
        });

        // 일정 조회 세가지
        new CommonMethod().sendPost("compPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());

                binding.tvCountComp.setText(list.size()+"");
            }
        });

        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("deptPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.tvCountDept.setText(list.size()+"");
            }
        });

        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("personalPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());

                binding.tvCountPersonal.setText(list.size()+"");

            }
        });

        // 스케쥴 부분 누르면 스케쥴로
        binding.llMySchedule.setOnClickListener(v -> {
            activity.btm_nav.setSelectedItemId(R.id.btm_item2);
        });

        // 정보변경 누르면 정보변경으로
        binding.tvMyInfoUpdate.setOnClickListener(v -> {
            activity.changeFragment(new MyInfoUpdateFragment());
        });

        // 7일간 본인의 근태 조회
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("list_7days.at",(isResult, data) -> {
           ArrayList<AttendVO> list  = new Gson().fromJson(data,new TypeToken<ArrayList<AttendVO>>(){}.getType());
        binding.recvAttend7daysRecord.setAdapter(new DaysAdapter(getLayoutInflater(),list));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        //binding.recvAttend7daysRecord.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.recvAttend7daysRecord.setLayoutManager(mLayoutManager);
        binding.recvAttend7daysRecord.smoothScrollToPosition(0);

        });




        return binding.getRoot();
    }
}