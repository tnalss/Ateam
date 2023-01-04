package com.example.lastproject.attend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

public class Attend_Activity_Adapter extends RecyclerView.Adapter<Attend_Activity_Adapter.ViewHolder> {
    LayoutInflater inflater;

    public Attend_Activity_Adapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_attend_apply,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView apply_result, apply_who,   apply_what, apply_date , state;
       ImageView check;

        public ViewHolder(@NonNull View v) {
            super(v);
            apply_result = v.findViewById(R.id.apply_result);
            apply_who = v.findViewById(R.id.apply_who);
            apply_what = v.findViewById(R.id.apply_what);
            apply_date = v.findViewById(R.id.apply_date);
            state = v.findViewById(R.id.state);
            check = v.findViewById(R.id.check);
        }
    }
}
