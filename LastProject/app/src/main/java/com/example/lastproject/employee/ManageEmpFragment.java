package com.example.lastproject.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;

import com.example.lastproject.databinding.FragmentManageEmpBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ManageEmpFragment extends Fragment {

    FragmentManageEmpBinding binding;
    ArrayList<EmployeeVO> list;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageEmpBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        new CommonMethod().sendPost("list.emp",(isResult, data) -> {
            if(isResult){
                list = new Gson().fromJson(data,new TypeToken<ArrayList<EmployeeVO>>(){}.getType());

                binding.recvEmpList.setAdapter(new EmpListAdapter(getLayoutInflater(),list,activity));
                binding.recvEmpList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            }
        });

        //사원검색기능 구현해야됨
        binding.ivPersonSearch.setOnClickListener(v->{

        });

        //신규사원추가버튼
        binding.btnNewEmp.setOnClickListener(v -> {
            activity.changeFragment(new EmpInsertFragment());
        });


        View v = binding.getRoot();
        return v;
    }
}