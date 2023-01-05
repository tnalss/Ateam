package com.example.lastproject.ea;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FormListAdapter extends RecyclerView.Adapter<FormListAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaCodeVO> list;
    MainActivity activity ;


    public FormListAdapter(LayoutInflater inflater, ArrayList<EaCodeVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
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

        h.line_form.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Fragment f = new WriteEaFragment();
            bundle.putSerializable("form", list.get(i));
            f.setArguments(bundle);
            activity.changeFragment(f);
        });

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
        LinearLayout line_form;
        TextView tv_form_title, tv_form_comment;
        public ViewHolder(@NonNull View v) {
            super(v);
            line_form = v.findViewById(R.id.line_form);
            tv_form_title = v.findViewById(R.id.tv_form_title);
            tv_form_comment = v.findViewById(R.id.tv_form_comment);
        }
    }
}
