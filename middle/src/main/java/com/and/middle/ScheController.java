package com.and.middle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import schedule.ScheduleVO;

@RestController
public class ScheController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	
	@RequestMapping(value="/comp.sche", produces="text/html;charset=utf-8")
	public String company_sche_list() {
		List<ScheduleVO> list = sql.selectList("sche.compSche");
		
		return new Gson().toJson(list).toString();
	}
	
	
	@RequestMapping(value="/compPeriod.sche", produces="text/html;charset=utf-8")
	public String company_sche_list_period() {
		List<ScheduleVO> list = sql.selectList("sche.compPeriod");
		
		return new Gson().toJson(list).toString();
	}
	
	
	@RequestMapping(value="/deptPeriod.sche", produces="text/html;charset=utf-8")
	public String dept_sche_list_period(String emp_no) {
		//사원번호 해당 부서 조회
		String dept_code = sql.selectOne("code.whatDepCode",emp_no);
		//해당부서 쿼리문 날리기.
		List<ScheduleVO> list = sql.selectList("sche.deptPeriod",dept_code);
		return new Gson().toJson(list).toString();
	}
	@RequestMapping(value="/peronalPeriod.sche", produces="text/html;charset=utf-8")
	public String personal_sche_list_period(String emp_no) {
		
		//해당쿼리문 날리기.
		List<ScheduleVO> list = sql.selectList("sche.personalPeriod",emp_no);
		return new Gson().toJson(list).toString();
	}
	
	public String today() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		String today = sdf.format(date).toString();
		return today;
	}
	
	
}
