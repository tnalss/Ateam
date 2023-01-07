package com.and.middle;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import notice.NoticeVO;

@RestController
public class NoticeController {
	

	@Autowired @Qualifier("hanul") SqlSession sql;
	@RequestMapping(value= "/notice.no", produces="text/html;charset=utf-8")
	public String notice() {
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<NoticeVO> notice = sql.selectList("no.list"); 
		return gson.toJson(notice);
	}
	

	@RequestMapping(value= "/secret.no", produces="text/html;charset=utf-8")
	public String secret() {		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<NoticeVO> secret = sql.selectList("no.se_list"); 
		return gson.toJson(secret);
	}
	

	@RequestMapping(value="/insert.no", produces="text/html;charset=utf-8")
	public String insert(String vo) {
		System.out.println(vo);
		NoticeVO temp_vo = new Gson().fromJson(vo, NoticeVO.class);
		int cnt = sql.insert("no.se_insert", temp_vo);
		return new Gson().toJson(cnt).toString();
	}


	
}
