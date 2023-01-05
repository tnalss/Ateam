package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ea.EaCodeVO;
import ea.EaVO;

@RestController
public class EaController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;

	@RequestMapping(value = "/form.ea", produces = "text/html;charset=utf-8")
	public String ea_select() {

		List<EaCodeVO> list = sql.selectList("ea.formlist");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// Gson gson = new Gson();

		return gson.toJson(list);
	}
	@RequestMapping(value="/recent_all_list.ea", produces="text/html;charset=utf-8")
	public String ea_recent_list(String no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<EaVO> list = sql.selectList("ea.recentlist",no);
		return gson.toJson(list);
	}
	
	  @RequestMapping(value="/code_list.ea",produces="text/html;charset=utf-8") 
	  public String code_list(String cate){ 
		  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		  List<EaCodeVO> list = sql.selectList("code.list", cate);
	
		  return gson.toJson(list); }
	  
	  //사원 이름 검색
	  @RequestMapping(value="/name_search.ea", produces="text/html;charset=utf-8")
	  public  String ea_search_name(String name) {
		  
		  return "";
	  }

}
