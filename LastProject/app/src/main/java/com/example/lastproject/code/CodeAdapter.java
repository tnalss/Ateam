package com.example.lastproject.code;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import java.util.ArrayList;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<CodeVO> list;
    private MainActivity activity;

    public CodeAdapter(LayoutInflater inflater, ArrayList<CodeVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
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

        int i = position;
        holder.ll_each_code.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("vo",list.get(position));
            Fragment fragment = new CodeDetailFragment();
            fragment.setArguments(bundle);
            activity.changeFragment(fragment);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_code_category, tv_code_name;
        LinearLayout ll_each_code;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_code_category = itemView.findViewById(R.id.tv_code_category);
            tv_code_name = itemView.findViewById(R.id.tv_code_name);
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
