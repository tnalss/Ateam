package com.example.lastproject.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentHomeBinding;
import com.example.lastproject.databinding.FragmentManageEmpBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ManageEmpFragment extends Fragment {

    FragmentManageEmpBinding binding;
    ArrayList<EmployeeVO> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageEmpBinding.inflate(inflater,container,false);
        new CommonMethod().sendPost("list.emp",(isResult, data) -> {
            if(isResult){
                list = new Gson().fromJson(data,new TypeToken<ArrayList<EmployeeVO>>(){}.getType());

                binding.recvEmpList.setAdapter(new EmpListAdapter(getLayoutInflater(),list));
                binding.recvEmpList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            }
        });

        View v = binding.getRoot();
        return v;
    }
}