package com.example.lastproject.calendar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentScheduleInfoBinding;

public class ScheduleInfoFragment extends Fragment implements View.OnClickListener {

    FragmentScheduleInfoBinding binding;
    private ScheduleVO vo;
    @Override
    public void onResume() {
        super.onResume();
//        if(vo.getSche_status().equals("L0")){
//            //완료된놈임.
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleInfoBinding.inflate(inflater,container,false);
        Bundle bundle = getArguments();
        vo = (ScheduleVO) bundle.getSerializable("vo");
        String emp_name = bundle.getString("emp_name");

        binding.tvEmpName.setText(emp_name);
        binding.tvScheTitle.setText(vo.getSche_title());
        binding.tvScheContent.setText(vo.getSche_content());
        binding.tvScheRed.setText(vo.getSche_red());
        binding.tvScheStart.setText(vo.getSche_start().substring(0,11));
        binding.tvScheEnd.setText(vo.getSche_end().substring(0,11));

        binding.btnDone.setOnClickListener(this);
        binding.btnModify.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_done){



        } else if ( v.getId() == R.id.btn_delete){

        } else if ( v.getId() == R.id.btn_modify){

        }
    }
}