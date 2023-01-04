package com.and.middle;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import login.LoginVO;

@RestController
public class UtilController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	//이 부분은 근태부분과 합쳐야합니다.
	//출퇴근 여부를 판단하여 출근시 출근시간을 알려주는 곳 입니다.
	@RequestMapping("/attendOrNot")
	public void attendOrNot(int emp_no) {
		LoginVO vo = sql.selectOne("util.attendOrNot",emp_no);
		//System.out.println(vo.getAtt_code());
		
	}
}
