package com.example.lastproject.code;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentCodeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CodeFragment extends Fragment {

    private FragmentCodeBinding binding;
    private MainActivity activity;
    private ArrayList<CodeVO> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCodeBinding.inflate(inflater, container, false);
        activity = (MainActivity) getActivity();

        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });

        allList(inflater);

        binding.edtFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(binding.edtFind.getText().toString().trim().length()>0){
                    new CommonMethod().setParams("keyword",binding.edtFind.getText().toString().trim()).sendPost("findBy.cd",(isResult, data) -> {
                        if(isResult){
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                            list = gson.fromJson(data, new TypeToken<ArrayList<CodeVO>>() {}.getType());
                            noResult();
                            binding.recvTopCode.setAdapter(new CodeAdapter(inflater,list));
                            binding.recvTopCode.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return binding.getRoot();
    }
    void noResult(){
        if(list.size()==0){
            binding.tvNoResult.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoResult.setVisibility(View.GONE);
        }
    }
    void allList(LayoutInflater inflater){
        new CommonMethod().sendPost("topCodeList.cd",(isResult, data) -> {
            if(isResult){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                list = gson.fromJson(data, new TypeToken<ArrayList<CodeVO>>() {}.getType());
                binding.recvTopCode.setAdapter(new CodeAdapter(inflater,list));
                binding.recvTopCode.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            }
        });
    }

}