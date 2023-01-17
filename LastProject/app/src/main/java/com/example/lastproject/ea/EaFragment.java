package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EaFragment extends Fragment implements View.OnClickListener {
    RecyclerView recv_recent_ea;
    TabLayout tab_layout;
    CardView cardv_write,cardv_draft,cardv_sign,cardv_return, cardv_retry, cardv_vacation, cardv_overtime, cardv_business;
    MainActivity activity;
    TextView tv_emp_name;
    ArrayList<EaVO> list;
    EaCodeVO vo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ea, container, false);
        recv_recent_ea = v.findViewById(R.id.recv_recent_ea);
        tab_layout = v.findViewById(R.id.tab_layout);
        cardv_write = v.findViewById(R.id.cardv_write);
        cardv_draft = v.findViewById(R.id.cardv_draft);
        cardv_sign = v.findViewById(R.id.cardv_sign);
        cardv_return = v.findViewById(R.id.cardv_return);
        cardv_retry = v.findViewById(R.id.cardv_retry);
        cardv_vacation = v.findViewById(R.id.cardv_vacation);
        cardv_overtime = v.findViewById(R.id.cardv_overtime);
        cardv_business = v.findViewById(R.id.cardv_business);
        tv_emp_name = v.findViewById(R.id.tv_emp_name);
        tv_emp_name.setText(Common.loginInfo.getEmp_name() +"("+ Common.loginInfo.getRank_name() +")"+"님");
        activity = (MainActivity) getActivity();

        cardv_write.setOnClickListener(this);
        cardv_draft.setOnClickListener(this);
        cardv_sign.setOnClickListener(this);
        cardv_return.setOnClickListener(this);
        cardv_retry.setOnClickListener(this);
        cardv_vacation.setOnClickListener(this);
        cardv_overtime.setOnClickListener(this);
        cardv_business.setOnClickListener(this);

        tab_layout.addTab(tab_layout.newTab().setText("기안함"));
        tab_layout.addTab(tab_layout.newTab().setText("결재함"));
        tab_layout.addTab(tab_layout.newTab().setText("회수함"));
        tab_layout.addTab(tab_layout.newTab().setText("결재완료"));
        tab_layout.addTab(tab_layout.newTab().setText("결재전"));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("please");

        myRef.setValue("What the Fuck");



        new CommonMethod().setParams("no",Common.loginInfo.getEmp_no()).sendPost("recent_all_list.ea", (isResult, data) -> {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                    list = gson.fromJson(data,
                            new TypeToken<ArrayList<EaVO>>() {
                            }.getType());
                    recv_recent_ea.setAdapter(new EaRecentListAdapter(inflater, list, activity));
                    recv_recent_ea.setLayoutManager(CommonMethod.getVManager(getContext()));
                });

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0){
                    new CommonMethod().setParams("no", Common.loginInfo.getEmp_no()).sendPost("recent_all_list.ea", (isResult, data) -> {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                        list = gson.fromJson(data,
                                new TypeToken<ArrayList<EaVO>>(){}.getType());

                        recv_recent_ea.setAdapter(new EaRecentListAdapter(inflater,list,activity));
                        recv_recent_ea.setLayoutManager(CommonMethod.getVManager(getContext()));
                    });
                }else if(position == 1){
                    new CommonMethod().setParams("no", Common.loginInfo.getEmp_no()).sendPost("signboxlist.ea", (isResult, data) -> {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                        list = gson.fromJson(data,
                                new TypeToken<ArrayList<EaVO>>(){}.getType());

                        recv_recent_ea.setAdapter(new EaRecentListAdapter(inflater,list,activity));
                        recv_recent_ea.setLayoutManager(CommonMethod.getVManager(getContext()));
                    });
                }else if(position == 2){
                    new CommonMethod().setParams("no", Common.loginInfo.getEmp_no()).sendPost("retryboxlist.ea", (isResult, data) -> {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                        list = gson.fromJson(data,
                                new TypeToken<ArrayList<EaVO>>(){}.getType());
                     for(int i=0;i<list.size();i++){
                         if(i>0){
                            if(list.get(i).getEa_num().equals(list.get(i-1).getEa_num())){
                                list.remove(i);
                            }
                         }
                     }
                        recv_recent_ea.setAdapter(new EaRecentListAdapter(inflater,list,activity));
                        recv_recent_ea.setLayoutManager(CommonMethod.getVManager(getContext()));
                    });
                }else if(position == 3){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cardv_write){
                activity.changeFragment(new FormListFragment());
        }else if(v.getId() == R.id.cardv_draft){
                activity.changeFragment(new EaDraftBoxFragment());
        }else if(v.getId() == R.id.cardv_sign){
                activity.changeFragment(new EaSignBoxFragment());
        }else if(v.getId() == R.id.cardv_return){

        }else if(v.getId() == R.id.cardv_retry){
            activity.changeFragment(new EaRetryBoxFragment());
        }else if(v.getId() == R.id.cardv_vacation){
            Bundle bundle = new Bundle();
            Fragment f = new WriteEaFragment();
            vo = new EaCodeVO();
            vo.setCode_value("휴가신청서");
            bundle.putSerializable("form", vo);
            f.setArguments(bundle);
            activity.changeFragment(f);
        }else if(v.getId() == R.id.cardv_overtime){
            Bundle bundle = new Bundle();
            Fragment f = new WriteEaFragment();
            vo = new EaCodeVO();
            vo.setCode_value("파견신청서");
            bundle.putSerializable("form", vo);
            f.setArguments(bundle);
            activity.changeFragment(f);
        }else if(v.getId() == R.id.cardv_business){
            Bundle bundle = new Bundle();
            Fragment f = new WriteEaFragment();
            vo = new EaCodeVO();
            vo.setCode_value("외근신청서");
            bundle.putSerializable("form", vo);
            f.setArguments(bundle);
            activity.changeFragment(f);
        }
    }
}