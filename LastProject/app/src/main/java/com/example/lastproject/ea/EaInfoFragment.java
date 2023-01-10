package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentEaInfoBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EaInfoFragment extends Fragment implements View.OnClickListener {
    String ea_num;
    ArrayList<EaVO> list;
    FragmentEaInfoBinding binding;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEaInfoBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();
        binding.cardvBack.setOnClickListener(this);
        ea_num = getArguments().getString("ea_num");
        new CommonMethod().setParams("ea_num",ea_num).sendPost("info.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,


                    
                    new TypeToken<ArrayList<EaVO>>(){}.getType());
            binding.tvInfoForm.setText(list.get(0).getEa_title().substring(list.get(0).getEa_title().indexOf("[")+1,list.get(0).getEa_title().indexOf("]")));
            binding.tvInfoEaTitle.setText(list.get(0).getEa_title());
            binding.tvInfoEaNo.setText(list.get(0).getEa_num());
            binding.tvInfoEmp.setText(list.get(0).getEmp_no());
            binding.tvInfoEaDep.setText(list.get(0).getEa_dummy());
            binding.tvInfoContent.setText(list.get(0).getEa_content());
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cardv_back){
            activity.onBackPressed();
        }
    }
}