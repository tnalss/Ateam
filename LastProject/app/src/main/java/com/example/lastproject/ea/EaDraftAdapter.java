package com.example.lastproject.ea;

import android.content.Context;
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

public class EaDraftAdapter extends RecyclerView.Adapter<EaDraftAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaVO> list;
    MainActivity activity ;

    public EaDraftAdapter(LayoutInflater inflater, ArrayList<EaVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_draftbox, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

            h.tv_status.setText(list.get(i).getEa_status());
            h.tv_title.setText(list.get(i).getEa_title());
            h.tv_date.setText(list.get(i).getEa_date().toString());
            if(list.get(i).getEa_a_date() == null){
                h.tv_sign_date.setText("미결재");
            }else{
                h.tv_sign_date.setText(list.get(i).getEa_a_date().toString());
            }
            if(list.get(i).getEa_read().equals("true")){
                h.tv_read.setText("읽음");
            }else {
                h.tv_read.setText("읽지않음");
            }
            h.line_draft.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                Fragment f = new EaInfoFragment();
                bundle.putString("ea_num", list.get(i).getEa_num());
                bundle.putInt("no",0);
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
        LinearLayout line_draft;
        TextView tv_status, tv_title, tv_date, tv_sign_date, tv_read;
        public ViewHolder(@NonNull View v) {
            super(v);
            line_draft = v.findViewById(R.id.line_draft);
            tv_status = v.findViewById(R.id.tv_status);
            tv_title = v.findViewById(R.id.tv_title);
            tv_date = v.findViewById(R.id.tv_date);
            tv_sign_date = v.findViewById(R.id.tv_sign_date);
            tv_read = v.findViewById(R.id.tv_read);

        }
    }
}
