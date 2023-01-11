package com.and.middle;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import Attend.AttendVO;

@RestController
public class AlController {
	@Autowired 
	@Qualifier("hanul")
	SqlSession sql;
	
	
	/*
	 @RequestMapping(value="/attend_on.at", produces="text/html;charset=utf-8")
	  public String attend_on(String emp_no) { sql.insert("at.attend_on",emp_no);
	  AttendVO vo = sql.selectOne("at.emp_today",emp_no); return new
	  Gson().toJson(vo).toString(); }
	 */
	
}
