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
import employee.EmployeeVO;
import login.LoginVO;

@Controller
public class AttendController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;

	// 관리자 모드 _ 페이지 처리
	public AttendPageVO attend_list(AttendPageVO page) {
		page.setTotalList(sql.selectOne("at.total", page));
		page.setList(sql.selectList("at.admin_attend", page));
		return page;
	}

	// 관리자 모드_ 근태관리화면_전체 가져오기
	@RequestMapping(value = "/admin_attend.at", produces = "text/html;charset=utf-8")
	public String admin_attend(HttpSession session, Model model, AttendPageVO page) {
		AttendPageVO vo = attend_list(page);
		model.addAttribute("page", vo);	


		model.addAttribute("branches", sql.selectList("emp.codeList", 'B'));
		model.addAttribute("departments", sql.selectList("emp.codeList", 'D'));
		model.addAttribute("ranks", sql.selectList("emp.codeList", 'R'));

		
		return "attend/admin_attend";
	}


	// 내 출퇴근 조회
	@RequestMapping(value = "/myattend", produces = "text/html;charset=utf-8")
	public String myattend(HttpSession session, Model model) {
		session.setAttribute("cate", "attend");
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		HashMap<String, String> tempMap = new HashMap<String, String>();
		AttendVO today = sql.selectOne("at.emp_today", vo.getEmp_no());
		List<AttendVO> list = sql.selectList("at.list_7days", vo.getEmp_no());
		int code = sql.selectOne("at.codeW4", vo.getEmp_no());
		int code2 = sql.selectOne("at.codeW3", vo.getEmp_no());
		int code3 = sql.selectOne("at.codeW2", vo.getEmp_no());

		// model.addAttribute("loginInfo",vo);
		model.addAttribute("today", today);
		model.addAttribute("list", list);
		model.addAttribute("code", code);
		model.addAttribute("code2", code2);
		model.addAttribute("code3", code3);
		return "attend/my_attend";
	}

	@RequestMapping(value = "/my_attend_a.at", produces = "text/html;charset=utf-8")
	public String my_attend_a(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.code4list", vo.getEmp_no());
		// List<AttendVO> since = sql.selectList("at.list_7days",vo.getEmp_no());
		model.addAttribute("list", list);
		// model.addAttribute("since",since);
		return "attend/my_attend_a";
	}

	@RequestMapping(value = "/my_attend_late.at", produces = "text/html;charset=utf-8")
	public String my_attend_late(HttpSession session, Model model, AttendPageVO page) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.code3list", vo.getEmp_no());
		// List<AttendVO> since = sql.selectList("at.list_7days",vo.getEmp_no());
		model.addAttribute("list", list);
		// model.addAttribute("since",since);
		return "attend/my_attend_late";
	}

	@RequestMapping(value = "/my_attend_n.at", produces = "text/html;charset=utf-8")
	public String my_attend_n(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.code2list", vo.getEmp_no());
		// List<AttendVO> since = sql.selectList("at.list_7days",vo.getEmp_no());
		model.addAttribute("list", list);
		// model.addAttribute("since",since);
		return "attend/my_attend_n";
	}

	@RequestMapping(value = "/my_attend_o.at", produces = "text/html;charset=utf-8")
	public String my_attend_o(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.codeotherslist", vo.getEmp_no());
		// List<AttendVO> since = sql.selectList("at.list_7days",vo.getEmp_no());
		model.addAttribute("list", list);
		// model.addAttribute("since",since);
		return "attend/my_attend_o";
	}

	@RequestMapping(value = "/my_attend_edit.at", produces = "text/html;charset=utf-8")
	public String my_attend_edit(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		model.addAttribute("att_code", sql.selectList("at.code_list", 'A'));
		return "attend/my_attend_edit";
	}

	// 출근하기
	@RequestMapping(value = "/attend_on.at", produces = "text/html;charset=utf-8")
	public String attend_on(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		sql.insert("at.attend_on", vo.getEmp_no());
		return "redirect:myattend";
	}

	// 퇴근하기
	@RequestMapping(value = "/attend_off.at", produces = "text/html;charset=utf-8")
	public String attend_off(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		sql.update("at.attend_off", vo.getEmp_no());
		return "redirect:myattend";
	}

	// 출근 취소
	@RequestMapping(value = "/on_cancel.at", produces = "text/html;charset=utf-8")
	public String on_cancel(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		sql.delete("at.on_cancel", vo.getEmp_no());
		return "redirect:myattend";
	}

	// 퇴근취소
	@RequestMapping(value = "/off_cancel.at", produces = "text/html;charset=utf-8")
	public String off_cancel(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		sql.update("off_cancel", vo.getEmp_no());
		return "redirect:myattend";
	}

}
