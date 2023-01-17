package com.and.middle;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


import Org.OrgVO;

@RestController
public class OrgController {
	@Autowired 
	@Qualifier("hanul")
	SqlSession sql;
	
	//전체 사원의 이름, 직급, 지점명, 부서명 조회 
			@RequestMapping(value="/org_all.org", produces="text/html;charset=utf-8")
			public String org_all() {
				List<OrgVO> list = sql.selectList("org.org_all");
				return new Gson().toJson(list).toString();				
			}	
	//전체 검색
			@RequestMapping(value="/org_all_r.org", produces="text/html;charset=utf-8")
			public String org_all_r(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_all_r",keyword);
				return new Gson().toJson(list).toString();				
			}				
			
	//지점별 조회 
			@RequestMapping(value="/org_branch.org",produces="text/html;charset=utf-8")
			public String org_branch(String code) {
				List<OrgVO> list = sql.selectList("org.org_branch",code);
				return new Gson().toJson(list).toString();
				
			}
			
			//부서별 검색
			@RequestMapping(value="/org_dept.org", produces="text/html;charset=utf-8")
			public String org_dept(String code) {
				List<OrgVO> list = sql.selectList("org.org_dept",code);
				return new Gson().toJson(list).toString();
			}
						
			//직급별 검색
		
			@RequestMapping(value="/org_rank.org", produces="text/html;charset=utf-8")
			public String org_rank(String code) {
				List<OrgVO> list = sql.selectList("org.org_rank",code);
				return new Gson().toJson(list).toString();
			}
					
			
			
	//지점내 사원 검색 
			@RequestMapping(value="/org_branch_n.org",produces="text/html;charset=utf-8")
			public String org_branch_n(String keyword,String code) {
				HashMap<String,String> map = new HashMap<>();
				map.put("keyword", keyword);
				map.put("code", code);
				List<OrgVO> list = sql.selectList("org.org_branch_n",map);
				return new Gson().toJson(list).toString();
			}
	
	
	
			//부서내 사원검색
			@RequestMapping(value="/org_dept_n.org", produces="text/html;charset=utf-8")
			public String org_dept_n(String keyword, String code) {				
				HashMap<String,String> map = new HashMap<>();
				map.put("keyword", keyword);
				map.put("code", code);
				List<OrgVO> list = sql.selectList("org.org_dept_n",map);				
				return new Gson().toJson(list).toString();				
			}	
			
						
			
			

	
	//직급내 사원검색
	@RequestMapping(value="/org_rank_n.org", produces="text/html;charset=utf-8")
	public String org_rank_n(String keyword, String code) {
		HashMap<String,String> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("code", code);
		List<OrgVO> list = sql.selectList("org.org_rank_n",map);		
		return new Gson().toJson(list).toString();				
	}	
			
			
			
}
