package com.example.lastproject.notice;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<ReplyVO>  reply;
    Context context;

    public ReplyAdapter(LayoutInflater inflater,  ArrayList<ReplyVO> reply, Context context) {
        this.inflater = inflater;
        this.reply = reply;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_no_recv_reply, parent, false);
        ReplyAdapter.ViewHolder viewHolder = new ReplyAdapter.ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_no_reply_content.setText(reply.get(i).getReply_content());
        h.tv_no_reply_date.setText(reply.get(i).getReply_create_date());
    }

    @Override
    public int getItemCount() {
        return reply.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no_reply_content, tv_no_reply_date;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_no_reply_content = v.findViewById(R.id.tv_no_reply_content);
            tv_no_reply_date = v.findViewById(R.id.tv_no_reply_date);

        }
    }
}
