package com.example.lastproject.Org_Chart;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.SimpleCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;


public class Org_rank_Fragment extends Fragment {

    RecyclerView recyclerview;
    ArrayList<OrgVO> list;
    MainActivity activity;
    EditText text_search;
    NiceSpinner spinner;
    ArrayList<SimpleCode> rank_list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_org_rank_, container, false);
        recyclerview =v.findViewById(R.id.recv_org_rank);
        text_search = v.findViewById(R.id.text_search);
        spinner = v.findViewById(R.id.spinner);

        new CommonMethod().setParams("top_code","R").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                rank_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> r = new ArrayList<>();
                for (int i = 0 ; i < rank_list.size() ; i++){
                    r.add(rank_list.get(i).getCode_value());
                }
                spinner.attachDataSource(r);

            }
        });

        spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int i, long id) {
                new CommonMethod().setParams("code",rank_list.get(i).getCode_value()).sendPost("org_rank.org",(isResult, data) -> {
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
                    new CommonMethod().setParams("code",spinner.getText().toString().trim()).setParams("keyword",text_search.getText().toString()).sendPost("org_rank_n.org",(isResult, data) -> {
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