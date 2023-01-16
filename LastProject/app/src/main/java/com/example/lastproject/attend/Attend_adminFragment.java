package com.example.lastproject.attend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lastproject.R;


public class Attend_adminFragment extends Fragment {
    TextView view_all;
    LayoutInflater inflater;
    RecyclerView recv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attend_admin, container, false);

        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            inflater.inflate(R.layout.fragment_attend_detail,container,false);


            }
        });

        return v ;


    }
}