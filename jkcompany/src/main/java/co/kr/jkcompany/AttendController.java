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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import attend.AlPageVO;
import attend.AttendPageVO;
import attend.AttendVO;
import common.CommonService;
import login.LoginVO;

@Controller
public class AttendController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;	
	

	// 페이지 처리
	public AttendPageVO attend_list(AttendPageVO page) {
		page.setTotalList(sql.selectOne("at.total", page));
		page.setList(sql.selectList("at.admin_attend", page));		
		return page;
	}
	// 페이지 처리
	public AlPageVO al_list(AlPageVO page_al) {
		page_al.setAl_list(sql.selectList("at.al_list",page_al));
		return page_al;
	}
	

	
	@RequestMapping("/attend_info.at")
	public String attend_info (String id, Model model ) {
		List<AttendVO> vo = sql.selectList("at.attend_info", id);
		model.addAttribute("emp_name",vo.get(0).getEmp_name());
		model.addAttribute("info",vo);
		model.addAttribute("attend", sql.selectList("emp.codeList", 'W'));		
		return "attend/attend_info";
	}


	@ResponseBody @RequestMapping("/attend_state_update.at")
	public boolean attend_state_update (AttendVO vo ) {
		return sql.update("at.updateAttendCode", vo) ==1 ? true : false;
	}
		
		

		@RequestMapping("/al_info.at")
		public String al_info(String id, Model model) {
			List<AttendVO> vo = sql.selectList("at.al_info", id);
			model.addAttribute("info",vo);			
			model.addAttribute("al", sql.selectList("emp.codeList", 'L'));		
			return "attend/al_info";
		}
		
		@ResponseBody @RequestMapping("/updateAlCode.at")
		public boolean updateAlCode (AttendVO vo ) {
			return sql.update("at.updateAlCode", vo) >= 1 ? true : false;
		}
			
		
		
		
	
	
	// 관리자 모드_ 근태관리화면_전체 가져오기
	@RequestMapping(value = "/admin_attend.at", produces = "text/html;charset=utf-8")
	public String admin_attend(HttpSession session, Model model, AttendPageVO page, AlPageVO page_al , @RequestParam(value = "pageCategory", defaultValue="1") int pageCategory) {
		AttendPageVO vo = attend_list(page);
		AlPageVO vo2 = al_list(page_al);
		int countV0 = sql.selectOne("at.countV0");
		int countV1 = sql.selectOne("at.countV1");
		int countOthers = sql.selectOne("at.countOthers");
		
		
		model.addAttribute("page", vo);
		model.addAttribute("branches", sql.selectList("emp.codeList", 'B'));
		model.addAttribute("departments", sql.selectList("emp.codeList", 'D'));
		model.addAttribute("ranks", sql.selectList("emp.codeList", 'R'));
		
		model.addAttribute("page_al",vo2);
		model.addAttribute("countV0",countV0);
		model.addAttribute("countV1",countV1);
		model.addAttribute("countOthers",countOthers);
		model.addAttribute("pageCategory",pageCategory);
	

		return "attend/admin_attend";
	}

	// 내 출퇴근 조회
	@RequestMapping(value = "/myattend", produces = "text/html;charset=utf-8")
	public String myattend(HttpSession session, Model model) {
		session.setAttribute("cate", "attend");
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");

		if(vo ==null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			model.addAttribute("url", "login");
			return "ea/alert";
		}
		
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
		List<AttendVO> since = sql.selectList("at.list_7days", vo.getEmp_no());
		model.addAttribute("list", list);
		model.addAttribute("since", since);
		return "attend/my_attend_a";
	}

	@RequestMapping(value = "/my_attend_late.at", produces = "text/html;charset=utf-8")
	public String my_attend_late(HttpSession session, Model model, AttendPageVO page) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.code3list", vo.getEmp_no());
		List<AttendVO> since = sql.selectList("at.list_7days", vo.getEmp_no());
		model.addAttribute("list", list);
		model.addAttribute("since", since);
		return "attend/my_attend_late";
	}

	@RequestMapping(value = "/my_attend_n.at", produces = "text/html;charset=utf-8")
	public String my_attend_n(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.code2list", vo.getEmp_no());
		List<AttendVO> since = sql.selectList("at.list_7days", vo.getEmp_no());
		model.addAttribute("list", list);
		model.addAttribute("since", since);
		return "attend/my_attend_n";
	}

	@RequestMapping(value = "/my_attend_o.at", produces = "text/html;charset=utf-8")
	public String my_attend_o(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<AttendVO> list = sql.selectList("at.codeotherslist", vo.getEmp_no());
		List<AttendVO> since = sql.selectList("at.list_7days", vo.getEmp_no());
		model.addAttribute("list", list);
		model.addAttribute("since", since);
		return "attend/my_attend_o";
	}

	@RequestMapping(value = "/my_attend_edit.at", produces = "text/html;charset=utf-8")
	public String my_attend_edit(HttpSession session, Model model) {
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		// model.addAttribute("status", status);
		
		model.addAttribute("al", sql.selectList("emp.codeList", 'V'));
		return "attend/my_attend_edit";
	}

	@ResponseBody
	@RequestMapping(value = "/attend_date.at")
	public String attend_date(String emp_no, String attend_date) {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("emp_no", emp_no);
		map.put("attend_date", attend_date);
		
		AttendVO status = sql.selectOne("at.attend_list_date", map);
		return new Gson().toJson(status);

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

	// 수정 신청하기
	@RequestMapping(value = "/edit_apply.at")
	public String edit_apply(HttpSession session, Model model, String emp_no, String date, String al_type,
			String message) {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("emp_no", emp_no);
		map.put("al_start_date", date);	
		map.put("al_code", al_type);
		map.put("al_reason", message);
		
		sql.insert("at.edit_apply",map);

		return "redirect:myattend";
	}
	
	// 수정 취소하기
		@RequestMapping(value = "/edit_apply_cancel.at", produces = "text/html;charset=utf-8")
		public String edit_apply_cancel(HttpSession session, Model model, String emp_no, String date, String al_type,
				String message) {
			
			return "redirect:my_attend_edit.at";
		}
}
