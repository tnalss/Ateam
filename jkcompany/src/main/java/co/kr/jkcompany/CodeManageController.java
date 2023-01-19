package co.kr.jkcompany;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import code.CodeVO;
import common.CommonService;

@Controller
public class CodeManageController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	//코드 전체 조회
	@RequestMapping(value= "/list.code" , produces="text/html;charset=utf-8")
	public String simpleList(HttpSession session, Model model) {
		//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
		session.setAttribute("cate", "code" );
		List<CodeVO> list = sql.selectList("code.topCodeList");
		model.addAttribute("list",list);
		return "codeManage/list";
	}
	

	// 해당 하위 코드 조회
	@RequestMapping(value= "/bottomCodeList.code" , produces="text/html;charset=utf-8")
	public String bottomCodeList(String code, Model model) {
		//코드로 하위 코드 검색
		List<CodeVO> list = sql.selectList("code.bottomCodeList",code);
		model.addAttribute("list",list);
		return "codeManage/bottomList";
	}
	
}
