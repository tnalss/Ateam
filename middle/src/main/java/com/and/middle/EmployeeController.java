package com.and.middle;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import common.CommonService;
import employee.EmployeeVO;


@RestController
public class EmployeeController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	
	@RequestMapping(value= "/list.emp" , produces="text/html;charset=utf-8")
	public String attendOrNot() {
		List<EmployeeVO> list = sql.selectList("emp.list");
		return new Gson().toJson(list).toString();
	}
	
	
	
	@RequestMapping(value= "/fire.emp" , produces="text/html;charset=utf-8")
	public void fire_emp(String emp_no) {
		sql.update("emp.fire",emp_no);
	}
	
	@RequestMapping(value= "/info.emp" , produces="text/html;charset=utf-8")
	public String emp_info(String emp_no) {
		EmployeeVO vo = sql.selectOne("emp.info",emp_no);
		return new Gson().toJson(vo).toString();
	}
	
	
	//회원가입
	@RequestMapping(value="/insert.emp" , produces="text/html;charset=utf-8")
	public String insert_emp(String param) {
		EmployeeVO vo = new Gson().fromJson(param, EmployeeVO.class);
		
		String emp_pw = new CommonService().rand6num();
		vo.setEmp_pw(emp_pw);
		sql.insert("emp.insert",vo);
				
		//새로운사번 알려줘야됨
		HashMap<String, String> map = new HashMap<>();
		map.put("emp_name", vo.getEmp_name());
		map.put("email", vo.getEmail());
		////////////////////////////////////////////////이메일로 비밀번호 보내주는 처리 필요		
		String emp_no = sql.selectOne("emp.emp_no",map);
		
		//System.out.println(vo2.getAdmin());
		//이름에해당하는 코드 찾아주는 처리 필요
		
		String branch_code = sql.selectOne("code.whatCode",vo.getBranch_name());
		String dept_code = sql.selectOne("code.whatCode",vo.getDepartment_name());
		String rank_code = sql.selectOne("code.whatCode",vo.getRank_name());
		HashMap<String, String> codemap = new HashMap<>();
		codemap.put("branch_code", branch_code);
		codemap.put("dept_code", dept_code);
		codemap.put("rank_code", rank_code);
		codemap.put("emp_no", emp_no);
		sql.update("emp.codeInsert",codemap);
		
		EmployeeVO vo2 = sql.selectOne("emp.search_emp",map);
		
		return new Gson().toJson(vo2).toString();
	}
	
	//퇴사자?어드민? 조회
	@RequestMapping("/adminCode.emp")
	public String adminCode(String emp_no) {
		return sql.selectOne("emp.adminCode",emp_no);
	}
}
