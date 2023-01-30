package com.example.lastproject.attend;

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
import com.example.lastproject.Org_Chart.OrgVO;
import com.example.lastproject.Org_Chart.Org_all_adapter;
import com.example.lastproject.R;

import java.util.ArrayList;

public class Attend_Admin_Adapter extends RecyclerView.Adapter<Attend_Admin_Adapter.ViewHolder> {

    public Attend_Admin_Adapter(LayoutInflater inflater, ArrayList<AttendAdminVO> list, Context context, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    LayoutInflater inflater;
    ArrayList<AttendAdminVO> list;
    Context context;
    MainActivity activity;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_attend_admin, parent, false);
        Attend_Admin_Adapter.ViewHolder viewHolder = new  Attend_Admin_Adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(list.get(position).getEmp_name()!= null){
            holder.tv_emp_name.setText(list.get(position).getEmp_name());
        }else {
            holder.tv_emp_name.setVisibility(View.GONE);
        }

        if(list.get(position).getBranch_name()!= null){
            holder.tv_emp_branch.setText(list.get(position).getBranch_name());
        } else{
            holder.tv_emp_branch.setVisibility(View.GONE);
        }


        if(list.get(position).getDepartment_name()!=null){
            holder.tv_emp_dep.setText(list.get(position).getDepartment_name());
        }else {
            holder.tv_emp_dep.setVisibility(View.GONE);
        }

        if(list.get(position).getRank_name()!=null) {
            holder.tv_emp_rank.setText(list.get(position).getRank_name());
        }else {
            holder.tv_emp_rank.setVisibility(View.GONE);
        }

        if(list.get(position).getProfile_path()!=null){
            Glide.with(activity).load(list.get(position).getProfile_path()).error(R.drawable.error_user_profile).into(holder.iv_emp_profile);
        }

        if(list.get(position).getAttend_date()!=null){
            holder.date.setText(list.get(position).getAttend_date().substring(0,10));
        }else {
            holder.date.setText("");
        }

        if(list.get(position).getAttend_on()!=null) {
            holder.on.setText("출근 "+list.get(position).getAttend_on().substring(11, 16));
        } else {
            holder.on.setText(" ");
        }


        if(list.get(position).getAttend_off()!=null) {
            holder.off.setText("퇴근 "+list.get(position).getAttend_off().substring(11, 16));
        }else {
            holder.off.setText(" ");
        }

        
        if(  list.get(position).getWork_time()!=null){
            holder.now.setText(list.get(position).getWork_time()+"시간");
        }else {
            holder.now.setText(" ");
        }

        if(list.get(position).getAtt_state() != null) {
            holder.status.setText(list.get(position).getAtt_state());
        } holder.status.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_emp_name, tv_emp_branch, tv_emp_dep, tv_emp_rank,now,date, on,off,status;
        ImageView iv_emp_profile;


        public ViewHolder(@NonNull View v) {
            super(v);
            iv_emp_profile =  v.findViewById(R.id.iv_emp_profile);
            tv_emp_name = v.findViewById(R.id.tv_emp_name);
            tv_emp_branch= v.findViewById(R.id.tv_emp_branch);
            tv_emp_dep = v.findViewById(R.id.tv_emp_dep);
            tv_emp_rank = v.findViewById(R.id.tv_emp_rank);
            date = v.findViewById(R.id.date);
            on= v.findViewById(R.id.on);
            off=v.findViewById(R.id.off);
            now = v.findViewById(R.id.now);
            status = v.findViewById(R.id.status);




        }
    }
}
