package com.and.middle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
	
	@RequestMapping(value="/dept_insert.sche", produces="text/html;charset=utf-8")
	public void insert_dept_sche(String vo) {
		ScheduleVO each= new Gson().fromJson(vo, ScheduleVO.class);
		sql.insert("sche.dept_insert",each);
		
	}
	@RequestMapping(value="/company_insert.sche", produces="text/html;charset=utf-8")
	public void insert_company_sche(String vo) {
		ScheduleVO each= new Gson().fromJson(vo, ScheduleVO.class);
		sql.insert("sche.company_insert",each);
		
	}	
	@RequestMapping(value="/personal_insert.sche", produces="text/html;charset=utf-8")
	public void insert_personal_sche(String vo) {
		ScheduleVO each= new Gson().fromJson(vo, ScheduleVO.class);
		sql.insert("sche.personal_insert",each);
		
	}	
	
	@RequestMapping(value="/findName.sche", produces="text/html;charset=utf-8")
	public String find_name_sche(String sche_no) {
		
		return sql.selectOne("sche.findName",sche_no);
		
	}	
	
	@RequestMapping("/delete.sche")
	public void delete_sche(String sche_no) {
		sql.delete("sche.delete",sche_no);
	}	
	
	@RequestMapping("/statusDone.sche")
	public void status_done(String sche_no,String status) {
		HashMap<String,String> map = new HashMap<>();
		map.put("sche_no", sche_no);
		map.put("status",status);
		sql.update("sche.done",map);
	}
	
}
