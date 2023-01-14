package com.and.middle;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import code.CodeVO;
import common.CommonService;

@RestController
public class CodeController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	//회수함에서 상신 업데이트
	@RequestMapping(value="/topCodeList.cd", produces="text/html;charset=utf-8")
	public String topCodeList() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<CodeVO> list = sql.selectList("code.topCodeList");
		return gson.toJson(list);
	}
	
	//키워드 검색
	@RequestMapping(value="/findBy.cd", produces="text/html;charset=utf-8")
	public String findBy(String keyword) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<CodeVO> list = sql.selectList("code.findBy",keyword);
		return gson.toJson(list);
	}
	
	//하위코드 검색
	@RequestMapping(value="/findByCate.cd", produces="text/html;charset=utf-8")
	public String findByCate(String keyword) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<CodeVO> list = sql.selectList("code.findByCate",keyword);
		return gson.toJson(list);
	}
		
		
		//상위코드 생성
	@RequestMapping(value="/newTopCode.cd", produces="text/html;charset=utf-8")
	public String newTopCode(String top_code, String code_value, String emp_no) {
		int temp = sql.selectOne("code.findTopByCate",top_code);
			
		if(temp != 0 ) {
			return "false";
		} else {
			HashMap<String, String> map = new HashMap<>();
			map.put("top_code", top_code);
			map.put("code_value", code_value);
			map.put("emp_no", emp_no);
			sql.insert("code.newTopCode",map);
			return "true";
		}

	}
	
	//상위코드 삭제
	@RequestMapping(value="/deleteTopCode.cd", produces="text/html;charset=utf-8")
	public void deleteTopCode(String code_category) {
		//하위코드삭제
		sql.delete("code.deleteBottomCodes",code_category);
		//상위코드 삭제
		sql.delete("code.deleteTopCode",code_category);
		
	}
	
	//상위코드 변경
	@RequestMapping(value="/updateTopCode.cd", produces="text/html;charset=utf-8")
	public void updateTopCode(String top_code, String code_value, String emp_no) {

		HashMap<String, String> map = new HashMap<>();
		map.put("top_code", top_code);
		map.put("code_value", code_value);
		map.put("emp_no", emp_no);
		sql.update("code.updateTopCode",map);

	}
	
	
	
	//하위코드 생성
@RequestMapping(value="/newBottomCode.cd", produces="text/html;charset=utf-8")
public String newBottomCode(String code_category, String bottom_code, String code_value, String emp_no) {
	HashMap<String, String> map = new HashMap<>();
	map.put("code_category", code_category);
	map.put("bottom_code", bottom_code);
	map.put("code_value", code_value);
	map.put("emp_no", emp_no);
	
	int temp = sql.selectOne("code.findBottomByNum",map);
		
	if(temp != 0 ) {
		return "false";
	} else {

		sql.insert("code.newBottomCode",map);
		return "true";
	}

}

//하위코드 변경
@RequestMapping(value="/updateBottomCode.cd", produces="text/html;charset=utf-8")
public void updateBottomCode(String vo) {
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
	CodeVO info = gson.fromJson(vo, CodeVO.class);

	sql.update("code.updateBottomCode",info);

}

//하위코드 삭제
@RequestMapping(value="/deleteBottomCode.cd", produces="text/html;charset=utf-8")
public void deleteBottomCode(String vo) {
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
	CodeVO info = gson.fromJson(vo, CodeVO.class);

	sql.delete("code.deleteBottomCode",info);

}
	
	
}
