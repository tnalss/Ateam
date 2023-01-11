package com.example.lastproject.notice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import java.util.ArrayList;

public class Reply_sec_Adapter extends RecyclerView.Adapter<Reply_sec_Adapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<ReplyVO>  reply;
    Context context;

    public Reply_sec_Adapter(LayoutInflater inflater, ArrayList<ReplyVO> reply, Context context) {
        this.inflater = inflater;
        this.reply = reply;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_sec_recv_reply, parent, false);
        Reply_sec_Adapter.ViewHolder viewHolder = new Reply_sec_Adapter.ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        int index = i;
        h.tv_sec_reply_content.setText(reply.get(i).getReply_content());
        h.tv_sec_reply_date.setText(reply.get(i).getReply_create_date().toString().substring(0,11));

        /* 댓글 삭제 */
        h.tv_sec_reply_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(inflater.getContext()).setTitle("확인").setMessage("댓글을 삭제하겠습니까")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                new CommonMethod().setParams("reply_no", reply.get(index).getReply_no()).sendPost("reply_delete.no", (isResult, data) -> {
                    Intent intent = new Intent(context, ListInfo_sec_Activity.class);
                    intent.putExtra("board_no", reply.get(index).getBoard_no());
                    context.startActivity(intent);
                    ((Activity)context).finish();
                });
                        }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                        }).show();
            }
        });
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return reply.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sec_reply_content, tv_sec_reply_date, tv_sec_reply_update, tv_sec_reply_delete;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_sec_reply_content = v.findViewById(R.id.tv_sec_reply_content);
            tv_sec_reply_date = v.findViewById(R.id.tv_sec_reply_date);
            tv_sec_reply_update = v.findViewById(R.id.tv_sec_reply_update);
            tv_sec_reply_delete = v.findViewById(R.id.tv_sec_reply_delete);

        }
    }

}
