package com.example.lastproject.ea;

import java.io.Serializable;
import java.sql.Date;

public class EaCodeVO implements Serializable {
    private String code_category, code_value, code_value2, creater, code_comments;
    private int code_num;
//    private Date create_date;
    private Date create_date;

	public String getCode_category() {
		return code_category;
	}

	public void setCode_category(String code_category) {
		this.code_category = code_category;
	}

	public String getCode_value() {
		return code_value;
	}

	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}

	public String getCode_value2() {
		return code_value2;
	}

	public void setCode_value2(String code_value2) {
		this.code_value2 = code_value2;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCode_comments() {
		return code_comments;
	}

	public void setCode_comments(String code_comments) {
		this.code_comments = code_comments;
	}

	public int getCode_num() {
		return code_num;
	}

	public void setCode_num(int code_num) {
		this.code_num = code_num;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
}