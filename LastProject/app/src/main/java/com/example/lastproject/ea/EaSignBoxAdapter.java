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

public class EaSignBoxAdapter extends RecyclerView.Adapter<EaSignBoxAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaVO> list;
    Context context;

    public EaSignBoxAdapter(LayoutInflater inflater, ArrayList<EaVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_draftbox,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_read.setVisibility(View.GONE);
        h.tv_status.setText(list.get(i).getEa_r_statuas());
        h.tv_title.setText(list.get(i).getEa_title());
        h.tv_date.setText(list.get(i).getEa_dummy());
        h.tv_sign_date.setText(list.get(i).getEa_date().toString());
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
        TextView tv_status, tv_title, tv_date, tv_sign_date, tv_read;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_status = v.findViewById(R.id.tv_status);
            tv_title = v.findViewById(R.id.tv_title);
            tv_date = v.findViewById(R.id.tv_date);
            tv_sign_date = v.findViewById(R.id.tv_sign_date);
            tv_read = v.findViewById(R.id.tv_read);
        }
    }
}
