package com.example.lastproject.ea;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;
import com.example.lastproject.employee.EmployeeVO;

import java.util.ArrayList;

public class EaSignerListAdatper extends RecyclerView.Adapter<EaSignerListAdatper.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EmployeeVO> list;

    public EaSignerListAdatper(LayoutInflater inflater, ArrayList<EmployeeVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_signer_list,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_signer_no.setText("사번   " + list.get(i).getEmp_no());
        h.tv_signer_name.setText("이름  " + list.get(i).getEmp_name());
        h.tv_signer_dep.setText("부서  " + list.get(i).getDepartment_name());
        h.tv_signer_rank.setText("직급  " + list.get(i).getRank_name());
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
        TextView tv_signer_no, tv_signer_name, tv_signer_dep, tv_signer_rank;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_signer_no = v.findViewById(R.id.tv_singer_no);
            tv_signer_name = v.findViewById(R.id.tv_singer_name);
            tv_signer_dep = v.findViewById(R.id.tv_singer_dep);
            tv_signer_rank = v.findViewById(R.id.tv_singer_rank);
        }
    }
}
