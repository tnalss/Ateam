package com.example.lastproject.attend;

import java.sql.Date;
import java.text.DateFormat;

public class AttendVO {
    public AttendVO(String att_state) {
        this.att_state = att_state;
    }

    String emp_name;
    String attend_date;
    String attend_on,attend_off;
    String att_code;
    String att_state;
    int emp_no;


    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getAttend_date() {
        return attend_date;
    }

    public void setAttend_date(String attend_date) {
        this.attend_date = attend_date;
    }

    public String getAttend_on() {
        return attend_on;
    }

    public void setAttend_on(String attend_on) {
        this.attend_on = attend_on;
    }

    public String getAttend_off() {
        return attend_off;
    }

    public void setAttend_off(String attend_off) {
        this.attend_off = attend_off;
    }

    public String getAtt_code() {
        return att_code;
    }

    public void setAtt_code(String att_code) {
        this.att_code = att_code;
    }

    public String getAtt_state() {
        return att_state;
    }

    public void setAtt_state(String att_state) {
        this.att_state = att_state;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }
}
