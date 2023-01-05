package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ea.EaCodeVO;

@RestController
public class AttendController {
	@Autowired @Qualifier("hanul") SqlSession sql;
	@RequestMapping(value="/attend.at")
	public void attend(int emp_no) {
		
		sql.update("at.attend",emp_no);

		
	}
	
}
