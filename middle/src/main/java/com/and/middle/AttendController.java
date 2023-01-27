package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Attend.AttendAdminVO;
import Attend.AttendVO;


@RestController
public class AttendController {
	@Autowired 
	@Qualifier("hanul")
	SqlSession sql;
	
	//로그인한 사원의 출근 처리
		@RequestMapping(value="/attend_on.at", produces="text/html;charset=utf-8")
		public String attend_on(String emp_no) {
			sql.insert("at.attend_on",emp_no);
			AttendVO vo = sql.selectOne("at.emp_today",emp_no);
			return new Gson().toJson(vo).toString();		
		}		
	
	//로그인한 사원의 퇴근 처리
	@RequestMapping(value="/attend_off.at", produces="text/html;charset=utf-8")
	public String attend_off(String emp_no) {
		sql.update("at.attend_off",emp_no);
		AttendVO vo = sql.selectOne("at.emp_today",emp_no);
		return new Gson().toJson(vo).toString();
	}
	
	
	//로그인한 사원의 오늘 날짜 출퇴근 상태 조회 
	@RequestMapping(value="/attend_today.at", produces="text/html;charset=utf-8")
	public String attend_today(String emp_no) {
		AttendVO vo = sql.selectOne("at.emp_today",emp_no);
		return new Gson().toJson(vo).toString();
	}	
	

	// 로그인한 사원의  전체 출퇴근 상태조회 
	@RequestMapping(value="/list_emp_since.at", produces="text/html;charset=utf-8")
	public String list_emp_since(String emp_no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd ").create();
		List<AttendVO> list = sql.selectList("at.list_emp_since",emp_no);
		return gson.toJson(list).toString() ;
	}


	// 일주일 출퇴근 상태조회 
	@RequestMapping(value="/list_7days.at", produces="text/html;charset=utf-8")
	public String list_7days(String emp_no) {
		List<AttendVO> list = sql.selectList("at.list_7days",emp_no);
		return new Gson().toJson(list).toString();
	}
	
	/////////////////////관리자 모드로 봅니다
	//전체사원의 전체 날짜 출퇴근 상태 조회 
	@RequestMapping(value="/all.at", produces="text/html;charset=utf-8")
	public String all() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd ").create();
		List<AttendAdminVO> list = sql.selectList("at.all");
		return gson.toJson(list).toString() ;
	}
	
	
	//전체사원의 하루동안의 근무 시간 총합  조회 
	@RequestMapping(value="/worktime_day.at", produces="text/html;charset=utf-8")
	public String worktime_day() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd ").create();
		List<AttendAdminVO> list = sql.selectList("at.worktime_day");
		return gson.toJson(list).toString() ;
	}

	
	
	//다합쳐서 조회 
	@RequestMapping(value="/worktime_all.at", produces="text/html;charset=utf-8")
	public String worktime_all(String param) {
		AttendAdminVO vo = new Gson().fromJson(param, AttendAdminVO.class);
		List<AttendAdminVO>list = sql.selectList("at.worktime_all",vo);
		return new Gson().toJson(list).toString();
		
	}
	
	
	
	
}
