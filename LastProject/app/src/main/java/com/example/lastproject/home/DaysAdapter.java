package com.example.lastproject.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;
import com.example.lastproject.attend.AttendVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<AttendVO> list;

    public DaysAdapter(LayoutInflater inflater, ArrayList<AttendVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_7days_attend, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ko_day_of_week = "", ko_month = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date date = sdf.parse(list.get(position).getAttend_date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Log.d("TAG", "onBindViewHolder: " + calendar.getTime());
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            int DAY_OF_WEEK = calendar.get(Calendar.DAY_OF_WEEK);
            switch (DAY_OF_WEEK) {
                case 1:
                    ko_day_of_week = "일";
                    break;
                case 2:
                    ko_day_of_week = "월";
                    break;
                case 3:
                    ko_day_of_week = "화";
                    break;
                case 4:
                    ko_day_of_week = "수";
                    break;
                case 5:
                    ko_day_of_week = "목";
                    break;
                case 6:
                    ko_day_of_week = "금";
                    break;
                case 7:
                    ko_day_of_week = "토";
                    break;
            }

            switch (month) {
                case 0:
                    ko_month = "1월";
                    break;
                case 1:
                    ko_month = "2월";
                    break;
                case 2:
                    ko_month = "3월";
                    break;
                case 3:
                    ko_month = "4월";
                    break;
                case 4:
                    ko_month = "5월";
                    break;
                case 5:
                    ko_month = "6월";
                    break;
                case 6:
                    ko_month = "7월";
                    break;
                case 7:
                    ko_month = "8월";
                    break;
                case 8:
                    ko_month = "9월";
                    break;
                case 9:
                    ko_month = "10월";
                    break;
                case 10:
                    ko_month = "11월";
                    break;
                case 11:
                    ko_month = "12월";
                    break;
            }

            holder.hc_text_bottom.setText(ko_day_of_week);
            holder.hc_text_middle.setText(day + "");
            holder.hc_text_top.setText(ko_month);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tv_att_state.setText(list.get(position).getAtt_state());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hc_text_top, hc_text_middle, hc_text_bottom, tv_att_state;
        View hc_selector;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hc_text_top = itemView.findViewById(R.id.hc_text_top);
            hc_text_middle = itemView.findViewById(R.id.hc_text_middle);
            hc_text_bottom = itemView.findViewById(R.id.hc_text_bottom);
            tv_att_state = itemView.findViewById(R.id.tv_att_state);
            hc_selector = itemView.findViewById(R.id.hc_selector);

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
