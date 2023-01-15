package com.example.lastproject.code;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentCodeInfoBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class CodeInfoFragment extends Fragment {
    private FragmentCodeInfoBinding binding;
    private CodeVO vo;
    private MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCodeInfoBinding.inflate(inflater, container, false);
        activity = (MainActivity) getActivity();
        Bundle bundle = getArguments();
        vo = (CodeVO) bundle.getSerializable("vo");

        binding.tvTopCode.setText(vo.getCode_category());

        binding.tvBottomCode.setText(vo.getCode_num() + "");
        binding.edtCodeValue.setText(vo.getCode_value());
        binding.edtCodeValue2.setText(vo.getCode_value2());

        binding.tvCreateDate.setText(vo.getCreate_date().toString());

        if (vo.getCreater().equals("admin") || vo.getCreater().equals("pjy") || vo.getCreater().equals("csm")) {
            binding.tvCreater.setText("기본코드");
        } else {
            new CommonMethod().setParams("emp_no", vo.getCreater()).sendPost("findNameByNo.emp", (isResult, data) -> {
                if (isResult) {
                    binding.tvCreater.setText(data);
                }
            });
        }

        binding.ivBack.setOnClickListener(v -> {activity.onBackPressed();});
        binding.btnDelete.setOnClickListener(v -> {
            if(vo.getCreater().equals("admin") ||vo.getCreater().equals("pjy")||vo.getCreater().equals("csm")){
                Toast.makeText(activity, "기본 코드는 삭제할 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            new CommonMethod().setParams("vo",gson.toJson(vo)).sendPost("deleteBottomCode.cd",(isResult, data) -> {
                if(isResult){
                    activity.getSupportFragmentManager().popBackStack();
                }
            });

        });

        binding.btnEdit.setOnClickListener(v -> {
            if(vo.getCreater().equals("admin") ||vo.getCreater().equals("pjy")||vo.getCreater().equals("csm")){
                Toast.makeText(activity, "기본 코드는 변경할 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(binding.edtCodeValue.getText().toString().trim().length()<1){
                Toast.makeText(activity, "코드밸류 1은 반드시 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            vo.setCode_value(binding.edtCodeValue.getText().toString().trim());
            vo.setCode_value2(binding.edtCodeValue2.getText().toString().trim());
            vo.setEmp_no(Common.loginInfo.getEmp_no());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
           new CommonMethod().setParams("vo",gson.toJson(vo)).sendPost("updateBottomCode.cd",(isResult, data) -> {
               if(isResult){
                   activity.getSupportFragmentManager().popBackStack();
               }
           });

        });

        return binding.getRoot();
    }


}