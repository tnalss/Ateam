package com.example.lastproject.ea;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.ApiClient;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.google.android.material.tabs.TabLayout;


public class EaFragment extends Fragment {
    TabLayout tab_layout;
    CardView cardv_write;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ea, container, false);
        tab_layout = v.findViewById(R.id.tab_layout);
        cardv_write = v.findViewById(R.id.cardv_write);
        activity = (MainActivity) getActivity();
        int con = R.id.container;
        cardv_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new FormListFragment());
            }
        });

        tab_layout.addTab(tab_layout.newTab().setText("전체"));
        tab_layout.addTab(tab_layout.newTab().setText("결재완료"));
        tab_layout.addTab(tab_layout.newTab().setText("결재전"));
        tab_layout.addTab(tab_layout.newTab().setText("휴가"));
        tab_layout.addTab(tab_layout.newTab().setText("연장근무"));
        return v;
    }
}