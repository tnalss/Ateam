package com.example.lastproject.calendar;

import android.app.AlertDialog;
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
import com.example.lastproject.databinding.FragmentScheduleInfoBinding;

public class ScheduleInfoFragment extends Fragment implements View.OnClickListener {

    FragmentScheduleInfoBinding binding;
    private ScheduleVO vo;
    private MainActivity activity;
    private Bundle bundle;
    @Override
    public void onResume() {
        super.onResume();
//        if(vo.getSche_status().equals("L0")){
//            //완료나 완료아닐때 표시해주기...
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleInfoBinding.inflate(inflater,container,false);
        bundle = getArguments();
        vo = (ScheduleVO) bundle.getSerializable("vo");
        String emp_name = bundle.getString("emp_name");

        activity = (MainActivity) getActivity();
        binding.tvEmpName.setText(emp_name);
        binding.tvScheTitle.setText(vo.getSche_title());
        binding.tvScheContent.setText(vo.getSche_content());
        binding.tvScheRed.setText(vo.getSche_red());
        binding.tvScheStart.setText(vo.getSche_start());
        binding.tvScheEnd.setText(vo.getSche_end());

        if(vo.getSche_start().length()>12)
            binding.tvScheStart.setText(vo.getSche_start().substring(0,11));
        if(vo.getSche_end().length()>12)
            binding.tvScheEnd.setText(vo.getSche_end().substring(0,11));

        binding.ivBack.setOnClickListener(this);
        binding.btnDone.setOnClickListener(this);
        binding.btnModify.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);

        if(! Common.loginInfo.getEmp_no().equals(vo.getEmp_no())){
            binding.btnDone.setVisibility(View.GONE);
            binding.btnModify.setVisibility(View.GONE);
            binding.btnDelete.setVisibility(View.GONE);
        }
        if(Common.loginInfo.getAdmin().equals("L1")){
            binding.btnDone.setVisibility(View.VISIBLE);
            binding.btnModify.setVisibility(View.VISIBLE);
            binding.btnDelete.setVisibility(View.VISIBLE);
        }


        if(vo.getSche_status().equals("L1")){

            binding.btnDone.setText("완료 처리");
        } else {

            binding.btnDone.setText("진행 처리");
        }



        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_done){
            if(vo.getSche_status().equals("L0")){
                new CommonMethod().setParams("sche_no",vo.getSche_no()).setParams("status","L1").sendPost("statusDone.sche",(isResult, data) -> {
                    if(isResult){
                    vo.setSche_status("L1");
                        Toast.makeText(getContext(), "진행중으로 처리되었습니다.", Toast.LENGTH_SHORT).show();
                        binding.btnDone.setText("완료 처리");
                    }
                });
            } else {
                new CommonMethod().setParams("sche_no",vo.getSche_no()).setParams("status","L0").sendPost("statusDone.sche",(isResult, data) -> {
                    vo.setSche_status("L0");
                        Toast.makeText(getContext(), "완료 처리되었습니다.", Toast.LENGTH_SHORT).show();
                    binding.btnDone.setText("진행 처리");
                });
        }


        } else if ( v.getId() == R.id.btn_delete){

            new AlertDialog.Builder(getContext()).setTitle("확인").setMessage("정말로 삭제하시겠습니까?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        new CommonMethod().setParams("sche_no",vo.getSche_no()).sendPost("delete.sche",(isResult, data) -> {
                            getActivity().getSupportFragmentManager().popBackStack();
                        });
                    }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                    }).show();
        } else if ( v.getId() == R.id.btn_modify){
            Fragment fragment = new ScheduleModifyFragment();
            fragment.setArguments(bundle);
            activity.changeFragment(fragment);

        } else if ( v.getId() == R.id.iv_back){
            getActivity().onBackPressed();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}