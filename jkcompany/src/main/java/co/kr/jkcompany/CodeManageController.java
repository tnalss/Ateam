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
import org.springframework.web.bind.annotation.ResponseBody;

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
		session.setAttribute("category", "admin" );
		List<CodeVO> list = sql.selectList("code.topCodeList");
		model.addAttribute("list",list);
		return "codeManage/list";
	}
	

	// 해당 하위 코드 조회
	@RequestMapping(value= "/bottomCodeList.code" , produces="text/html;charset=utf-8")
	public String bottomCodeList(String code, Model model) {
		//코드로 하위 코드 검색
		List<CodeVO> list = sql.selectList("code.bottomCodeList",code);
		model.addAttribute("top",sql.selectOne("code.topCodeInfo",code));
		
		model.addAttribute("list",list);
		return "codeManage/bottomList";
	}
	
	
	//상위 코드 삭제
	@RequestMapping(value= "/deleteTop.code" , produces="text/html;charset=utf-8")
	public String deleteTop(String id) {
		
		sql.delete("code.deleteBottom",id);
		sql.delete("code.deleteTop",id);

		return "redirect:list.code";
	}
	
	//상위 코드 수정
	@ResponseBody
	@RequestMapping("/updateTop.code")
	public boolean updateTop(CodeVO vo) {
		int test = sql.update("code.updateTop",vo);
		return test==1 ? true : false;
	}
	
	
	// 상위 코드 추가
	//코드 전체 조회
	@ResponseBody
	@RequestMapping("/addTop.code")
	public boolean addTop(CodeVO vo) {
		int test = sql.selectOne("code.hasTop",vo);
		if(test==1) {
			return false;
		}
		sql.insert("code.insertTop",vo);
		
		return true;
	}
	
	
	//하위코드 추가
	@ResponseBody
	@RequestMapping("/addBottom.code")
	public boolean addBottom(CodeVO vo) {
		int test = sql.selectOne("code.hasBottom",vo);
		if(test==1) {
			return false;
		}
		sql.insert("code.insertBottom",vo);
		
		return true;
	}

	//하위 코드 삭제
	@RequestMapping(value= "/deleteBottom.code" , produces="text/html;charset=utf-8")
	public String deleteBottom(String code_category, String code_num) {
		HashMap<String,String> map = new HashMap<>();
		map.put("code_category", code_category);
		map.put("code_num", code_num);
		sql.delete("code.deleteBottomOne",map);

		return "redirect:bottomCodeList.code?code="+code_category;
	}
	
}
