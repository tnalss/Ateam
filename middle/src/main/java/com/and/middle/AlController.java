package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import Attend.AttendVO;
import al.AlVO;

@RestController
public class AlController {
	@Autowired 
	@Qualifier("hanul")
	SqlSession sql;
	
	//로그인한 사원의 휴가및연차 상태목록 조회
	@RequestMapping(value="/al_list.al" , produces="text/html;charset=utf-8" )
	public String al_list(String emp_no) {
		List<AlVO> list = sql.selectList("al.al_list",emp_no);
		return new Gson().toJson(list).toString();
	}
	
	//로그인한 사원의 휴가 신청  처리
	@RequestMapping(value="/al_v_a.al", produces="text/html;charset=utf-8")
	public String al_v0(String emp_no) {		
		sql.insert("al.al_v_a",emp_no);			
		return "1";
		
	}		


		
		
	
	
}

	

