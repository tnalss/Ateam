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
import com.example.lastproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class FormListFragment extends Fragment {
    RecyclerView recv_form;
    ArrayList<EaCodeVO> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form_list, container, false);
        recv_form = v.findViewById(R.id.recv_form);
        ApiClient.setBASEURL("http://192.168.0.2/middle/");

        new CommonMethod().sendPost("form.ea", (isResult, data) -> {
            list = new Gson().fromJson(data,
                    new TypeToken<ArrayList<EaCodeVO>>(){}.getType());

            recv_form.setAdapter(new FormListAdapter(inflater,list,getContext()));
            recv_form.setLayoutManager(CommonMethod.getVManager(getContext()));
        });


        return v;
    }
}