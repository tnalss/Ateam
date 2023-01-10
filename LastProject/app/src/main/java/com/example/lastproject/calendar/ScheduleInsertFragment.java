package com.example.lastproject.calendar;

import static android.app.Activity.RESULT_OK;
import static com.example.lastproject.common.Common.SEARCH_ADDRESS_ACTIVITY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentScheduleInsertBinding;
import com.google.gson.Gson;

import java.util.Calendar;

public class ScheduleInsertFragment extends Fragment implements View.OnClickListener {

    FragmentScheduleInsertBinding binding;
    private MainActivity activity;
    private ScheduleVO vo;
    private String cate;
    private Calendar cal;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleInsertBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        cate = bundle.getString("cate");
        activity = (MainActivity) getActivity();
        cal= Calendar.getInstance();

        binding.tvFragmentName.setText(cate+" 일정 추가");
        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });

        binding.btnScheInsert.setOnClickListener(this);

        vo = new ScheduleVO();

        binding.scheForm.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        binding.tvScheStart.setOnClickListener(this);
        binding.tvScheEnd.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_sche_insert ){
            if(binding.edtScheTitle.getText().toString().length()==0 || binding.edtScheContent.getText().toString().length()==0 || binding.tvScheStart.getText().toString().indexOf('년')==-1 || binding.tvScheEnd.getText().toString().indexOf('년')==-1) {
                Toast.makeText(activity, "모든 값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }


//입력확인요
            vo.setEmp_no(Common.loginInfo.getEmp_no());
            vo.setDepartment_name(Common.loginInfo.getDepartment_name());
            vo.setSche_title(binding.edtScheTitle.getText().toString());
            vo.setSche_content(binding.edtScheContent.getText().toString());

            vo.setSche_status("L1");


            if(cate.equals("부서")) {
                new CommonMethod().setParams("vo", new Gson().toJson(vo).toString()).sendPost("dept_insert.sche", (isResult, data) -> {
                    if (isResult) {
                        activity.getSupportFragmentManager().popBackStack();

                    }

                });
            } else if ( cate.equals("회사")){
                new CommonMethod().setParams("vo", new Gson().toJson(vo).toString()).sendPost("company_insert.sche", (isResult, data) -> {
                    if (isResult) {
                        activity.getSupportFragmentManager().popBackStack();

                    }

                });
            } else if (cate.equals("개인")){
                new CommonMethod().setParams("vo", new Gson().toJson(vo).toString()).sendPost("personal_insert.sche", (isResult, data) -> {
                    if (isResult) {
                        activity.getSupportFragmentManager().popBackStack();

                    }

                });
            }


        } else if (v.getId() == R.id.tv_sche_start){
            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                     listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            dialog.show();
        } else if (v.getId() == R.id. tv_sche_end){
            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                    elistener,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            dialog.show();
        }
    }



    //키보드 숨겨주는 메소드
    private void hideKeyboard()    {
        if (getActivity() != null && getActivity().getCurrentFocus() != null)        {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvScheStart.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일");
            vo.setSche_start(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

        }
    };
    private DatePickerDialog.OnDateSetListener elistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvScheEnd.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일");
            vo.setSche_end(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

        }
    };
}