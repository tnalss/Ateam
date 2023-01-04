package com.example.lastproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.ea.EaCodeVO;
import com.example.lastproject.ea.FormListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {
        String TAG = "로그";
        RecyclerView recv_notice, recv_secret;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.fragment_notice, container, false);


        recv_notice = v.findViewById(R.id.recv_notice);
        recv_secret = v.findViewById(R.id.recv_secret);
        ApiClient.setBASEURL("http://192.168.0.28/middle/");


        new CommonMethod().setParams("id","한울").sendPost("notice.no", (isResult, data) -> {

        });


        return v;

    }
}