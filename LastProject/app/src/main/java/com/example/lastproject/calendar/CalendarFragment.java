package com.example.lastproject.calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentCalendarBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CalendarFragment extends Fragment implements View.OnClickListener {
    FragmentCalendarBinding binding;
    MainActivity activity;

    @Override
    public void onResume() {
        super.onResume();
        new CommonMethod().sendPost("compPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.recvCompSchedule.setAdapter(new ScheduleAdapter(getLayoutInflater(),list, (MainActivity) getActivity()));
                binding.recvCompSchedule.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

                binding.tvCountComp.setText(list.size()+"");
                if(list.size()==0){
                    binding.llCompanySchedule.setVisibility(View.VISIBLE);
                }
            }
        });

        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("deptPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.recvDeptSchedule.setAdapter(new ScheduleAdapter(getLayoutInflater(),list,(MainActivity) getActivity()));
                binding.recvDeptSchedule.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

                binding.tvCountDept.setText(list.size()+"");
                if(list.size()==0){
                    binding.llDepartmentSchedule.setVisibility(View.VISIBLE);
                }
            }
        });

        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("personalPeriod.sche",(isResult, data) -> {
            if(isResult){
                ArrayList<ScheduleVO>  list = new Gson().fromJson(data,new TypeToken<ArrayList<ScheduleVO>>(){}.getType());
                binding.recvPersonalSchedule.setAdapter(new ScheduleAdapter(getLayoutInflater(),list,(MainActivity) getActivity()));
                binding.recvPersonalSchedule.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

                binding.tvCountPersonal.setText(list.size()+"");
                if(list.size()==0){
                    binding.llPersonalSchedule.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();
        binding.tvCalendarIntro.setText(Common.loginInfo.getEmp_name()+"님");
        binding.tvDeptSchedule.setText(Common.loginInfo.getDepartment_name()+ " 일정");

        if( Common.loginInfo.getRank_name().equals("사원")){
            binding.tvAddDepartmentSchedule.setVisibility(View.GONE);
            binding.ivAddDepartmentSchedule.setVisibility(View.GONE);
        }
        if( Common.loginInfo.getAdmin().equals("L1")){
            binding.tvAddCompanySchedule.setVisibility(View.VISIBLE);
            binding.ivAddCompanySchedule.setVisibility(View.VISIBLE);
            binding.tvAddDepartmentSchedule.setVisibility(View.VISIBLE);
            binding.ivAddDepartmentSchedule.setVisibility(View.VISIBLE);
        } else {
            binding.tvAddCompanySchedule.setVisibility(View.GONE);
            binding.ivAddCompanySchedule.setVisibility(View.GONE);
        }

        binding.tvAddCompanySchedule.setOnClickListener(this);
        binding.ivAddCompanySchedule.setOnClickListener(this);
        binding.tvAddDepartmentSchedule.setOnClickListener(this);
        binding.ivAddDepartmentSchedule.setOnClickListener(this);
        binding.tvAddPersonalSchedule.setOnClickListener(this);
        binding.ivAddPersonalSchedule.setOnClickListener(this);

        //binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());


        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        // 일정추가 글씨 눌렀을때 처리

        if(v.getId() == R.id.tv_add_company_schedule || v.getId() == R.id.iv_add_company_schedule){
            Bundle bundle = new Bundle();
            bundle.putString("cate","회사");
            Fragment fragment = new ScheduleInsertFragment();
            fragment.setArguments(bundle);
            activity.changeFragment(fragment);

        } else if (v.getId() ==R.id.tv_add_department_schedule ||v.getId() ==R.id.iv_add_department_schedule){
            Bundle bundle = new Bundle();
            bundle.putString("cate","부서");
            Fragment fragment = new ScheduleInsertFragment();
            fragment.setArguments(bundle);
            activity.changeFragment(fragment);

        } else if(v.getId() == R.id.tv_add_personal_schedule||v.getId() == R.id.iv_add_personal_schedule){

            Bundle bundle = new Bundle();
            bundle.putString("cate","개인");

            Fragment fragment = new ScheduleInsertFragment();
            fragment.setArguments(bundle);
            activity.changeFragment(fragment);

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

}