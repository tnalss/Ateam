package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ea.EaCodeVO;

@RestController
public class EaController {
	@Autowired @Qualifier("hanul") SqlSession sql;
	
	@RequestMapping(value="/form.ea",produces="text/html;charset=utf-8")
	public String ea_select() {
		
		List<EaCodeVO> list = sql.selectList("ea.formlist");

		return new Gson().toJson(list);
	}
	
}
