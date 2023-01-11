package com.example.lastproject.notice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<ReplyVO> reply;
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

        int index = i;
        h.tv_no_reply_content.setText(reply.get(i).getReply_content());
        h.tv_no_reply_date.setText(reply.get(i).getReply_create_date().toString().substring(0,11));
        /* 댓글 삭제 */
        h.tv_no_reply_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(inflater.getContext()).setTitle("확인").setMessage("댓글을 삭제하겠습니까")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            new CommonMethod().setParams("reply_no", reply.get(index).getReply_no()).sendPost("reply_delete.no", (isResult, data) -> {
                                Intent intent = new Intent(context, ListInfo_no_Activity.class);
                                intent.putExtra("board_no", reply.get(index).getBoard_no());
                                context.startActivity(intent);
                                ((Activity)context).finish();
                            });
                        }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                        }).show();
            }
        });

        /* 댓글 수정 버튼*/
        h.tv_no_reply_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.btn_no_reply_update.setVisibility(View.VISIBLE);
                h.edt_no_reply_update.setVisibility(View.VISIBLE);

                h.edt_no_reply_update.setText(reply.get(index).getReply_content());
            }
        });

        h.btn_no_reply_update.setOnClickListener(new View.OnClickListener() {
        ReplyVO vo = new ReplyVO();
            @Override
            public void onClick(View v) {
        vo.setReply_no(reply.get(index).getReply_no());
        vo.setReply_content(h.edt_no_reply_update.getText().toString());
        vo.getReply_no();
        vo.setReply_content(h.edt_no_reply_update.getText().toString());
        new CommonMethod().setParams("re", new Gson().toJson(vo)).sendPost("reply_update.no", (isResult, data) -> {
            h.btn_no_reply_update.setVisibility(View.GONE);
            h.edt_no_reply_update.setVisibility(View.GONE);
        });
            }
        });

    }
    @Override
    public int getItemCount() {
        return reply.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no_reply_content, tv_no_reply_date, tv_no_reply_update, tv_no_reply_delete;
        Button btn_no_reply_update;
        EditText edt_no_reply_update;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_no_reply_content = v.findViewById(R.id.tv_no_reply_content);
            tv_no_reply_date = v.findViewById(R.id.tv_no_reply_date);
            tv_no_reply_update = v.findViewById(R.id.tv_no_reply_update);
            tv_no_reply_delete = v.findViewById(R.id.tv_no_reply_delete);
            btn_no_reply_update = v.findViewById(R.id.btn_no_reply_update);
            edt_no_reply_update = v.findViewById(R.id.edt_no_reply_update);

        }
    }
}
