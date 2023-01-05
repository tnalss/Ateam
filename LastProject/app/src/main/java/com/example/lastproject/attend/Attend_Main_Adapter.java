package com.example.lastproject.attend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Attend_Main_Adapter extends RecyclerView.Adapter<Attend_Main_Adapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<AttendVO> list;
    Context context;
    public Attend_Main_Adapter(LayoutInflater inflater, ArrayList<AttendVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.fragment_attend,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.date.setText(getDate());
        h.on.setText(list.get(i).getAttend_on());
        h.off.setText(list.get(i).getAttend_off());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView  date, on,off, work_state, sum_hour, now;

        public ViewHolder(@NonNull View v) {
            super(v);
            date = v.findViewById(R.id.date);
            on= v.findViewById(R.id.on);
            off=v.findViewById(R.id.off);
            //work_state = v.findViewById(R.id.work_state);
            //sum_hour = v.findViewById(R.id.sum_hour);
            now = v.findViewById(R.id.now);

        }
    }

    // 현재 시간을 "yyyy-MM-dd"로 표시하는 메소드
    private String getDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);

        return getTime;
    }

    //출퇴근 시간 가져오는 메소드




}
