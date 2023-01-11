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

import common.CommonService;
import common.SimpleCode;
import employee.EmployeeVO;
import login.LoginVO;

@RestController
public class UtilController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	//이 메소드는 근태부분과 합쳐야합니다.
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
	
	@RequestMapping("/attendString")
	public String attendString(String emp_no) {
		String status="";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		HashMap<String,String> map = new HashMap<>();
		map.put("emp_no", emp_no);
		map.put("date", sdf.format(date).toString());
		status = sql.selectOne("util.attendString",map);
		
		return status;
	}
	
	@RequestMapping(value = "/codeList.cm",produces="text/html;charset=utf-8")
	public String codeList(String top_code) {
		List<SimpleCode> list = sql.selectList("code.simpleList",top_code);
				
		return new Gson().toJson(list).toString();
	}
	
	@RequestMapping(value="/tempPW.cm", produces="text/html;charset=utf-8")
	public void send_temp_pw(String vo) {
		EmployeeVO info = new Gson().fromJson(vo, EmployeeVO.class);
		String pw = common.rand6num();
		info.setEmp_pw(pw);
		
		common.sendPassword(info);
		
		
		sql.update("util.tempPW",info);

	}
	

}
