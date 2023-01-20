package co.kr.jkcompany;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonService;


@Controller
public class ScheduleController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;


	@RequestMapping(value= "/list.sche" , produces="text/html;charset=utf-8")
	public String emp_list(HttpSession session, Model model) {
		
		//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
		session.setAttribute("cate", "sche" );

		sql.selectList("sche.compPeriod");
		return "schedule/list";
	}
	
	


	
}
