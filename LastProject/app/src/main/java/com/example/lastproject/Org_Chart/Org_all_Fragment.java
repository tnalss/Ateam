package com.example.lastproject.Org_Chart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.employee.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;


public class Org_all_Fragment extends Fragment {
    RecyclerView recyclerview;
    ArrayList<OrgVO> list;
    ImageView search;
    EditText text_search;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_org_all_, container, false);

        search = v.findViewById(R.id.search);
        recyclerview =v.findViewById(R.id.recv_org_all);


        text_search = v.findViewById(R.id.text_search);
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (text_search.length() >=0){
                new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_all_r.org",(isResult, data) -> {
                    list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                    recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list, (Org_MainActivity) getActivity()));
                    recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                });
            }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        /*리사이클러뷰 - 조직도 목록 보여주기*/
        new CommonMethod().sendPost("org_all.org",(isResult, data) -> {
        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list, (Org_MainActivity) getActivity()));
        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
        });




        return v;

    }
}