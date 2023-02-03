package com.example.lastproject.al;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;
import com.example.lastproject.attend.AttendActivity;

import java.util.ArrayList;

public class Al_Apply_Adapter extends RecyclerView.Adapter<Al_Apply_Adapter.ViewHolder> {

    public Al_Apply_Adapter(LayoutInflater inflater, ArrayList<AlVO> al_list) {
        this.inflater = inflater;
        this.al_list = al_list;
    }

    LayoutInflater inflater;
    ArrayList<AlVO> al_list;





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_attend_apply,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

    h.apply_what.setText(al_list.get(i).getCode_value());
    h.apply_date.setText(al_list.get(i).getAl_reg_date().substring(0,10));



    }

    @Override
    public int getItemCount() {
        return al_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView    apply_what, apply_date ;


        public ViewHolder(@NonNull View v) {
            super(v);

            apply_what = v.findViewById(R.id.apply_what);
            apply_date = v.findViewById(R.id.apply_date);

        }
    }
}
