package com.example.lastproject.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
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

    void noResult(){
        if(list.size()==0){
            binding.tvNoResult.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoResult.setVisibility(View.GONE);
        }
    }
    void search(){
        if(binding.edtFind.length()>0){
            //쿼리날려서 리스트잡고 리스트바꿔서보내주자.
            new CommonMethod().setParams("keyword",binding.edtFind.getText().toString().trim()).sendPost("keyword.emp",(isResult, data) -> {
                if(isResult){
                    list = new Gson().fromJson(data,new TypeToken<ArrayList<EmployeeVO>>(){}.getType());

                    binding.recvEmpList.setAdapter(new EmpListAdapter(getLayoutInflater(),list,activity));
                    binding.recvEmpList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    noResult();
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new CommonMethod().sendPost("list.emp",(isResult, data) -> {
            if(isResult){
                list = new Gson().fromJson(data,new TypeToken<ArrayList<EmployeeVO>>(){}.getType());

                binding.recvEmpList.setAdapter(new EmpListAdapter(getLayoutInflater(),list,activity));
                binding.recvEmpList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                noResult();
            }
        });
    }

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



        //사원검색기능을 위한 디자인
        binding.ivPersonSearch.setOnClickListener(v->{
            if(binding.tvMenuTitle.getVisibility()==View.VISIBLE) {
                binding.tvMenuTitle.setVisibility(View.GONE);
                binding.edtFind.setVisibility(View.VISIBLE);
                binding.ivPersonSearch.setVisibility(View.GONE);
                binding.searchButton.setVisibility(View.VISIBLE);
            }
        });

        binding.searchButton.setOnClickListener(v -> {

            binding.tvMenuTitle.setVisibility(View.VISIBLE);
            binding.edtFind.setVisibility(View.GONE);
            binding.ivPersonSearch.setVisibility(View.VISIBLE);
            binding.searchButton.setVisibility(View.GONE);
            search();

        });

        //신규사원추가버튼
        binding.btnNewEmp.setOnClickListener(v -> {
            activity.changeFragment(new EmpInsertFragment());
        });

        //edittext 값변하면 실시간 검색기능
        binding.edtFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        return binding.getRoot();
    }
}