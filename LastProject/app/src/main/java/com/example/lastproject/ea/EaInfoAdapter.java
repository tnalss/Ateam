package com.example.lastproject.ea;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.employee.EmployeeVO;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EaInfoAdapter extends RecyclerView.Adapter<EaInfoAdapter.ViewHolder> {
    int temp;
    LayoutInflater inflater;
    ArrayList<EaVO> ea_list;
    EmployeeVO vo;
    Context context;

    public EaInfoAdapter(LayoutInflater inflater, ArrayList<EaVO> ea_list, Context context, int temp) {
        this.inflater = inflater;
        this.ea_list = ea_list;
        this.context = context;
        this.temp = temp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_ea_info_line,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        if(temp == 1){
            if(i == 0){
                new CommonMethod().setParams("emp_no",ea_list.get(i).getEmp_no()).sendPost("info.emp",(isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setText(ea_list.get(i).getEa_date().toString());
                });
            }else{
                new CommonMethod().setParams("emp_no", ea_list.get(i-1).getEa_receiver()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_fixed.setText("결재");
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    if(Common.loginInfo.getEmp_no().equals(vo.getEmp_no())){
                        h.line_btn_sign.setVisibility(View.VISIBLE);
                        h.tv_info_status.setText(ea_list.get(i-1).getEa_status());
                        h.tv_info_status.setVisibility(View.VISIBLE);
                    }else{
                        h.tv_info_status.setText(ea_list.get(i-1).getEa_status());
                        h.tv_info_status.setVisibility(View.VISIBLE);
                    }
                    h.tv_info_date.setVisibility((View.GONE));
                });
            }
        }else if(temp == 0){
            if(i == 0){
                new CommonMethod().setParams("emp_no",ea_list.get(i).getEmp_no()).sendPost("info.emp",(isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setText(ea_list.get(i).getEa_date().toString());
                });
            }else{
                new CommonMethod().setParams("emp_no", ea_list.get(i-1).getEa_receiver()).sendPost("info.emp", (isResult, data) -> {
                    vo = new Gson().fromJson(data, EmployeeVO.class);
                    h.tv_fixed.setText("결재");
                    h.tv_info_name.setText(vo.getEmp_name());
                    h.tv_info_email.setText(vo.getEmail());
                    h.tv_info_dep.setText(vo.getDepartment_name());
                    h.tv_info_date.setVisibility(View.GONE);
                    h.tv_info_status.setVisibility(View.VISIBLE);
                    h.tv_info_status.setText(ea_list.get(i-1).getEa_status());

                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return ea_list.size()+1;
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
        LinearLayout line_btn_sign;
        Button btn_approved, btn_denied;
        ImageView imgv_stamp;
        TextView tv_info_name, tv_info_email,tv_info_dep,tv_info_date,tv_fixed, tv_info_status;
        public ViewHolder(@NonNull View v) {
            super(v);
            line_btn_sign = v.findViewById(R.id.line_btn_sign);
            btn_approved = v.findViewById(R.id.btn_approved);
            btn_denied = v.findViewById(R.id.btn_denied);
            imgv_stamp = v.findViewById(R.id.imgv_stamp);
            tv_info_name = v.findViewById(R.id.tv_info_name);
            tv_info_email = v.findViewById(R.id.tv_info_email);
            tv_info_dep = v.findViewById(R.id.tv_info_dep);
            tv_info_date = v.findViewById(R.id.tv_info_date);
            tv_fixed = v.findViewById(R.id.tv_fixed);
            tv_info_status = v.findViewById(R.id.tv_info_status);
        }
    }
}
