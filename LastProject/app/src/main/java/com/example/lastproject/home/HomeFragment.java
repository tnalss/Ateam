package com.example.lastproject.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.Org_Chart.Org_MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.attend.Attend_adminFragment;
import com.example.lastproject.code.CodeFragment;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentHomeBinding;
import com.example.lastproject.employee.ManageEmpFragment;
import com.example.lastproject.login.LoginVO;
import com.example.lastproject.login.LogoutActivity;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import com.example.lastproject.notice.NoticeFragment;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private MainActivity activity;
    private int cnt=0,flag=0;
    private int unreadDocs=0;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        Timer timer;

        activity = (MainActivity) getActivity();

        binding.menu11.setClipToOutline(true);
        binding.menu12.setClipToOutline(true);
        binding.menu13.setClipToOutline(true);
        binding.menu21.setClipToOutline(true);
        binding.menu22.setClipToOutline(true);
        binding.menu23.setClipToOutline(true);
        binding.menu31.setClipToOutline(true);
        binding.menu32.setClipToOutline(true);
        binding.menu33.setClipToOutline(true);
        //visibility ????????? ???????????? ??????????????? ?????? ?????? ??? ?????? ????????????..

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner1);
        images.add(R.drawable.banner2);
        images.add(R.drawable.banner3);
        //????????????????????? ???????????? ????????? ?????? ??????????????????!
        binding.vpSlider.setClipToOutline(true); // ??????????????? ?????????
        binding.vpSlider.setOffscreenPageLimit(1);
        binding.vpSlider.setAdapter(new SlideBannerAdapter(inflater,getContext(),images));
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(cnt == images.size()) {
                    cnt = 0;
                }
                binding.vpSlider.setCurrentItem(cnt++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 300, 2500);

        binding.vpSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tvSliderIndicator.setText(position+1+ "/"+images.size());
            }
        });


        binding.tvEmpName.setText(Common.loginInfo.getEmp_name());
        binding.tvBurgerName.setText(Common.loginInfo.getEmp_name());

        binding.tvEmpDepRank.setText(Common.loginInfo.getDepartment_name()+" / "+Common.loginInfo.getRank_name());
        if(Common.loginInfo.getProfile_path()!=null){
            Glide.with(this).load(Common.loginInfo.getProfile_path()).error(R.drawable.error_user_profile).into(binding.ivEmpProfile);
            Glide.with(this).load(Common.loginInfo.getProfile_path()).error(R.drawable.error_user_profile).into(binding.cvBurgerProfile);
        }
        // ?????? ????????? ????????? ?????? ??????
        // ???????????? ?????????????????? ???????????????? ?????????????
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("attendOrNot",(isResult, data) -> {
            LoginVO vo = new Gson().fromJson(data,LoginVO.class);
            if(isResult) {
                if (vo != null) {
                    //if(vo.getAtt_code().equals("W0")) {
                        Common.loginInfo.setAtt_code(vo.getAtt_code());
                        Common.loginInfo.setAttend_date(vo.getAttend_date());
                        Common.loginInfo.setAttend_on(vo.getAttend_on());
                        if(Common.loginInfo.getAtt_code()!=null){
                        binding.tvOntime.setText("?????? : "+Common.loginInfo.getAttend_on()+" ");
                        binding.ivOnWork.setVisibility(View.VISIBLE);
                        if(vo.getAtt_code().equals("W1")) {
                            Common.loginInfo.setAttend_off(vo.getAttend_off());
                            binding.tvOntime.setText("?????? : " + Common.loginInfo.getAttend_off() + " ");
                        }
                        }
                    //}
                } else {
                    binding.tvOntime.setText("");
                }
            }
        });


        // ??????????????? ????????? ????????? ?????? ????????????
        new CommonMethod().setParams("emp_no",Common.loginInfo.getEmp_no()).sendPost("howManyDocs.cm",(isResult, data) -> {
            if(isResult){
                if(!data.equals("0")){
                    binding.ivRedDot.setVisibility(View.VISIBLE);
                    unreadDocs=Integer.parseInt(data);
                    binding.tvUnreadDocs.setText("????????? ?????? : "+unreadDocs+" ???");
                    binding.ivNoti.setOnClickListener(this);

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
                .addEvents(new CalendarEventsPredicate() {
                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        //Circle Indicator
                        // test the date and return a list of CalendarEvent to assosiate with this Date.
                        //???????????? list??? ?????????????????????????
                        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        //Log.d("TAG", "events: "+sdf.format(date.getTime()).toString());
                        //ArrayList<CalendarEvent> test = new ArrayList<>();
                        //test.add(new CalendarEvent(123,"??????"));
                        //???????????????List <devs.mulham.horizontalcalendar.model.CalendarEvent
                        return null;
                    }
                })
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
               // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                //Log.d("TAG", "events: "+ sdf.format(date.getTime()) +position);

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


        binding.ivBurger.setOnClickListener(this);
        binding.menu11.setOnClickListener(this);
        binding.menu12.setOnClickListener(this);
        binding.menu13.setOnClickListener(this);
        binding.menu21.setOnClickListener(this);
        binding.menu22.setOnClickListener(this);
        binding.menu23.setOnClickListener(this);
        binding.menu31.setOnClickListener(this);
        binding.menu32.setOnClickListener(this);
        binding.menu33.setOnClickListener(this);
        
        binding.ivEmpDetail.setOnClickListener(this);
        binding.flOnOff.setOnClickListener(this);
        //????????????
        binding.llMyInfo.setOnClickListener(this);
        binding.llLogout.setOnClickListener(this);

        binding.tvUnreadDocs.setOnClickListener(this);
        View v = binding.getRoot();
        return v;
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu1_1){
            //????????????
            activity.changeFragment(new ManageEmpFragment());
        } else if ( v.getId() == R.id.menu1_2){
            //????????????
            activity.changeFragment(new CodeFragment());
        } else if ( v.getId() == R.id.menu1_3){
            //?????????
           activity.changeFragment(new NoticeFragment());
        } else if ( v.getId() == R.id.menu2_1){
            //???????????? ???????????? --23.01.16 ?????? ??????
            activity.changeFragment(new Attend_adminFragment());
        } else if ( v.getId() == R.id.menu2_2){
            // ???????????? ???????????? ????????????????????? ???????????? ?????????
            activity.btm_nav.setSelectedItemId(R.id.btm_item2);
        } else if ( v.getId() == R.id.menu2_3){
            // ?????????????????? ???????????? ????????????????????? ???????????? ?????????
            activity.btm_nav.setSelectedItemId(R.id.btm_item4);
        } else if (v.getId() == R.id.iv_emp_detail || v.getId() == R.id.ll_my_info){
            //????????? ?????? > ????????????
            activity.changeFragment(new MyInfoFragment());
        } else if(v.getId() == R.id.fl_on_off){
            //????????? x??? v?????????????????? ????????? ????????????
            activity.btm_nav.setSelectedItemId(R.id.btm_item3);
        } else if(v.getId() == R.id.menu3_2){
            //????????? ???????????? ????????? ????????????
           Intent orgIntent = new Intent(activity, Org_MainActivity.class);
           startActivity(orgIntent);
        }else if(v.getId() == R.id.menu3_3){
            //????????? ????????????
            activity.btm_nav.setSelectedItemId(R.id.btm_item3);
        }  else if(v.getId() == R.id.iv_burger){
            binding.draw.openDrawer(Gravity.RIGHT);
        } else if(v.getId() == R.id.ll_logout){
            //???????????? ????????????
            Intent intent2 = new Intent(activity, LogoutActivity.class);
            startActivity(intent2);
            activity.finish();
        } else if(v.getId() == R.id.iv_noti){
            if(flag==0) {
                binding.tvUnreadDocs.setVisibility(View.VISIBLE);
                flag=1;
            } else{
                binding.tvUnreadDocs.setVisibility(View.GONE);
                flag=0;
            }
        } else if ( v.getId() == R.id.tv_unreadDocs){
            activity.btm_nav.setSelectedItemId(R.id.btm_item4);
        }
    }


}