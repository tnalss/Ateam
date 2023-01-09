package com.example.lastproject.ea;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;
import com.example.lastproject.employee.EmployeeVO;

import java.util.ArrayList;

public class WriteSearchAdapter extends RecyclerView.Adapter<WriteSearchAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EmployeeVO> list;
    WriteEaFragment fragment;
    int flag;

    public WriteSearchAdapter( int flag , LayoutInflater inflater, ArrayList<EmployeeVO> list, WriteEaFragment fragment) {
        this.inflater = inflater;
        this.list = list;
        this.fragment = fragment;
        this.flag = flag;
    }

    public WriteSearchAdapter(LayoutInflater inflater, ArrayList<EmployeeVO> list, WriteEaFragment fragment) {
        this.inflater = inflater;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_write_search,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_emp_no.setText(list.get(i).getEmp_no());
        h.tv_emp_name.setText(list.get(i).getEmp_name());
        h.tv_emp_dep.setText(list.get(i).getDepartment_name());
        h.tv_emp_rank.setText(list.get(i).getRank_name());
        h.line_write_search.setOnClickListener(v -> {
            if(flag==1){
                fragment.signmakeChip(list.get(i).getEmp_name());
                h.line_write_search.setVisibility(View.GONE);
            }else if (flag==2){
                fragment.refermakeChip(list.get(i).getEmp_name());
                h.line_write_search.setVisibility(View.GONE);
            }
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
        TextView tv_emp_name, tv_emp_no, tv_emp_dep, tv_emp_rank;
        LinearLayout line_write_search;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_emp_name = v.findViewById(R.id.tv_emp_name);
            tv_emp_no = v.findViewById(R.id.tv_emp_no);
            tv_emp_dep = v.findViewById(R.id.tv_emp_dep);
            tv_emp_rank = v.findViewById(R.id.tv_emp_rank);
            line_write_search = v.findViewById(R.id.line_write_search);
        }
    }

}
