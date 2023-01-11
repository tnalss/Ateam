package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import Attend.AttendVO;

@RestController
public class OrgController {
	@Autowired 
	@Qualifier("hanul")
	SqlSession sql;
	
	//전체 사원의 이름, 직급, 지점명, 부서명 조회 
			@RequestMapping(value="/org_all.org", produces="text/html;charset=utf-8")
			public String org_all() {
				List<AttendVO> list = sql.selectList("org.org_all");
				return new Gson().toJson(list).toString();				
			}	
	//전체 검색
			@RequestMapping(value="/org_all_r.org", produces="text/html;charset=utf-8")
			public String org_all_r(String keyword) {
				List<AttendVO> list = sql.selectList("org.org_all_r",keyword);
				return new Gson().toJson(list).toString();				
			}	
			
			
	//지점별 조회 
			@RequestMapping(value="/org_branch.org",produces="text/html;charset=utf-8")
			public String org_branch() {
				List<AttendVO> list = sql.selectList("org.org_branch");
				return new Gson().toJson(list).toString();
			}
			
	//지점 검색
			
			@RequestMapping(value="/org_banch_r.org", produces="text/html;charset=utf-8")
			public String org_branch_r() {
				List<AttendVO> list = sql.selectList("org.org_branch_r");
				return new Gson().toJson(list).toString();				
			}
			
	//	직급별 가나다 순으로 조회 & 이름도 가나다 순	
			@RequestMapping(value="/org_rank.org", produces="text/html;charset=utf-8")
			public String org_rank() {
				List<AttendVO> list = sql.selectList("org.org_rank");
				return new Gson().toJson(list).toString();
			}
			
		//직급 검색
			
			@RequestMapping(value="/org_rank_r.org", produces="text/html;charset=utf-8")
			public String org_rank_r() {
				List<AttendVO> list = sql.selectList("org.org_rank_r");
				return new Gson().toJson(list).toString();				
			}		
			
			
	//부서별 가나다 순으로 조회 & 직급 높-낮 & 이름 가나다 	
			@RequestMapping(value="/org_dept.org", produces="text/html;charset=utf-8")
			public String org_dept() {
				List<AttendVO> list = sql.selectList("org.org_dept");
				return new Gson().toJson(list).toString();
			}
			
	//부서 검색
			
			@RequestMapping(value="/org_dept_r.org", produces="text/html;charset=utf-8")
			public String org_dept_r() {
				List<AttendVO> list = sql.selectList("org.org_dept_r");
				return new Gson().toJson(list).toString();				
			}		
			
			
			
}
