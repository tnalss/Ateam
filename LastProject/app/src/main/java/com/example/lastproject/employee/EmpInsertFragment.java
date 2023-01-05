package com.example.lastproject.employee;

import android.app.DatePickerDialog;
import android.os.Bundle;

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
import com.example.lastproject.databinding.FragmentEmpInsertBinding;
import com.example.lastproject.databinding.FragmentManageEmpBinding;
import com.example.lastproject.ea.EaCodeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EmpInsertFragment extends Fragment implements View.OnClickListener {
    private FragmentEmpInsertBinding binding;
    private MainActivity activity;
    private EmployeeVO vo= new EmployeeVO();
    private EaCodeVO dep_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //부서목록긁어오기
        new CommonMethod().setParams("cate","D").sendPost("code_list.ea",(isResult, data) -> {
            dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<EaCodeVO>>(){}.getType());
        });

    }

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














        binding.btnEmpInsert.setOnClickListener(v -> {
            //입력판단해야됨 길이로?


            //문자열로변환필요
            vo.setEmp_name(binding.edtEmpName.getText().toString());
            if(! binding.radioMale.isChecked())
                vo.setGender("여");
            else
                vo.setGender("남");
            vo.setEmail(binding.edtEmpEmail.getText().toString());
            vo.setPhone(binding.edtEmpPhone.getText().toString());
            //다른정보들도 추가 요함


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