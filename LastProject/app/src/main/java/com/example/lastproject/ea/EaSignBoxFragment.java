package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EaSignBoxFragment extends Fragment {
    RecyclerView recv_sign;
    ArrayList<EaVO> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ea_sign_box, container, false);
        recv_sign = v.findViewById(R.id.recv_sign);
        new CommonMethod().setParams("no", Common.loginInfo.getEmp_no()).sendPost("signboxlist.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaVO>>(){}.getType());

            recv_sign.setAdapter(new EaSignBoxAdapter(inflater,list,getContext()));
            recv_sign.setLayoutManager(CommonMethod.getVManager(getContext()));
        });

        return v;
    }
}