package com.example.lastproject.calendar;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lastproject.R;

public class ScheduleDialog extends Dialog {
    TextView tv_sche_title;
    public ScheduleDialog(@NonNull Context context, ScheduleVO vo) {
        super(context);
        setContentView(R.layout.item_dialog_schedule);

        tv_sche_title = findViewById(R.id.tv_sche_title);

        tv_sche_title.setText(vo.getSche_title());


    }
}
