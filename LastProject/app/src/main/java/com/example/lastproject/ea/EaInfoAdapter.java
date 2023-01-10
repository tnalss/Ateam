package com.example.lastproject.ea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class EaInfoAdapter extends RecyclerView.Adapter<EaInfoAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaVO> list;
    Context context;

    public EaInfoAdapter(LayoutInflater inflater, ArrayList<EaVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_info_line,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

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
        TextView tv_info_name, tv_info_email,tv_info_dep,tv_info_date;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_info_name = v.findViewById(R.id.tv_info_name);
            tv_info_email = v.findViewById(R.id.tv_info_email);
            tv_info_dep = v.findViewById(R.id.tv_info_dep);
            tv_info_date = v.findViewById(R.id.tv_info_date);
        }
    }
}
