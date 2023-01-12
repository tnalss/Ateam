package com.example.lastproject.notice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        h.tv_sec_views.setText(" 조회수 : " + secret.get(i).getBoard_hits());
        h.tv_sec_reply_cnt.setText("댓글 : " + secret.get(i).getReply_count());
        final int index = i;
        // 익명게시판 내용
         h.li_sec_title.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent = new Intent(context, ListInfo_sec_Activity.class);
                  intent.putExtra("board_no", secret.get(index).getBoard_no());
                  intent.putExtra("re_count", secret.get(index).getReply_count());
                context.startActivity(intent);
            }
         });

         // 좋아요
         h.img_sec_heart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                     if(h.img_sec_heart.getVisibility() == View.VISIBLE) {
                         h.img_sec_heart.setVisibility(View.GONE);
                         h.img_sec_heart2.setVisibility(View.VISIBLE);
                     } else if(h.img_sec_heart2.getVisibility() == View.VISIBLE) {
                         h.img_sec_heart2.setVisibility(View.GONE);
                         h.img_sec_heart.setVisibility(View.VISIBLE);
                     }


             }
         });
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
        TextView tv_sec_date, tv_sec_title, tv_sec_views, tv_sec_reply_cnt;
        ImageView img_sec_img, img_sec_heart, img_sec_heart2;
        LinearLayout li_sec_title;
        public ViewHolder(@NonNull View v) {
            super(v);
            img_sec_img = v.findViewById(R.id.img_sec_img);
            tv_sec_title = v.findViewById(R.id.tv_sec_title);
            tv_sec_date = v.findViewById(R.id.tv_sec_date);
            li_sec_title = v.findViewById(R.id.li_sec_title);
            img_sec_heart = v.findViewById(R.id.img_sec_heart);
            img_sec_heart2 = v.findViewById(R.id.img_sec_heart2);
            tv_sec_views = v.findViewById(R.id.tv_sec_views);
            tv_sec_reply_cnt = v.findViewById(R.id.tv_sec_reply_cnt);
        }
    }
}
