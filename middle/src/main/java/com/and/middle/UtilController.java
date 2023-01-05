package com.and.middle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import login.LoginVO;

@RestController
public class UtilController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	//이 부분은 근태부분과 합쳐야합니다.
	//출퇴근 여부를 판단하여 출근시 출근시간을 알려주는 곳 입니다.
	@RequestMapping("/attendOrNot")
	public String attendOrNot(String emp_no) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		
		HashMap<String,String> map = new HashMap<>();
		map.put("emp_no", emp_no);
		map.put("date", sdf.format(date).toString());
		
		LoginVO vo = sql.selectOne("util.attendOrNot",map);
		//System.out.println(vo.getAtt_code());
		
		return new Gson().toJson(vo).toString();
		
	}
}
