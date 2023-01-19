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
public class CodeManageController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	//코드 전체 조회
	@RequestMapping(value= "/simpleList.code" , produces="text/html;charset=utf-8")
	public String simpleList(HttpSession session, Model model) {
		
		//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
		session.setAttribute("cate", "code" );
		
		return "codeManage/list";
	}
	
}
