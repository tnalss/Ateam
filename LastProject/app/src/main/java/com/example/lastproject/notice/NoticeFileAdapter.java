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

import java.util.List;

public class NoticeFileAdapter extends RecyclerView.Adapter<NoticeFileAdapter.ViewHolder>{
    LayoutInflater inflater;
    Context context;
    List<NoticeFileVO> filevo;

    public NoticeFileAdapter(LayoutInflater inflater, List<NoticeFileVO> filevo ,Context context) {
        this.inflater = inflater;
        this.context = context;
        this.filevo = filevo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_no_recv_file, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        Glide.with(context).load(filevo.get(i).getFile_path()).error(R.drawable.error_user_profile).into(h.img_no_file);
    }

    @Override
    public int getItemCount() {
        return filevo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_no_file;
        public ViewHolder(@NonNull View v) {
            super(v);
            img_no_file = v.findViewById(R.id.img_no_file);

        }
    }
}
