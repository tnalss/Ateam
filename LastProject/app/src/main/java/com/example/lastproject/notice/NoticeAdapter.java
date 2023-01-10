package com.example.lastproject.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.MainActivity;
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
        h.tv_no_views.setText(" 조회수 : " + notice.get(i).getBoard_hits());
        final int index = i;
            // 공지사항 내용
            h.li_no_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListInfo_no_Activity.class);
                    intent.putExtra("board_no", notice.get(index).getBoard_no());
                    context.startActivity(intent);
                }
            });

    }



    @Override
    public int getItemCount() {
        return notice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no_title, tv_no_date, tv_no_views;
        ImageView img_no_img;
        LinearLayout li_no_title;

        public ViewHolder(@NonNull View v) {
            super(v);
            tv_no_title = v.findViewById(R.id.tv_no_title);
            img_no_img = v.findViewById(R.id.img_no_img);
            tv_no_date = v.findViewById(R.id.tv_no_date);
            li_no_title = v.findViewById(R.id.li_no_title);
            tv_no_views = v.findViewById(R.id.tv_no_views);

        }
    }
}
