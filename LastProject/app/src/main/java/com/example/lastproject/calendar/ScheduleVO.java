package com.example.lastproject.calendar;

import java.io.Serializable;

public class ScheduleVO implements Serializable {
    private String sche_no,emp_no,sche_type,sche_title,sche_content;
    private String sche_red,sche_start,sche_end,sche_status;

    public String getSche_no() {
        return sche_no;
    }

    public void setSche_no(String sche_no) {
        this.sche_no = sche_no;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getSche_type() {
        return sche_type;
    }

    public void setSche_type(String sche_type) {
        this.sche_type = sche_type;
    }

    public String getSche_title() {
        return sche_title;
    }

    public void setSche_title(String sche_title) {
        this.sche_title = sche_title;
    }

    public String getSche_content() {
        return sche_content;
    }

    public void setSche_content(String sche_content) {
        this.sche_content = sche_content;
    }

    public String getSche_red() {
        return sche_red;
    }

    public void setSche_red(String sche_red) {
        this.sche_red = sche_red;
    }

    public String getSche_start() {
        return sche_start;
    }

    public void setSche_start(String sche_start) {
        this.sche_start = sche_start;
    }

    public String getSche_end() {
        return sche_end;
    }

    public void setSche_end(String sche_end) {
        this.sche_end = sche_end;
    }

    public String getSche_status() {
        return sche_status;
    }

    public void setSche_status(String sche_status) {
        this.sche_status = sche_status;
    }
}
