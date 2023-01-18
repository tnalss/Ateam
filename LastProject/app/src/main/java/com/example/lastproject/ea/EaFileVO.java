package com.example.lastproject.ea;


import java.io.Serializable;

public class EaFileVO implements Serializable {
	String file_no, ea_num, file_path, file_name, ea_receiver;

	public String getFile_no() {
		return file_no;
	}

	public void setFile_no(String file_no) {
		this.file_no = file_no;
	}

	public String getEa_num() {
		return ea_num;
	}

	public void setEa_num(String ea_num) {
		this.ea_num = ea_num;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getEa_receiver() {
		return ea_receiver;
	}

	public void setEa_receiver(String ea_receiver) {
		this.ea_receiver = ea_receiver;
	}
}
