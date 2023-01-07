package com.example.lastproject.calendar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentCalendarBinding;
import com.example.lastproject.databinding.FragmentEmpInsertBinding;
import com.example.lastproject.employee.EmployeeVO;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {
    FragmentCalendarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater,container,false);

        binding.tvCalendarIntro.setText(Common.loginInfo.getEmp_name()+"님");
        binding.tvDeptSchedule.setText(Common.loginInfo.getDepartment_name()+ " 일정");


        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        new CommonMethod().sendPost("compPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.recvCompSchedule.setAdapter(new ScheduleAdapter(getLayoutInflater(),list));
                binding.recvCompSchedule.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

                binding.tvCountComp.setText(list.size()+"");
          }
       });

        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("deptPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.recvDeptSchedule.setAdapter(new ScheduleAdapter(getLayoutInflater(),list));
                binding.recvDeptSchedule.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

                binding.tvCountDept.setText(list.size()+"");
            }
        });

        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("peronalPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.recvPersonalSchedule.setAdapter(new ScheduleAdapter(getLayoutInflater(),list));
                binding.recvPersonalSchedule.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

                binding.tvCountPersonal.setText(list.size()+"");
            }
        });

        return binding.getRoot();
    }


//        new CommonMethod().sendPost(param,(isResult, data) -> {
//            if(isResult){
//                Bundle bundle = new Bundle();
//                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
//
//            }
//        });

}