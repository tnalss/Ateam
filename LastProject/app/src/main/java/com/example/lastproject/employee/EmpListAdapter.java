package com.example.lastproject.employee;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import java.util.ArrayList;

public class EmpListAdapter extends RecyclerView.Adapter<EmpListAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EmployeeVO> list;
    MainActivity activity;

    public EmpListAdapter(LayoutInflater inflater, ArrayList<EmployeeVO> list, MainActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_employees,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //추후 프로필도 추가해야함.
        holder.tv_emp_name.setText(list.get(position).getEmp_name());
        holder.tv_emp_branch_dept_rank.setText(list.get(position).getBranch_name()+" / "+ list.get(position).getDepartment_name()+" / " +list.get(position).getRank_name());
        //현재근무상태 W0출근 W1퇴근

        if  (list.get(position).getAdmin().equals("X0")){
            holder.tv_nowStatus.setText("퇴사");
        } else {
            holder.tv_nowStatus.setText("재직중");
        }
        int i = position;
        holder.ll_each_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 각각 사원 클릭했을 시 사원 상세정보화면으로
                // 오늘의 해당사원 근무상태를 긁어서 detail로 전송
                new CommonMethod().setParams("emp_no",list.get(i).getEmp_no()).sendPost("attendString",(isResult, data) -> {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("vo",list.get(i));
                    if(data!=null){
                    bundle.putString("status",data);}
                    Fragment fragment = new EmpDetailFragment();
                    fragment.setArguments(bundle);
                    activity.changeFragment(fragment);

                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_emp_name,tv_emp_branch_dept_rank,tv_nowStatus;
        ImageView iv_emp_profile;
        LinearLayout ll_each_emp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_emp_profile = itemView.findViewById(R.id.iv_emp_profile);
            tv_emp_name = itemView.findViewById(R.id.tv_emp_name);
            tv_emp_branch_dept_rank = itemView.findViewById(R.id.tv_emp_branch_dep_rank);
            tv_nowStatus = itemView.findViewById(R.id.tv_nowStatus);
            ll_each_emp = itemView.findViewById(R.id.ll_each_emp);
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
