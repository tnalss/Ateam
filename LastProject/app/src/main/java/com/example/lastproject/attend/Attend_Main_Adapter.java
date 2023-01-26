package com.example.lastproject.attend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;
import com.example.lastproject.al.AlVO;

import java.util.ArrayList;


public class Attend_Main_Adapter extends RecyclerView.Adapter<Attend_Main_Adapter.ViewHolder> {
    public Attend_Main_Adapter(LayoutInflater inflater, ArrayList<AttendVO> list,Context context) {
        this.inflater = inflater;
        this.list = list;

        this.context = context;
    }

    LayoutInflater inflater;
    ArrayList<AttendVO> list;

    Context context;




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_attend_record,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {


        h.date.setText(list.get(i).getAttend_date().substring(0,10));


        if(list.get(i).getAttend_on()!=null) {
            h.on.setText(list.get(i).getAttend_on().substring(0, 16));
        } else {
            h.on.setText("");
            h.tv_on.setVisibility(View.GONE);
        }


        if(list.get(i).getAttend_off()!=null) {
            h.off.setText(list.get(i).getAttend_off().substring(0, 16));
        } else {
            h.off.setText("");
            h.tv_off.setVisibility(View.GONE);
        }

        if(list.get(i).getAtt_state() == null){
            h.check.setVisibility(View.GONE);
        }
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
        TextView  date, on,off, now,tv_off, tv_on;
        ImageView check;

        public ViewHolder(@NonNull View v) {
            super(v);
            date = v.findViewById(R.id.date);
            on= v.findViewById(R.id.on);
            off=v.findViewById(R.id.off);
            now = v.findViewById(R.id.now);
            check = v.findViewById(R.id.check);
            tv_on = v.findViewById(R.id.tv_on);
            tv_off=v.findViewById(R.id.tv_off);

        }
    }




}
