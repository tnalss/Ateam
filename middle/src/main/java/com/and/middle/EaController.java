package com.and.middle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ea.EaCodeVO;
import ea.EaVO;
import employee.EmployeeVO;

@RestController
public class EaController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;

	//기안 상세정보 조회
	@RequestMapping(value="/info.ea", produces="text/html;charset=utf-8")
	public String ea_info(String ea_num) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<EaVO> list = sql.selectList("ea.info", ea_num);
		return gson.toJson(list);	
	}
	
	//결재함 리스트
	@RequestMapping(value="/signboxlist.ea", produces="text/html;charset=utf-8")
	public String ea_signbox_list(String no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<EaVO> list = sql.selectList("ea.signboxlist",no);
		return gson.toJson(list);
	}
	
	//전자결재 상신하기
	@RequestMapping(value="/insert.ea", produces="text/html;charset=utf-8")
	public void ea_insert(String send_list) {
		System.out.println(new Date().getTime());
		List<EaVO> list = new Gson().fromJson(send_list, new TypeToken<List<EaVO>>() {}.getType());
	
		sql.insert("ea.insert",list);
	}
	
	//기안서 목록 불러오기
	@RequestMapping(value = "/form.ea", produces = "text/html;charset=utf-8")
	public String ea_select() {

		List<EaCodeVO> list = sql.selectList("ea.formlist");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// Gson gson = new Gson();

		return gson.toJson(list);
	}
	
	//최근순 기안 리스트
	@RequestMapping(value="/recent_all_list.ea", produces="text/html;charset=utf-8")
	public String ea_recent_list(String no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<EaVO> list = sql.selectList("ea.recentlist",no);
		return gson.toJson(list);
	}
	
	//카테고리에 맞는 값들 불러오기
	  @RequestMapping(value="/code_list.ea",produces="text/html;charset=utf-8") 
	  public String code_list(String cate){ 
		  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		  List<EaCodeVO> list = sql.selectList("code.list", cate);
	
		  return gson.toJson(list); }
	  
	  //사원 이름 검색
	  @RequestMapping(value="/name_search.ea", produces="text/html;charset=utf-8")
	  public  String ea_search_name(String name) {
		  List<EmployeeVO> list = sql.selectList("ea.name_search", name);
		  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		  return gson.toJson(list);
	  }
	  
	  //결재자 정보(사번, 이름, 부서, 계급) 불러오기.
	  @RequestMapping(value="/signer.ea", produces="text/html;charset=utf-8")
	  public  String ea_signer(String chipList) {
		 ArrayList<String> name = new Gson().fromJson(chipList,ArrayList.class);
		 HashMap<String,Object> map = new HashMap<String, Object>();
		 map.put("name", name);
		 List<EmployeeVO> list = sql.selectList("ea.signer",map);
		 
		  return new Gson().toJson(list);
	  }

}
