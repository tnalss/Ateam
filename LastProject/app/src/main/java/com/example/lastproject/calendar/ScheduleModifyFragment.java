package com.example.lastproject.calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentScheduleModifyBinding;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScheduleModifyFragment extends Fragment implements View.OnClickListener {

    FragmentScheduleModifyBinding binding;
    private ScheduleVO vo;
    private Calendar cal;
    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleModifyBinding.inflate(inflater,container,false);
        cal= Calendar.getInstance();
        Bundle bundle = getArguments();
        activity= (MainActivity) getActivity();
        vo = (ScheduleVO) bundle.getSerializable("vo");
        binding.edtScheTitle.setText(vo.getSche_title());
        binding.edtScheContent.setText(vo.getSche_title());

        binding.tvScheStart.setText(vo.getSche_start().substring(0,11));
        binding.tvScheEnd.setText(vo.getSche_end().substring(0,11));

        binding.tvScheStart.setOnClickListener(this);
        binding.tvScheEnd.setOnClickListener(this);
        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });

        binding.btnScheModify.setOnClickListener(this);


        return binding.getRoot();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvScheStart.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            vo.setSche_start(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

        }
    };
    private DatePickerDialog.OnDateSetListener elistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvScheEnd.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            vo.setSche_end(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_sche_start){
            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                    listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            dialog.show();
        } else if (v.getId() == R.id. tv_sche_end){
            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                    elistener,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            dialog.show();
        } else if (v.getId() == R.id.btn_sche_modify){

            //vo.setSche_title(binding.edtScheTitle.getText().toString());
            //vo.setSche_content(binding.edtScheContent.getText().toString());

            new CommonMethod().setParams("param",new Gson().toJson(vo)).sendPost("modify.sche",(isResult, data) -> {
                if(isResult){
                    activity.getSupportFragmentManager().popBackStack();
                }

            });

        }
    }
}