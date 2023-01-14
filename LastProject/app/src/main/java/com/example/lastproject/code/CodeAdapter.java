package com.example.lastproject.code;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<CodeVO> list;

    public CodeAdapter(LayoutInflater inflater, ArrayList<CodeVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_code_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_code_category.setText(list.get(position).getCode_category());
        holder.tv_code_name.setText(list.get(position).getCode_name());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_code_category, tv_code_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_code_category = itemView.findViewById(R.id.tv_code_category);
            tv_code_name = itemView.findViewById(R.id.tv_code_name);

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
