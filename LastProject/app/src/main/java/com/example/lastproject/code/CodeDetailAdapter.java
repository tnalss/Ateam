package com.example.lastproject.code;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class CodeDetailAdapter extends RecyclerView.Adapter<CodeDetailAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<CodeVO> list;
    private MainActivity activity;

    public CodeDetailAdapter(LayoutInflater inflater, ArrayList<CodeVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_code_detail_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_code_category.setText(list.get(position).getCode_category()+list.get(position).getCode_num());
        holder.tv_code_value.setText(list.get(position).getCode_value());

        int i = position;
        holder.ll_each_code.setOnClickListener(v -> {
            if(list.get(position).getCreater().equals("admin") ||list.get(position).getCreater().equals("pjy")||list.get(position).getCreater().equals("csm")){
               Toast.makeText(activity, "기본 코드는 변경할 수 없습니다.", Toast.LENGTH_SHORT).show();
                 return;
            }


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_code_category, tv_code_value;
        LinearLayout ll_each_code;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_code_category = itemView.findViewById(R.id.tv_code_category);
            tv_code_value = itemView.findViewById(R.id.tv_code_value);
            ll_each_code = itemView.findViewById(R.id.ll_each_code);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
