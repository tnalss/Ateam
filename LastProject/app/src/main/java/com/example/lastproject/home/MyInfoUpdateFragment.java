package com.example.lastproject.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.databinding.FragmentMyInfoBinding;

public class MyInfoUpdateFragment extends Fragment {
    FragmentMyInfoBinding binding;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyInfoBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();




        binding.ivBack.setOnClickListener(v -> {
            activity.getSupportFragmentManager().popBackStack();
        });




        return binding.getRoot();
    }
}