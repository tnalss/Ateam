package com.example.lastproject.Org_Chart;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class Org_MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;
    LinearLayout l1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_main);
        getSupportActionBar().hide();


        l1 = findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });






        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
//        mTabLayout.addTab(mTabLayout.newTab().setText("홈"));

        mViewPager = (ViewPager2) findViewById(R.id.pager_content);

        //프레그먼트 이동 구현
        mContentPagerAdapter = new ContentsPagerAdapter(this);
        mViewPager.setAdapter(mContentPagerAdapter);

        final List<String> tabElement = Arrays.asList("전체 조직도"," 지점","부서","직급");

        //tabLyout와 viewPager 연결
        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(Org_MainActivity.this);
                textView.setText(tabElement.get(position));
                tab.setCustomView(textView);
            }
        }).attach();

    }
}
