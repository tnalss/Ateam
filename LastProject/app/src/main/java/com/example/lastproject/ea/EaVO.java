package com.example.lastproject.ea;

import java.io.Serializable;
import java.sql.Date;


public class EaVO implements Serializable {
	private String ea_title, ea_content, ea_status, ea_r_statuas, ea_pop, ea_read, ea_dummy,ea_num, ea_receiver, emp_no, emp_name, ea_file_name, ea_file_path;
	private Date ea_date, ea_cdate, ea_u_date, ea_r_date, ea_a_date;

	public String getEa_file_name() {
		return ea_file_name;
	}

	public void setEa_file_name(String ea_file_name) {
		this.ea_file_name = ea_file_name;
	}

	public String getEa_file_path() {
		return ea_file_path;
	}

	public void setEa_file_path(String ea_file_path) {
		this.ea_file_path = ea_file_path;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEa_title() {
		return ea_title;
	}

	public void setEa_title(String ea_title) {
		this.ea_title = ea_title;
	}

	public String getEa_content() {
		return ea_content;
	}

	public void setEa_content(String ea_content) {
		this.ea_content = ea_content;
	}

	public String getEa_status() {
		return ea_status;
	}

	public void setEa_status(String ea_status) {
		this.ea_status = ea_status;
	}

	public String getEa_r_statuas() {
		return ea_r_statuas;
	}

	public void setEa_r_statuas(String ea_r_statuas) {
		this.ea_r_statuas = ea_r_statuas;
	}

	public String getEa_pop() {
		return ea_pop;
	}

	public void setEa_pop(String ea_pop) {
		this.ea_pop = ea_pop;
	}

	public String getEa_read() {
		return ea_read;
	}

	public void setEa_read(String ea_read) {
		this.ea_read = ea_read;
	}

	public String getEa_dummy() {
		return ea_dummy;
	}

	public void setEa_dummy(String ea_dummy) {
		this.ea_dummy = ea_dummy;
	}

	public String getEa_num() {
		return ea_num;
	}

	public void setEa_num(String ea_num) {
		this.ea_num = ea_num;
	}

	public String getEa_receiver() {
		return ea_receiver;
	}

	public void setEa_receiver(String ea_receiver) {
		this.ea_receiver = ea_receiver;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public Date getEa_date() {
		return ea_date;
	}

	public void setEa_date(Date ea_date) {
		this.ea_date = ea_date;
	}

	public Date getEa_cdate() {
		return ea_cdate;
	}

	public void setEa_cdate(Date ea_cdate) {
		this.ea_cdate = ea_cdate;
	}

	public Date getEa_u_date() {
		return ea_u_date;
	}

	public void setEa_u_date(Date ea_u_date) {
		this.ea_u_date = ea_u_date;
	}

	public Date getEa_r_date() {
		return ea_r_date;
	}

	public void setEa_r_date(Date ea_r_date) {
		this.ea_r_date = ea_r_date;
	}

	public Date getEa_a_date() {
		return ea_a_date;
	}

	public void setEa_a_date(Date ea_a_date) {
		this.ea_a_date = ea_a_date;
	}
}
