package com.example.lastproject.notice;

import java.io.Serializable;
import java.util.Date;

public class NoticeVO implements Serializable {
    private int board_no, emp_no, board_hits, img_no_img, reply_count;
    private String board_title, board_cate, board_content, write_date, emp_name;

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public int getBoard_hits() {
        return board_hits;
    }

    public void setBoard_hits(int board_hits) {
        this.board_hits = board_hits;
    }

    public int getImg_no_img() {
        return img_no_img;
    }

    public void setImg_no_img(int img_no_img) {
        this.img_no_img = img_no_img;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_cate() {
        return board_cate;
    }

    public void setBoard_cate(String board_cate) {
        this.board_cate = board_cate;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
}
