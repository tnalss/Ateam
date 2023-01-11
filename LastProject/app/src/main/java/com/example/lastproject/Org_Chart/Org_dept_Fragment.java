package com.example.lastproject.Org_Chart;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class Org_dept_Fragment extends Fragment {

    RecyclerView recyclerview;
    ArrayList<OrgVO> list;

    MainActivity activity;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_org_dept_, container, false);


        recyclerview =v.findViewById(R.id.recv_org_dept);

        /*리사이클러뷰 - 조직도 목록 보여주기*/
        new CommonMethod().sendPost("org_dept.org",(isResult, data) -> {
            list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
            recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
            recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
        });

        return v;
    }
}