package com.example.lastproject.employee;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.common.SimpleCode;
import com.example.lastproject.databinding.FragmentEmpInsertBinding;
import com.example.lastproject.databinding.FragmentManageEmpBinding;
import com.example.lastproject.ea.EaCodeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class EmpInsertFragment extends Fragment implements View.OnClickListener {
    private FragmentEmpInsertBinding binding;
    private MainActivity activity;
    private EmployeeVO vo= new EmployeeVO();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentEmpInsertBinding.inflate(inflater,container,false);
        //뒤로가기
        binding.ivBack.setOnClickListener(v -> {
           activity.onBackPressed();
        });
        // 신규사원 저장하는 부분인데 비밀번호를 난수로 이메일로 보내줘야할듯?


        binding.llBirth.setOnClickListener(this);

        // 지점 목록
        new CommonMethod().setParams("top_code","B").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                ArrayList<SimpleCode> dep_list;
                dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> b = new ArrayList<>();
                for (int i = 0 ; i < dep_list.size() ; i++){
                    b.add(dep_list.get(i).getCode_value());
                }
                binding.spnBranch.attachDataSource(b);
            }
        });

        //부서 목록
        new CommonMethod().setParams("top_code","D").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
            ArrayList<SimpleCode> dep_list;
            dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
            ArrayList<String> d = new ArrayList<>();
            for (int i = 0 ; i < dep_list.size() ; i++){
                d.add(dep_list.get(i).getCode_value());
            }
            binding.spnDept.attachDataSource(d);
            }
        });
        //직책 목록
        new CommonMethod().setParams("top_code","R").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                ArrayList<SimpleCode> dep_list;
                dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> r = new ArrayList<>();
                for (int i = 0 ; i < dep_list.size() ; i++){
                    r.add(dep_list.get(i).getCode_value());
                }
                binding.spnRank.attachDataSource(r);
            }
        });


        binding.btnEmpInsert.setOnClickListener(v -> {
            //입력판단해야됨 길이로?/////////////////////////////////////////입력유무판단.


            //문자열로변환필요
            vo.setEmp_name(binding.edtEmpName.getText().toString());
            if(! binding.radioMale.isChecked())
                vo.setGender("여");
            else
                vo.setGender("남");
            vo.setEmail(binding.edtEmpEmail.getText().toString());
            vo.setPhone(binding.edtEmpPhone.getText().toString());
            //다른정보들도 추가 요함 생일은 다이어로그에서 알아서 넣음
            vo.setBranch_name(binding.spnBranch.getText().toString());
            vo.setDepartment_name(binding.spnDept.getText().toString());
            vo.setRank_name(binding.spnRank.getText().toString());


            new CommonMethod().setParams("param",vo).sendPostFile("insert.emp",null,(isResult, data) -> {
                if(isResult) {
                    EmployeeVO vo2 = new Gson().fromJson(data,EmployeeVO.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("vo",vo2);
                    Fragment fragment = new EmpDetailFragment();
                    fragment.setArguments(bundle);
                    activity.changeFragment(fragment);
                }
            });
        });


        View v = binding.getRoot();
        return v;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvBirth.setText(year+"년 "+monthOfYear+"월 "+dayOfMonth+"일");
            vo.setBirth(year+"/"+monthOfYear+"/"+dayOfMonth);
        }
    };


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ll_birth || v.getId()==R.id.tv_birth){
            DatePickerDialog dialog = new DatePickerDialog(getContext(), listener, 1990, 6, 1);
            dialog.getDatePicker().setSpinnersShown(true);
            dialog.getDatePicker().setCalendarViewShown(false);
            //스피너로 보이게 왜 안되냐?
            dialog.show();
        }
    }


}