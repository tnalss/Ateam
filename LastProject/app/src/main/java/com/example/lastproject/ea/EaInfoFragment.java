package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.google.android.material.card.MaterialCardView;


public class EaInfoFragment extends Fragment implements View.OnClickListener {
    EaVO vo;
    MaterialCardView cardv_back;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ea_info, container, false);
        activity = (MainActivity) getActivity();
        cardv_back = v.findViewById(R.id.cardv_back);
        cardv_back.setOnClickListener(this);
        vo = (EaVO) getArguments().getSerializable("form");

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cardv_back){
            activity.onBackPressed();
        }
    }
}