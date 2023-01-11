package com.example.lastproject.Org_Chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.attend.Attend_Main_Adapter;

import java.util.ArrayList;

public class Org_all_adapter extends RecyclerView.Adapter<Org_all_adapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<OrgVO> list;
    MainActivity activity;

    public Org_all_adapter(LayoutInflater inflater, ArrayList<OrgVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_org_all, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_emp_name.setText(list.get(position).getEmp_name());
        holder.tv_emp_branch.setText(list.get(position).getBranch_name());
        holder.tv_emp_dep.setText(list.get(position).getDepartment_name());
        holder.tv_emp_rank.setText(list.get(position).getRank_name());
        if(list.get(position).getProfile_path()!=null){
            Glide.with(activity).load(list.get(position).getProfile_path()).error(R.drawable.error_user_profile).into(holder.iv_emp_profile);
        }


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
        TextView tv_emp_name, tv_emp_branch, tv_emp_dep, tv_emp_rank;
        ImageView iv_emp_profile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_emp_profile = itemView.findViewById(R.id.iv_emp_profile);
            tv_emp_name = itemView.findViewById(R.id.tv_emp_name);
            tv_emp_branch= itemView.findViewById(R.id.tv_emp_branch);
            tv_emp_dep = itemView.findViewById(R.id.tv_emp_dep);
            tv_emp_rank = itemView.findViewById(R.id.tv_emp_rank);


        }
    }


}