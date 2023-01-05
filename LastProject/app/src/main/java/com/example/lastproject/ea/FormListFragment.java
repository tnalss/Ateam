package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;



public class FormListFragment extends Fragment {
    RecyclerView recv_form;
    ArrayList<EaCodeVO> list;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form_list, container, false);
        recv_form = v.findViewById(R.id.recv_form);
        activity = (MainActivity)getActivity();
        
        ApiClient.setBASEURL("http://192.168.0.23:3303/middle/");

        new CommonMethod().sendPost("form.ea", (isResult, data) -> {

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaCodeVO>>(){}.getType());

            recv_form.setAdapter(new FormListAdapter(inflater,list,activity));
            recv_form.setLayoutManager(CommonMethod.getVManager(getContext()));
        });


        return v;
    }
}