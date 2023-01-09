package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EaDraftBoxFragment extends Fragment {
    RecyclerView recv_draft;
    ArrayList<EaVO> list;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ea_draft_box, container, false);
        recv_draft = v.findViewById(R.id.recv_draft);
        activity = (MainActivity)getActivity();
        new CommonMethod().setParams("no", Common.loginInfo.getEmp_no()).sendPost("recent_all_list.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaVO>>(){}.getType());

            recv_draft.setAdapter(new EaDraftAdapter(inflater,list,activity));
            recv_draft.setLayoutManager(CommonMethod.getVManager(getContext()));
        });


        return v;
    }
}