package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EaRetryBoxFragment extends Fragment {
    RecyclerView recv_retry;
    ArrayList<EaVO> list;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ea_retry_box, container, false);
        activity = (MainActivity)getActivity();
        recv_retry = v.findViewById(R.id.recv_retry);

        new CommonMethod().setParams("no", Common.loginInfo.getEmp_no()).sendPost("retryboxlist.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaVO>>(){}.getType());
            for(int i=0;i<list.size();i++){
                if(i>0){
                    if(list.get(i).getEa_num().equals(list.get(i-1).getEa_num())){
                        list.remove(i);
                    }
                }
            }
            recv_retry.setAdapter(new EaRetryBoxAdapter(inflater,activity,list));
            recv_retry.setLayoutManager(CommonMethod.getVManager(getContext()));
        });

        return v;
    }
}