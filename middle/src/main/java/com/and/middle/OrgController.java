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
			public String org_branch() {
				List<OrgVO> list = sql.selectList("org.org_branch");
				return new Gson().toJson(list).toString();
			}
	//지점별 조회한  사원 검색
			@RequestMapping(value="/org_branch_n.org",produces="text/html;charset=utf-8")
			public String org_branch_n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_n",keyword);
				return new Gson().toJson(list).toString();
			}
			//지점별_광주 나오도록  조회 
			@RequestMapping(value="/org_branch_1.org",produces="text/html;charset=utf-8")
			public String org_branch_1() {
				List<OrgVO> list = sql.selectList("org.org_branch_1");
				return new Gson().toJson(list).toString();
			}		
			
			//광주지점 내 사원 나오도록  조회 
			@RequestMapping(value="/org_branch_1n.org",produces="text/html;charset=utf-8")
			public String org_branch_1n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_1n",keyword);
				return new Gson().toJson(list).toString();
			}		
			
			//지점별_대구 나오도록  조회 
			@RequestMapping(value="/org_branch_2.org",produces="text/html;charset=utf-8")
			public String org_branch_2() {
				List<OrgVO> list = sql.selectList("org.org_branch_2");
				return new Gson().toJson(list).toString();
			}	
			
			//대구지점 내 사원 나오도록  조회 
			@RequestMapping(value="/org_branch_2n.org",produces="text/html;charset=utf-8")
			public String org_branch_2n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_2n",keyword);
				return new Gson().toJson(list).toString();
			}	
			//지점별_대전 나오도록  조회 
			@RequestMapping(value="/org_branch_3.org",produces="text/html;charset=utf-8")
			public String org_branch_3() {
				List<OrgVO> list = sql.selectList("org.org_branch_3");
				return new Gson().toJson(list).toString();
			}	
			//대전지점 내 사원 나오도록  조회 
			@RequestMapping(value="/org_branch_3n.org",produces="text/html;charset=utf-8")
			public String org_branch_3n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_3n",keyword);
				return new Gson().toJson(list).toString();
			}	
			//지점별_부산 나오도록  조회 
			@RequestMapping(value="/org_branch_4.org",produces="text/html;charset=utf-8")
			public String org_branch_4() {
				List<OrgVO> list = sql.selectList("org.org_branch_4");
				return new Gson().toJson(list).toString();
			}	
			//부산지점 내 사원 나오도록  조회 
			@RequestMapping(value="/org_branch_4n.org",produces="text/html;charset=utf-8")
			public String org_branch_4n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_4n",keyword);
				return new Gson().toJson(list).toString();
			}	
			//지점별_서울 나오도록  조회 
			@RequestMapping(value="/org_branch_5.org",produces="text/html;charset=utf-8")
			public String org_branch_5() {
				List<OrgVO> list = sql.selectList("org.org_branch_5");
				return new Gson().toJson(list).toString();
			}	
			//부산지점 내 사원 나오도록  조회 
			@RequestMapping(value="/org_branch_5n.org",produces="text/html;charset=utf-8")
			public String org_branch_5n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_5n",keyword);
				return new Gson().toJson(list).toString();
			}	
			//지점별_미지정 나오도록  조회 
			@RequestMapping(value="/org_branch_6.org",produces="text/html;charset=utf-8")
			public String org_branch_6() {
				List<OrgVO> list = sql.selectList("org.org_branch_6");
				return new Gson().toJson(list).toString();
			}	
			//지점미지정 내 사원 나오도록  조회 
			@RequestMapping(value="/org_branch_6n.org",produces="text/html;charset=utf-8")
			public String org_branch_6n(String keyword) {
				List<OrgVO> list = sql.selectList("org.org_branch_6n",keyword);
				return new Gson().toJson(list).toString();
			}	
				

			
			
	//부서별 검색
			@RequestMapping(value="/org_dept.org", produces="text/html;charset=utf-8")
			public String org_dept(String code) {
				List<OrgVO> list = sql.selectList("org.org_dept",code);
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
			
						
			
			
//	직급별 
	@RequestMapping(value="/org_rank.org", produces="text/html;charset=utf-8")
	public String org_rank(String code) {
		List<OrgVO> list = sql.selectList("org.org_rank",code);
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
