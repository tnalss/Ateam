package com.example.lastproject.calendar;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<ScheduleVO> list;
    MainActivity activity;
    public ScheduleAdapter(LayoutInflater inflater, ArrayList<ScheduleVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_schedule_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_sche_title.setText(list.get(position).getSche_title());
        holder.tv_sche_content.setText(list.get(position).getSche_content());
        if (list.get(position).getSche_start().equals(list.get(position).getSche_end())) {
            holder.tv_period.setText(list.get(position).getSche_start().substring(0,10));
        } else {
            holder.tv_period.setText(list.get(position).getSche_start().substring(0, 10) + " ~ " + list.get(position).getSche_end().substring(0, 10));
        }
        if(list.get(position).getSche_status().equals("L0")){
            holder.tv_complete.setVisibility(View.VISIBLE);
        }
        int i = position;
        holder.ll_each_schedule.setOnClickListener(v -> {
//            ScheduleDialog dialog = new ScheduleDialog(v.getContext(),list.get(i));
//
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.show();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("vo",list.get(i));
//            //이름이랑도 필요할것같은데?
//
//
//            Fragment fragment = new ScheduleDetailFragment();
//            fragment.setArguments(bundle);
//            activity.changeFragment(new ScheduleDetailFragment());
//

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sche_title,tv_sche_content,tv_period,tv_complete;
        LinearLayout ll_each_schedule;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sche_title = itemView.findViewById(R.id.tv_sche_title);
            tv_sche_content = itemView.findViewById(R.id.tv_sche_content);
            tv_period = itemView.findViewById(R.id.tv_period);
            tv_complete = itemView.findViewById(R.id.tv_complete);
            ll_each_schedule = itemView.findViewById(R.id.ll_each_schedule);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
