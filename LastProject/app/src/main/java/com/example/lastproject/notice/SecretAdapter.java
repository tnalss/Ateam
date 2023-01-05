package com.example.lastproject.notice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class SecretAdapter extends RecyclerView.Adapter<SecretAdapter.ViewHolder>{
    LayoutInflater inflater;
    Context context;
    ArrayList<NoticeVO> secret;

    public SecretAdapter(LayoutInflater inflater, Context context, ArrayList<NoticeVO> secret) {
        this.inflater = inflater;
        this.context = context;
        this.secret = secret;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_no_recv_secret, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_sec_title.setText(secret.get(i).getBoard_title());
        h.tv_sec_date.setText(secret.get(i).getWrite_date());
    }

    @Override
    public int getItemCount() {
        return secret.size();
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
        TextView tv_sec_date, tv_sec_title;
        ImageView img_sec_img;
        public ViewHolder(@NonNull View v) {
            super(v);
            img_sec_img = v.findViewById(R.id.img_sec_img);
            tv_sec_title = v.findViewById(R.id.tv_sec_title);
            tv_sec_date = v.findViewById(R.id.tv_sec_date);
        }
    }
}
