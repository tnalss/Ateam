package com.example.lastproject.attend;

public class AttCodeVO {
    private String code_value;
    private String code_comments;
    private String code_category;
    private int code_num;



    public String getCode_category() {
        return code_category;
    }

    public void setCode_category(String code_category) {
        this.code_category = code_category;
    }

    public int getCode_num() {
        return code_num;
    }

    public void setCode_name(int code_num) {
        this.code_num = code_num;
    }

    public String getCode_value() {
        return code_value;
    }

    public void setCode_value(String code_value) {
        this.code_value = code_value;
    }

    public String getCode_comments() {
        return code_comments;
    }

    public void setCode_comments(String code_comments) {
        this.code_comments = code_comments;
    }
}