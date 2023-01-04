package com.example.lastproject.ea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FormListAdapter extends RecyclerView.Adapter<FormListAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaCodeVO> list;
    Context context;

    public FormListAdapter(LayoutInflater inflater, ArrayList<EaCodeVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = inflater.inflate(R.layout.item_ea_formlist,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_form_title.setText(list.get(i).getCode_value());
        h.tv_form_comment.setText(list.get(i).getCode_comments());


    }

    @Override
    public int getItemCount() {
        return list.size();
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
        TextView tv_form_title, tv_form_comment;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_form_title = v.findViewById(R.id.tv_form_title);
            tv_form_comment = v.findViewById(R.id.tv_form_comment);
        }
    }
}
