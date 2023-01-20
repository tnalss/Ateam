package co.kr.jkcompany;

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

import attend.AttendVO;
import common.CommonService;


@Controller
public class AttendController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	
	// 전체사원의 전체 날짜 출퇴근 상태 조회
	@RequestMapping(value = "/admin_attend.at", produces = "text/html;charset=utf-8")
	public String admin_attend(HttpSession session, Model model) {
		List<AttendVO> list = sql.selectList("at.admin_attend");
		model.addAttribute("admin_attend",list);
		return "attend/admin_attend";
	}
	//전체사원의 선택 날짜 출퇴근 상태 조회 
	@RequestMapping(value = "/ad_date.at", produces = "text/html;charset=utf-8")
	public String ad_date(HttpSession session, Model model) {
		List<AttendVO> list = sql.selectList("at.ad_date");
		model.addAttribute("ad_date",list);
		return "attend/ad_date";
	}
	

	
	

	//내 출퇴근 조회
	@RequestMapping(value = "/myattend", produces = "text/html;charset=utf-8")
	public String myattend(HttpSession session, Model model) {
		session.removeAttribute("category");
		return "attend/my_attend";
	}

	//내 휴가연차 조회 
	@RequestMapping(value = "/myholiday", produces = "text/html;charset=utf-8")
	public String myholiday(HttpSession session, Model model) {
		session.removeAttribute("category");
		return "attend/my_holiday";
	}

	
	
	

	

	/*

	 * //조회하고 출력하는 예제
	 * 
	 * @RequestMapping(value= "/list.emp" , produces="text/html;charset=utf-8")
	 * public String emp_list(HttpSession session, Model model) {
	 * 
	 * //각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요! session.setAttribute("cate", "emp" );
	 * 
	 * List<EmployeeVO> list = sql.selectList("emp.list");
	 * 
	 * //조회해온 값을 모델로 list라는 곳에 담았습니다. model.addAttribute("list", list);
	 * 
	 * 
	 * //리턴을 통해 employee 폴더에 list.jsp 를 찾아갑니다. return "employee/list"; } 
	 * 
	 * 
	 * //로그인한 사원의
	 * 출근 처리
	 * 
	 * 
	 * @RequestMapping(value="/attend_on.at", produces="text/html;charset=utf-8")
	 * public String attend_on(String emp_no) { sql.update("at.attend_on",emp_no);
	 * AttendVO vo = sql.selectOne("at.emp_today",emp_no); return new
	 * Gson().toJson(vo).toString(); }
	 * 
	 * 
	 * //로그인한 사원의 퇴근 처리
	 * 
	 * @RequestMapping(value="/attend_off.at", produces="text/html;charset=utf-8")
	 * public String attend_off(String emp_no) { sql.update("at.attend_off",emp_no);
	 * AttendVO vo = sql.selectOne("at.emp_today",emp_no); return new
	 * Gson().toJson(vo).toString(); }
	 * 
	 * 
	 * //로그인한 사원의 오늘 날짜 출퇴근 상태 조회
	 * 
	 * @RequestMapping(value="/attend_today.at", produces="text/html;charset=utf-8")
	 * public String attend_today(String emp_no) { AttendVO vo =
	 * sql.selectOne("at.emp_today",emp_no); return new
	 * Gson().toJson(vo).toString(); }
	 * 
	 * 
	 * // 로그인한 사원의 전체 출퇴근 상태조회
	 * 
	 * @RequestMapping(value="/list_emp_since.at",
	 * produces="text/html;charset=utf-8") public String list_emp_since(String
	 * emp_no) { Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd ").create(); List<AttendVO> list =
	 * sql.selectList("at.list_emp_since",emp_no); return
	 * gson.toJson(list).toString() ; }
	 * 
	 * 
	 * // 일주일 출퇴근 상태조회
	 * 
	 * @RequestMapping(value="/list_7days.at", produces="text/html;charset=utf-8")
	 * public String list_7days(String emp_no) { List<AttendVO> list =
	 * sql.selectList("at.list_7days",emp_no); return new
	 * Gson().toJson(list).toString(); }
	 * 
	 * /////////////////////관리자 모드로 봅니다
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //다합쳐서 조회
	 * 
	 * @RequestMapping(value="/worktime_all.at", produces="text/html;charset=utf-8")
	 * public String worktime_all(String param) { AttendVO vo = new
	 * Gson().fromJson(param, AttendVO.class); List<AttendVO>list =
	 * sql.selectList("at.worktime_all",vo); return new
	 * Gson().toJson(list).toString();
	 * 
	 * }
	 * 
	 */

}
