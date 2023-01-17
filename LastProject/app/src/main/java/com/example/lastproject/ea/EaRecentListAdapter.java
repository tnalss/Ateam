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
import com.example.lastproject.common.Common;

import java.util.ArrayList;

public class EaRecentListAdapter extends RecyclerView.Adapter<EaRecentListAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaVO> list;
    MainActivity activity ;

    public EaRecentListAdapter(LayoutInflater inflater, ArrayList<EaVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_recentlist,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_title.setText(list.get(i).getEa_title());
        h.tv_name_date.setText(Common.loginInfo.getEmp_name()+" | " + list.get(i).getEa_date());
        h.tv_process.setText(list.get(i).getEa_status());
        int cnt = i;
        h.line_recent.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("ea_num", list.get(cnt).getEa_num());
            if(list.get(cnt).getEa_status().equals("회수")){
                bundle.putInt("no",2);
            }

                Fragment f = new EaInfoFragment();
                f.setArguments(bundle);
                activity.changeFragment(f);
        });
    }

    @Override
    public int getItemCount() {
        return  list.size();
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
        LinearLayout line_recent;
        TextView tv_title, tv_name_date, tv_process;
        public ViewHolder(@NonNull View v) {
            super(v);
            line_recent = v.findViewById(R.id.line_recent);
            tv_title = v.findViewById(R.id.tv_title);
            tv_name_date = v.findViewById(R.id.tv_name_date);
            tv_process = v.findViewById(R.id.tv_process);
        }
    }
}
