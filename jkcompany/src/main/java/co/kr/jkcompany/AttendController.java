package co.kr.jkcompany;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import attend.AttendPageVO;
import attend.AttendVO;
import common.CommonService;
import employee.EmployeePageVO;
import login.LoginVO;


@Controller
public class AttendController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	
	// 관리자 모드_ 근태관리화면_전체 가져오기 
	@RequestMapping(value = "/admin_attend.at", produces = "text/html;charset=utf-8")
	public String admin_attend(HttpSession session, Model model,AttendPageVO page) {
		//List<AttendVO> list = sql.selectList("at.admin_attend");
		AttendPageVO vo = attend_list(page);
		model.addAttribute("page", vo );
		//model.addAttribute("admin_attend",list);
		return "attend/admin_attend";
	}	
	
	// 페이지 처리
		public AttendPageVO attend_list(AttendPageVO page) {
			page.setTotalList( sql.selectOne("at.total", page) ); 
			page.setList( sql.selectList("at.admin_attend", page) );
			return page;
		}

	

	//내 출퇴근 조회
	@RequestMapping(value = "/myattend", produces = "text/html;charset=utf-8")
	public String myattend(HttpSession session, Model model) { 
		session.setAttribute("cate","attend");
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");		
		HashMap<String, String> tempMap = new HashMap<String, String>();
		AttendVO today = sql.selectOne("at.emp_today", vo.getEmp_no());				
		List<AttendVO> list = sql.selectList("at.list_7days",vo.getEmp_no());
		int code = sql.selectOne("at.codeW4",vo.getEmp_no());
		int code2= sql.selectOne("at.codeW3nW7",vo.getEmp_no());
		model.addAttribute("today",today);
		model.addAttribute("list",list);
		model.addAttribute("code",code);
		model.addAttribute("code2",code2);
		return "attend/my_attend";
	}

	
	
	
	  
	  

	

}
