package com.example.lastproject.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    ArrayList<NoticeVO> notice;

    public NoticeAdapter(LayoutInflater inflater, Context context, ArrayList<NoticeVO> notice) {
        this.inflater = inflater;
        this.context = context;
        this.notice = notice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_no_recv_notice, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
            h.tv_no_title.setText(notice.get(i).getBoard_title());
            h.tv_no_date.setText(notice.get(i).getWrite_date());
    }

    @Override
    public int getItemCount() {
        return notice.size()    ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no_title, tv_no_date;
        ImageView img_no_img;

        public ViewHolder(@NonNull View v) {
            super(v);
            tv_no_title = v.findViewById(R.id.tv_no_title);
            img_no_img = v.findViewById(R.id.img_no_img);
            tv_no_date = v.findViewById(R.id.tv_no_date);

        }
    }
}
