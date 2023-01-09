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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
        View v = inflater.inflate(R.layout.item_recv_attend_record,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {


        h.date.setText(list.get(i).getAttend_date());
        h.on.setText(list.get(i).getAttend_on());
        h.off.setText(list.get(i).getAttend_off());
        h.now.setText(list.get(i).getAtt_state());


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


    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView  date, on,off, now;

        public ViewHolder(@NonNull View v) {
            super(v);
            date = v.findViewById(R.id.date);
            on= v.findViewById(R.id.on);
            off=v.findViewById(R.id.off);
            now = v.findViewById(R.id.now);

        }
    }




}
