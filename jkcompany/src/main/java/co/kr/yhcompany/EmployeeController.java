package co.kr.yhcompany;

import java.util.HashMap;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.CommonService;
import employee.EmployeeVO;


@Controller
public class EmployeeController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	
	//조회하고 출력하는 예제
	@RequestMapping(value= "/list.emp" , produces="text/html;charset=utf-8")
	public String emp_list(HttpSession session, Model model) {
		
		//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
		session.setAttribute("cate", "emp" );
		
		List<EmployeeVO> list = sql.selectList("emp.list");
		
		//조회해온 값을 모델로 list라는 곳에 담았습니다.
		model.addAttribute("list", list);
		
		
		//리턴을 통해 employee 폴더에 list.jsp 를 찾아갑니다.
		return "employee/list";
	}
	
	
	// 매퍼없이 갈때 예제
	@RequestMapping(value= "/notile" , produces="text/html;charset=utf-8")
	public String notile(Model model) {
		List<EmployeeVO> list = sql.selectList("emp.list");
		model.addAttribute("list", list);
		
		//default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		//리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "default/employee/list";
	}
	
	
	
	
	
	// 사번으로 이름만 검색
	@RequestMapping(value= "/findNameByNo.emp" , produces="text/html;charset=utf-8")
	public String findNameByNo(String emp_no) {
		
		return sql.selectOne("emp.findNameByNo",emp_no);
	}
	
	
	
	@RequestMapping(value= "/fire.emp" , produces="text/html;charset=utf-8")
	public void fire_emp(String emp_no) {
		sql.update("emp.fire",emp_no);
	}
	
	@RequestMapping(value= "/info.emp" , produces="text/html;charset=utf-8")
	public String emp_info(String emp_no) {
		EmployeeVO vo = sql.selectOne("emp.info",emp_no);
		return new Gson().toJson(vo).toString();
	}
	
	//내정보에서 정보수정 (비밀번호변경)
	@RequestMapping(value="/myinfo_update.emp", produces="text/html;charset=utf-8")
	public void myinfo_update(String emp_vo) {
		EmployeeVO vo = new Gson().fromJson(emp_vo, EmployeeVO.class);
		sql.update("emp.myinfo_update", vo);
	}
	
	//회원가입
	@RequestMapping(value="/insert.emp" , produces="text/html;charset=utf-8")
	public String insert_emp(String param, HttpServletRequest req) {
		EmployeeVO vo = new Gson().fromJson(param, EmployeeVO.class);
		
		//첨부파일 
		MultipartRequest mReq= (MultipartRequest) req;
		MultipartFile file = mReq.getFile("file");
		if(file!=null) {
			String path = common.fileUpload("and",file,req);
			vo.setProfile_path(path);
		}
		
		String emp_pw = new CommonService().rand6num();
		vo.setEmp_pw(emp_pw);
		sql.insert("emp.insert",vo);
				
		//새로운사번 알려줘야됨
		HashMap<String, String> map = new HashMap<>();
		map.put("emp_name", vo.getEmp_name());
		map.put("email", vo.getEmail());
		////////////////////////////////////////////////이메일로 비밀번호 보내주는 처리 필요		
		String emp_no = sql.selectOne("emp.emp_no",map);
		
		//System.out.println(vo2.getAdmin());
		//이름에해당하는 코드 찾아주는 처리 필요
		
		String branch_code = sql.selectOne("code.whatCode",vo.getBranch_name());
		String dept_code = sql.selectOne("code.whatCode",vo.getDepartment_name());
		String rank_code = sql.selectOne("code.whatCode",vo.getRank_name());
		HashMap<String, String> codemap = new HashMap<>();
		codemap.put("branch_code", branch_code);
		codemap.put("dept_code", dept_code);
		codemap.put("rank_code", rank_code);
		codemap.put("emp_no", emp_no);
		sql.update("emp.codeInsert",codemap);
		
		EmployeeVO vo2 = sql.selectOne("emp.search_emp",map);
		
		return new Gson().toJson(vo2).toString();
	}
	
	//퇴사자?어드민? 조회
	@RequestMapping("/adminCode.emp")
	public String adminCode(String emp_no) {
		return sql.selectOne("emp.adminCode",emp_no);
	}
	
	//수정
	@RequestMapping(value="/update.emp" , produces="text/html;charset=utf-8")
	public String update_emp(String param, HttpServletRequest req) {
		EmployeeVO vo = new Gson().fromJson(param, EmployeeVO.class);
		
		//첨부파일 
		MultipartRequest mReq= (MultipartRequest) req;
		MultipartFile file = mReq.getFile("file");
		if(file!=null) {
			String path = common.fileUpload("and",file,req);
			vo.setProfile_path(path);
		}	
		
		sql.update("emp.update",vo);
		
		String branch_code = sql.selectOne("code.whatCode",vo.getBranch_name());
		String dept_code = sql.selectOne("code.whatCode",vo.getDepartment_name());
		String rank_code = sql.selectOne("code.whatCode",vo.getRank_name());
		HashMap<String, String> codemap = new HashMap<>();
		codemap.put("branch_code", branch_code);
		codemap.put("dept_code", dept_code);
		codemap.put("rank_code", rank_code);
		codemap.put("emp_no", vo.getEmp_no());
		sql.update("emp.orgUpdate",codemap);
		vo = sql.selectOne("emp.info",vo.getEmp_no());
		return new Gson().toJson(vo).toString();
	}
	
	@RequestMapping(value="/keyword.emp", produces="text/html;charset=utf-8")
	public String find_with_keyword(String keyword) {
		List<EmployeeVO> list = sql.selectList("emp.keyword",keyword);
		return new Gson().toJson(list).toString();
	}
	
	//아이디 찾기
	@RequestMapping(value="/findId.emp", produces="text/html;charset=utf-8")
	public String findId(String vo) {
		EmployeeVO info = new Gson().fromJson(vo, EmployeeVO.class);
	
		return sql.selectOne("lo.findId",info);
	}
	
	//패스워드찾기
	@RequestMapping(value="/findPw.emp", produces="text/html;charset=utf-8")
	public void findPw(String vo) {
		EmployeeVO info = new Gson().fromJson(vo, EmployeeVO.class);
		String pw = common.rand6num();
		info.setEmp_pw(pw);
		
		
		common.sendPassword(info);
		sql.update("lo.findPw",info);
	}
}
