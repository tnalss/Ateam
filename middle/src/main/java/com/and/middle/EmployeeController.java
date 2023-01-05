package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

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
}
