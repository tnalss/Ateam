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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class Org_branch_Fragment extends Fragment {
    RecyclerView recyclerview;
    ArrayList<OrgVO> list;
    MainActivity activity;
    EditText text_search;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    Spinner spinner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_org_branch_, container, false);
        recyclerview =v.findViewById(R.id.recv_org_branch);
        text_search = v.findViewById(R.id.text_search);

        arrayList = new ArrayList<>();
        arrayList.add("지점 전체");
        arrayList.add("광주");
        arrayList.add("대구");
        arrayList.add("대전");
        arrayList.add("부산");
        arrayList.add("서울");
        arrayList.add("지점 미지정");


        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
        spinner = v.findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i==0){
                    new CommonMethod().sendPost("org_branch.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                    });
                    text_search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (text_search.length() >=0){
                                new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_n.org",(isResult, data) -> {
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

                }else if (i ==1){
                    new CommonMethod().sendPost("org_branch_1.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                        text_search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (text_search.length() >=0){
                                    new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_1n.org",(isResult, data) -> {
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
                    });
                }else if (i == 2){
                    new CommonMethod().sendPost("org_branch_2.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                        text_search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (text_search.length() >=0){
                                    new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_2n.org",(isResult, data) -> {
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
                    });
                }else if (i ==3){
                    new CommonMethod().sendPost("org_branch_3.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                        text_search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (text_search.length() >=0){
                                    new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_3n.org",(isResult, data) -> {
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
                    });
                }else if (i ==4){
                    new CommonMethod().sendPost("org_branch_4.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                        text_search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (text_search.length() >=0){
                                    new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_4n.org",(isResult, data) -> {
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
                    });
                }else if (i == 5){
                    new CommonMethod().sendPost("org_branch_5.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                        text_search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (text_search.length() >=0){
                                    new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_5n.org",(isResult, data) -> {
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
                    });

                }else if ( i == 6){
                    new CommonMethod().sendPost("org_branch_6.org",(isResult, data) -> {
                        list = new Gson().fromJson(data, new TypeToken<ArrayList<OrgVO>>(){}.getType());
                        recyclerview.setAdapter(new Org_all_adapter(getLayoutInflater(),list,activity));
                        recyclerview.setLayoutManager(CommonMethod.getVManager(getContext()));
                        text_search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (text_search.length() >=0){
                                    new CommonMethod().setParams("keyword",text_search.getText().toString()).sendPost("org_branch_6n.org",(isResult, data) -> {
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
                    });
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return v;
    }
}