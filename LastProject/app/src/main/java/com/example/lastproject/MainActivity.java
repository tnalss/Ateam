package com.example.lastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.lastproject.common.Common;
import com.example.lastproject.attend.AttendFragment;
import com.example.lastproject.databinding.ActivityMainBinding;
import com.example.lastproject.ea.EaFragment;
import com.example.lastproject.home.HomeFragment;
import com.example.lastproject.login.LoginVO;
import com.example.lastproject.login.LoginActivity;
import com.example.lastproject.login.LogoutActivity;


public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
        double waitTime=0;
        LoginVO vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Common.loginInfo=  (LoginVO) intent.getSerializableExtra("loginInfo");

        changeFragment(new HomeFragment());
        binding.btmNav.setOnItemSelectedListener(item -> {
            if ( item.getItemId()== R.id.btm_item1 ) {
                //홈
                changeFragment(new HomeFragment());
            }   else if ( item.getItemId() == R.id.btm_item2 ){
                //일정
            }   else if ( item.getItemId() == R.id.btm_item3 ){
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new AttendFragment()).commit();
            }   else if ( item.getItemId() == R.id.btm_item4 ){
                changeFragment(new EaFragment());
                //전자결재
            }   else if(item.getItemId() == R.id.btm_logout )   {
                //임시 로그아웃
                Intent intent2 = new Intent(MainActivity.this, LogoutActivity.class);
                startActivity(intent2);
            }
            return true;
        });
        /*
        ApiClient.setBASEURL("http://localhost/middle/");
        로그인에서 해주겠지? */

    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - waitTime >=1500 ) {
            waitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed(); // 액티비티 종료
        }
    }

}