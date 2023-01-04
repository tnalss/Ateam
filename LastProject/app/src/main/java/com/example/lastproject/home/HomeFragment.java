package com.example.lastproject.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentHomeBinding;
import com.example.lastproject.employee.ManageEmpFragment;
import com.example.lastproject.login.LoginVO;
import com.google.gson.Gson;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;

    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        binding.menu11.setClipToOutline(true);
        binding.menu12.setClipToOutline(true);
        binding.menu13.setClipToOutline(true);
        binding.menu21.setClipToOutline(true);
        binding.menu22.setClipToOutline(true);
        binding.menu23.setClipToOutline(true);
        //visibility 속성을 이용해서 일반회원의 경우 메뉴 몇 개를 숨겨야함..

        binding.tvEmpName.setText(Common.loginInfo.getEmp_name());
        binding.tvEmpDepRank.setText(Common.loginInfo.getDepartment_name()+" / "+Common.loginInfo.getRank_name());
        //쿼리 날려서 출퇴근 여부 파악
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attendOrNot",(isResult, data) -> {
            LoginVO vo = new Gson().fromJson(data,LoginVO.class);
            if(isResult) {
                if (vo != null) {
                    if(vo.getAtt_code().equals("W0")) {
                        Common.loginInfo.setAtt_code(vo.getAtt_code());
                        Common.loginInfo.setAttend_date(vo.getAttend_date());
                        Common.loginInfo.setAttend_on(vo.getAttend_on());
                        binding.tvOntime.setText("출근 시각 : "+Common.loginInfo.getAttend_on()+" ");
                        binding.ivOnWork.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.tvOntime.setText("");
                }
            }
        });

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(binding.getRoot(), R.id.calendarView).range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }
            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });


        binding.menu11.setOnClickListener(this);
        binding.menu12.setOnClickListener(this);
        binding.menu13.setOnClickListener(this);
        binding.menu21.setOnClickListener(this);
        binding.menu22.setOnClickListener(this);
        binding.menu23.setOnClickListener(this);
        binding.ivEmpDetail.setOnClickListener(this);
        binding.flOnOff.setOnClickListener(this);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu1_1){
            //사원관리
            activity.changeFragment(new ManageEmpFragment());
        } else if ( v.getId() == R.id.menu1_2){
            //문서관리
        } else if ( v.getId() == R.id.menu1_3){
            //게시판
        } else if ( v.getId() == R.id.menu2_1){
            //지점관리
        } else if ( v.getId() == R.id.menu2_2){
            // 일정관리 눌렀을때 바텀네비게이션 찾아가서 눌러줌
            activity.btm_nav.setSelectedItemId(R.id.btm_item2);
        } else if ( v.getId() == R.id.menu2_3){
            // 전자결재메뉴 눌렀을때 바텀네비게이션 찾아가서 눌러줌
            activity.btm_nav.setSelectedItemId(R.id.btm_item4);
        } else if (v.getId() == R.id.iv_emp_detail){
            //사원명 옆에 > 눌렀을때

        } else if(v.getId() == R.id.fl_on_off){
            //상단에 x나 v버튼눌렀을때 출퇴근 화면으로
            activity.btm_nav.setSelectedItemId(R.id.btm_item3);
        }
    }
}