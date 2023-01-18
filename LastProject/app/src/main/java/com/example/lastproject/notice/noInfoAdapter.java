package com.example.lastproject.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lastproject.R;

import java.util.ArrayList;

public class noInfoAdapter extends RecyclerView.Adapter<noInfoAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<NoticeFileVO> filevo;
    Context context;

    public noInfoAdapter(LayoutInflater inflater,ArrayList<NoticeFileVO> filevo, Context context) {
        this.inflater = inflater;
        this.filevo = filevo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_no_recv_info_file, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        Glide.with(context).load(filevo.get(i).getFile_path()).into(h.img_no_info_file);
    }

    @Override
    public int getItemCount() {
        return filevo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_no_info_file;
        public ViewHolder(@NonNull View v) {
            super(v);
            img_no_info_file = v.findViewById(R.id.img_no_info_file);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return  position;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
