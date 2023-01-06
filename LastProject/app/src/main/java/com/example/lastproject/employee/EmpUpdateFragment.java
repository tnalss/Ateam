package com.example.lastproject.employee;

import static android.app.Activity.RESULT_OK;

import static com.example.lastproject.common.Common.SEARCH_ADDRESS_ACTIVITY;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.SimpleCode;
import com.example.lastproject.common.WebViewActivity;
import com.example.lastproject.databinding.FragmentEmpInsertBinding;
import com.example.lastproject.databinding.FragmentEmpUpdateBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EmpUpdateFragment extends Fragment implements View.OnClickListener {
    private MainActivity activity;
    private FragmentEmpUpdateBinding binding;
    private EmployeeVO vo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentEmpUpdateBinding.inflate(inflater,container,false);

        Bundle bundle=getArguments();
        vo = (EmployeeVO) bundle.getSerializable("vo");

        binding.tvEmpNo.setText(vo.getEmp_no());
        binding.edtEmpName.setText(vo.getEmp_name());
        binding.tvBirth.setText(vo.getBirth().substring(0,10));
        if(vo.getGender().equals("남")){
            binding.radioMale.setChecked(true);
        }else{
            binding.radioFemale.setChecked(true);
        }

        binding.edtEmpEmail.setText(vo.getEmail());
        binding.edtEmpPhone.setText(vo.getPhone());

        int div = vo.getAddress().indexOf("/");
        if(div != -1){
            String tempAddress = vo.getAddress().substring(0,div-1);
            binding.edtAddress.setText(tempAddress);
            binding.edtAddressDetail.setText(vo.getAddress().substring(div+2));
        } else{
            binding.edtAddress.setText(vo.getAddress());
        }
        binding.edtSalary.setText(vo.getSalary()+"");

        if(vo.getAdmin().equals("L0")){
            binding.radioEmp.setChecked(true);
        }else{
            binding.radioL1.setChecked(true);
        }

        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });

        binding.btnEmpUpdate.setOnClickListener(v -> {
            //입력판단해야됨 길이로?/////////////////////////////////////////입력유무판단.


            //문자열로변환필요
            vo.setEmp_name(binding.edtEmpName.getText().toString());
            if(! binding.radioMale.isChecked())
                vo.setGender("여");
            else
                vo.setGender("남");
            vo.setEmail(binding.edtEmpEmail.getText().toString());
            vo.setPhone(binding.edtEmpPhone.getText().toString());
            //생일 년/월/일 포맷으로 변경해서 넣어줘야함.
            vo.setBirth( binding.tvBirth.getText().toString());
            vo.setBranch_name(binding.spnBranch.getText().toString());
            vo.setDepartment_name(binding.spnDept.getText().toString());
            vo.setRank_name(binding.spnRank.getText().toString());
            vo.setAddress(binding.edtAddress.getText().toString()+" / "+binding.edtAddressDetail.getText().toString());
            vo.setSalary(Integer.parseInt(binding.edtSalary.getText().toString()));
            if(binding.radioEmp.isChecked()){
                vo.setAdmin("L0");
            } else {
                vo.setAdmin("L1");
            }

            new CommonMethod().setParams("param",vo).sendPostFile("update.emp",null,(isResult, data) -> {
                if(isResult) {
                    EmployeeVO vo2 = new Gson().fromJson(data,EmployeeVO.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("vo",vo2);
                    Fragment fragment = new EmpDetailFragment();
                    fragment.setArguments(bundle2);
                    activity.changeFragment(fragment);
                }
            });

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


        if(vo.getBranch_name()!=null)
            binding.spnBranch.setText(vo.getBranch_name());
        if(vo.getDepartment_name()!=null)
            binding.spnDept.setText(vo.getDepartment_name());
        if(vo.getRank_name()!=null)
            binding.spnRank.setText(vo.getRank_name());

        return binding.getRoot();
    }

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

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvBirth.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
            vo.setBirth(year+"/"+monthOfYear+"/"+dayOfMonth);
        }
    };
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

}