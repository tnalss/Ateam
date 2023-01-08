package com.example.lastproject.employee;

import static android.app.Activity.RESULT_OK;

import static com.example.lastproject.common.Common.SEARCH_ADDRESS_ACTIVITY;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import com.example.lastproject.common.SimpleCode;
import com.example.lastproject.common.WebViewActivity;
import com.example.lastproject.databinding.FragmentEmpInsertBinding;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;



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

        //스피너 클릭시 키보드 끄기 기능 필요함!//터치리스너로 하였음!
        binding.svForm.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        binding.llBirth.setOnClickListener(this);
        binding.edtAddress.setOnClickListener(this);
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
            //입력유무판단.
            if(binding.edtEmpName.getText().length()<1 || binding.edtEmpPhone.getText().length()<1||
            binding.edtAddress.getText().length()<1||binding.edtSalary.getText().length()<1|| binding.tvBirth.getText().length()<1||
            binding.edtAddressDetail.getText().length()<1) {
                Toast.makeText(activity, "빈칸을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }


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
            vo.setAddress(binding.edtAddress.getText().toString()+" / "+binding.edtAddressDetail.getText().toString());
            vo.setSalary(Integer.parseInt(binding.edtSalary.getText().toString()));

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
        } else if (v.getId() == R.id.edt_address){
            //주소검색 웹 뷰를 띄움
            Intent i = new Intent(getContext(), WebViewActivity.class);
            startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        binding.edtAddress.setText(data);
                    }
                }
                break;
        }
    }
    //키보드 숨겨주는 메소드
    private void hideKeyboard()    {
        if (getActivity() != null && getActivity().getCurrentFocus() != null)        {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}