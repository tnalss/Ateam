package com.example.lastproject.Org_Chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.SimpleCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;


public class Org_branch_Fragment extends Fragment {
    RecyclerView recyclerview;
    ArrayList<OrgVO> list;
    MainActivity activity;
    EditText text_search;
    NiceSpinner spinner;
    ArrayList<SimpleCode> branch_list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_org_branch_, container, false);
        recyclerview =v.findViewById(R.id.recv_org_branch);
        text_search = v.findViewById(R.id.text_search);
        spinner = v.findViewById(R.id.spinner);

        new CommonMethod().setParams("top_code","B").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                branch_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> b = new ArrayList<>();
                for (int i = 0 ; i < branch_list.size() ; i++){
                    b.add(branch_list.get(i).getCode_value());
                }
                spinner.attachDataSource(b);

            }
        });

        spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int i, long id) {
                new CommonMethod().setParams("code",branch_list.get(i).getCode_value()).sendPost("org_branch.org",(isResult, data) -> {
                    list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>() {
                    }.getType());
                    recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(), list, activity));
                    recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                });
            }
        });
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (text_search.length() >0){
                    new CommonMethod().setParams("code",spinner.getText().toString().trim()).setParams("keyword",text_search.getText().toString()).sendPost("org_branch_n.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }
}