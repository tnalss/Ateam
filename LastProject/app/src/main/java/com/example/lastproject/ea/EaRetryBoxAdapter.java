package com.example.lastproject.ea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import java.util.ArrayList;

public class EaRetryBoxAdapter extends RecyclerView.Adapter<EaRetryBoxAdapter.ViewHolder> {
    LayoutInflater inflater;
    MainActivity activity;
    ArrayList<EaVO> list;

    public EaRetryBoxAdapter(LayoutInflater inflater, MainActivity activity, ArrayList<EaVO> list) {
        this.inflater = inflater;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_retrybox,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_no.setText(list.get(i).getEa_num());
        h.tv_title.setText(list.get(i).getEa_title());
        h.tv_date.setText(list.get(i).getEa_date().toString());
        h.line_retry.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Fragment f = new EaInfoFragment();
            bundle.putString("ea_num", list.get(i).getEa_num());
            bundle.putInt("no",2);
            f.setArguments(bundle);
            activity.changeFragment(f);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout line_retry;
        TextView tv_no, tv_title, tv_date;
        public ViewHolder(@NonNull View v) {
            super(v);
            line_retry = v.findViewById(R.id.line_retry);
            tv_no = v.findViewById(R.id.tv_no);
            tv_title = v.findViewById(R.id.tv_title);
            tv_date = v.findViewById(R.id.tv_date);
        }
    }
}
