package com.example.lastproject.Org_Chart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.lastproject.R;
import com.google.android.material.tabs.TabLayout;

public class Org_MainActivity extends AppCompatActivity {
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_main);
        tabLayout = findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("부서별 조직도"));
        tabLayout.addTab(tabLayout.newTab().setText("지점별 조직도"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    /*부서별 조직도*/
                }else if(position ==1){
                    /*지점별 조직도*/

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }
}