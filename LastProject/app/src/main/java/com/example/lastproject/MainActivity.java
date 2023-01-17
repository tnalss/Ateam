package com.example.lastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.lastproject.calendar.CalendarFragment;
import com.example.lastproject.common.Common;
import com.example.lastproject.attend.AttendFragment;
import com.example.lastproject.databinding.ActivityMainBinding;
import com.example.lastproject.ea.EaFragment;
import com.example.lastproject.home.HomeFragment;
import com.example.lastproject.login.LoginVO;
import com.example.lastproject.login.LoginActivity;
import com.example.lastproject.login.LogoutActivity;
import com.example.lastproject.notice.NoticeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
        double waitTime=0;
        public BottomNavigationView btm_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        btm_nav = findViewById(R.id.btm_nav);
        Intent intent = getIntent();
        Common.loginInfo = (LoginVO) intent.getSerializableExtra("loginInfo");

        changeFragment(new HomeFragment());
        binding.btmNav.setOnItemSelectedListener(item -> {
            if ( item.getItemId()== R.id.btm_item1 ) {
                //홈
                changeFragment(new HomeFragment());
            }   else if ( item.getItemId() == R.id.btm_item2 ){
                //일정
                changeFragment(new CalendarFragment());
            }   else if ( item.getItemId() == R.id.btm_item3 ){
                changeFragment(new AttendFragment());
                //출퇴근관리
            }   else if ( item.getItemId() == R.id.btm_item4 ){
                changeFragment(new EaFragment());
                //전자결재
            } else if( item.getItemId() == R.id.btm_item5){
                //게시판
                changeFragment(new NoticeFragment());
            }
            return true;
        });
        /*
        ApiClient.setBASEURL("http://localhost/middle/");
        로그인에서 해주겠지? */

    }
    public void changeFragment(Fragment fragment){
        //addToBackStack(null)을 이용하면 뒤로가기 누르면 이전 프래그먼트로 이동합니다! 23/1/4 csm
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
    }

//    @Override
//    public void onBackPressed() {
//        if(System.currentTimeMillis() - waitTime >=1500 ) {
//            waitTime = System.currentTimeMillis();
//        } else {
//            super.onBackPressed(); // 액티비티 종료
//        }
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

}