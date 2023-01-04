package com.and.middle;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import login.LoginVO;

@RestController
public class LoginController {
	@Autowired private SqlSession sql;
	@RequestMapping(value= "/login", produces="text/html;charset=utf-8")
	public String login(String emp_no, String emp_pw, HttpSession session) {		
		HashMap<String, String> map = new HashMap<String, String>();	
		map.put("emp_no", emp_no);
		map.put("emp_pw", emp_pw);
		LoginVO vo = sql.selectOne("lo.loginInfo", map);
		System.out.println(vo.getEmp_name());
		System.out.println(vo.getEmp_no());
		System.out.println(vo.getEmp_pw());
		System.out.println(vo.getPhone());
		session.setAttribute("loginInfo", vo);
		
		return new Gson().toJson(vo).toString();
	}
}
