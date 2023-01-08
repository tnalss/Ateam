package com.example.lastproject.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<ScheduleVO> list;
    public ScheduleAdapter(LayoutInflater inflater, ArrayList<ScheduleVO> list) {
        this.inflater = inflater;
        this.list = list;

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sche_title,tv_sche_content,tv_period,tv_complete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sche_title = itemView.findViewById(R.id.tv_sche_title);
            tv_sche_content = itemView.findViewById(R.id.tv_sche_content);
            tv_period = itemView.findViewById(R.id.tv_period);
            tv_complete = itemView.findViewById(R.id.tv_complete);

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
