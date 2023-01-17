package com.example.lastproject.ea;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentEaInfoBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;


public class EaInfoFragment extends Fragment implements View.OnClickListener {
    HashMap<String, Object> map;
    String ea_num;
    ArrayList<EaVO> list;
    FragmentEaInfoBinding binding;
    MainActivity activity;
    AlertDialog.Builder my_alert;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEaInfoBinding.inflate(inflater,container,false);
        my_alert = new AlertDialog.Builder(getContext());
        activity = (MainActivity) getActivity();
        if(getArguments().getInt("no") == 1){
            binding.cardvRetry.setVisibility(View.GONE);
        }else if(getArguments().getInt("no") == 2){
            binding.cardvRetry.setVisibility(View.GONE);
            binding.cardvDelete.setVisibility(View.VISIBLE);
            binding.cardvApply.setVisibility(View.VISIBLE);
            binding.cardvModify.setVisibility(View.VISIBLE);
        }

        binding.cardvBack.setOnClickListener(this);
        binding.cardvRetry.setOnClickListener(this);
        binding.cardvDelete.setOnClickListener(this);
        binding.cardvApply.setOnClickListener(this);

        ea_num = getArguments().getString("ea_num");
        new CommonMethod().setParams("ea_num",ea_num).sendPost("info.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaVO>>(){}.getType());
            binding.tvInfoForm.setText(list.get(0).getEa_title().substring(list.get(0).getEa_title().indexOf("[")+1,list.get(0).getEa_title().indexOf("]")));
            binding.tvInfoEaTitle.setText(list.get(0).getEa_title());
            binding.tvInfoEaNo.setText(list.get(0).getEa_num());
            binding.tvInfoEmp.setText(list.get(0).getEmp_name());
            binding.tvInfoEaDep.setText(list.get(0).getEa_dummy());
            binding.tvInfoContent.setText(list.get(0).getEa_content());
            binding.recvEaInfoLine.setAdapter(new EaInfoAdapter(inflater,list,getContext(),getArguments().getInt("no"),activity));
            binding.recvEaInfoLine.setLayoutManager(CommonMethod.getHManager(getContext()));

        });



        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cardv_back){
            activity.onBackPressed();
        }else if(v.getId() == R.id.cardv_retry){
            map = new HashMap<>();
            map.put("ea_num",list.get(0).getEa_num());
            map.put("status", "E4");
            my_alert.setTitle("알림");
            my_alert.setMessage("현재 문서를 회수 하시겠습니까?");
            //OK 버튼 눌렀을 때
            my_alert.setPositiveButton("회수하기", (dialog, which) -> {
                Toast.makeText(getContext(), "회수완료", Toast.LENGTH_SHORT).show();
                new CommonMethod().setParams("map", new Gson().toJson(map) ).sendPost("update_status.ea", (isResult, data) -> {
                    activity.changeFragment(new EaDraftBoxFragment());
                });
            });
            my_alert.setNegativeButton("취소",(dialog, which) -> {
                Toast.makeText(getContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            });
            my_alert.show();

        }else if(v.getId() == R.id.cardv_delete){
            my_alert.setTitle("알림");
            my_alert.setMessage("현재 문서를 삭제 하시겠습니까?");
            my_alert.setPositiveButton("삭제하기", (dialog, which) -> {
                Toast.makeText(getContext(), "삭제완료", Toast.LENGTH_SHORT).show();
                new CommonMethod().setParams("num", list.get(0).getEa_num()).sendPost("retry_delete.ea", (isResult, data) -> {
                    activity.changeFragment(new EaRetryBoxFragment());
                });
            });
            my_alert.setNegativeButton("취소",(dialog, which) -> {
                Toast.makeText(getContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            });
            my_alert.show();
        }else if(v.getId() == R.id.cardv_apply){
            my_alert.setTitle("알림");
            my_alert.setMessage("현재 문서를 다시 상신 하시겠습니까?");
            my_alert.setPositiveButton("상신하기", (dialog, which) -> {
                Toast.makeText(getContext(), "상신완료", Toast.LENGTH_SHORT).show();
                new CommonMethod().setParams("num", list.get(0).getEa_num()).sendPost("retry_draft.ea", (isResult, data) -> {
                    activity.changeFragment(new EaRetryBoxFragment());
                });
            });
            my_alert.setNegativeButton("취소",(dialog, which) -> {
                Toast.makeText(getContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            });
            my_alert.show();
        }
    }
}