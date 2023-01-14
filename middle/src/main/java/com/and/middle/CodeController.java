package com.and.middle;

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
	
	
}
