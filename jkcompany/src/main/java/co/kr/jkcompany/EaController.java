package co.kr.jkcompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import common.CommonService;
import common.SimpleCode;
import ea.EaFileVO;
import ea.EaVO;
import employee.EmployeePageVO;
import employee.EmployeeVO;
import login.LoginVO;

@Controller
public class EaController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;


	// 전자결재 홈화면
	@RequestMapping(value = "/main.ea", produces = "text/html;charset=utf-8")
	public String ea_main(HttpSession session, Model model) throws Exception {
		session.setAttribute("category","ea");
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		if(vo ==null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			model.addAttribute("url", "login");
			return "ea/alert";
		}

		List<EaVO> list = sql.selectList("ea.recentlist", vo);
		model.addAttribute("list", list);
		
		// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "ea/main";
	}

	// 전자결재 상신작성 화면
	@RequestMapping(value = "/write.ea", produces = "text/html;charset=utf-8")
	public String ea_write(Model model, EmployeePageVO page) {
		
		List<SimpleCode> doc_list = sql.selectList("emp.codeList", "P");
		model.addAttribute("doc_list", doc_list);
		
		EmployeePageVO vo = emp_list(page);
		model.addAttribute("page", vo );
		// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "ea/write";

	}
	// 전자결재 상신작성 화면
		@ResponseBody
		@RequestMapping(value = "/write_sign_list.ea", produces = "text/html;charset=utf-8")
		public String ea_write_sign_list(EmployeePageVO page, int eapage) {
			

			page.setCurPage(eapage);
			EmployeePageVO vo = emp_list(page);
			//model.addAttribute("page", vo );
			// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
			// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
			return new Gson().toJson(vo);

		}


	// 페이지 처리
	public EmployeePageVO emp_list(EmployeePageVO page) {
		page.setTotalList( sql.selectOne("emp.total", page) ); 
		page.setList( sql.selectList("emp.plist", page) );
		return page;
	}
		// 전자결재 상신하기
		@ResponseBody
		@RequestMapping(value = "/write_insert.ea")
		public boolean ea_write_insert(HttpSession session, Model model, String ea_title, String ea_contents, String ea_doc, String[] ea_signer) {
			LoginVO login = (LoginVO)session.getAttribute("loginInfo");
			String no = login.getEmp_no();
			ArrayList<EaVO> list = new ArrayList<>();
			for(int i=0;i<ea_signer.length;i++) {
				EaVO vo = new EaVO();
				vo.setEmp_no(no);
				vo.setEa_title("["+ea_doc+"]"+ea_title);
				vo.setEa_content(ea_contents);
				vo.setEa_receiver(ea_signer[i]);
				list.add(vo);
			}
			sql.insert("ea.insert",list);
			return true;
		}
		
	
	// 상신함 화면
	@RequestMapping(value = "/draft.ea", produces = "text/html;charset=utf-8")
	public String ea_draft(HttpSession session, Model model) {
		
		LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
		List<EaVO> draft_list = sql.selectList("ea.recentlist", vo);
		model.addAttribute("draft_list", draft_list);

		// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "ea/draft";

	}
	
	// 결재함 화면
		@RequestMapping(value = "/sign.ea", produces = "text/html;charset=utf-8")
		public String ea_sign(HttpSession session, Model model) {
			LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
			List<EaVO> sign_list = sql.selectList("ea.signboxlist", vo);
			model.addAttribute("sign_list", sign_list);

			// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
			// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
			return "ea/sign";

		}
		
		//회수함 리스트
		@RequestMapping(value="/retry.ea", produces="text/html;charset=utf-8")
		public String ea_retry(HttpSession session, Model model) {
			LoginVO vo = (LoginVO) session.getAttribute("loginInfo");
			List<EaVO> retry_list = sql.selectList("ea.retryboxlist",vo);
			for(int i=0;i<retry_list.size();i++){
                if(i>0){
                    if(retry_list.get(i).getEa_num().equals(retry_list.get(i-1).getEa_num())){
                        retry_list.remove(i);
                    }
                }
			}
			model.addAttribute("retry_list",retry_list);
			return "ea/retry";
		}

		//문서대장
		@RequestMapping(value="/document.ea", produces="text/html;charset=utf-8")
		public String ea_document(Model model) {
			List<EaFileVO> flist = sql.selectList("ea.file_select_all");
			model.addAttribute("flist",flist);
			return "ea/document";
		}

		//문서 상태(대기,회수,결재완료,반려) 변경
		@RequestMapping(value="/update_status.ea", produces="text/html;charset=utf-8")
		public String ea_status_update(String ea_num, String ea_status) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("ea_num", ea_num);
			m.put("status", ea_status);
			sql.update("ea.status_update", m);
			return "redirect:draft.ea";
		}
		//회수함에서 회수기안 삭제
		@RequestMapping(value="/delete.ea", produces="text/html;charset=utf-8")
		public String ea_retry_delete(String ea_num, String ea_status) {
			sql.delete("ea.delete", ea_num);
			if(ea_status.equals("E4")) {
				return "redirect:draft.ea";
				
			}
			return "redirect:retry.ea";
		}
		
		
		//기안 상세정보 조회
		@RequestMapping(value="/info.ea", produces="text/html;charset=utf-8")
		public String ea_info(String ea_num, Model model, HttpSession session,String cnt) {
			List<EaVO> list = sql.selectList("ea.info", ea_num);
			
			LoginVO vo =(LoginVO)session.getAttribute("loginInfo");
			String form = list.get(0).getEa_title().substring(list.get(0).getEa_title().indexOf("[")+1,list.get(0).getEa_title().indexOf("]"));
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("form", form);
			map.put("ea_num",ea_num);
			map.put("emp_name", list.get(0).getEmp_name());
			map.put("emp_dep", list.get(0).getEa_dummy());
			map.put("title", list.get(0).getEa_title());
			map.put("content", list.get(0).getEa_content());
			map.put("date",list.get(0).getEa_date().toString());
			EmployeeVO evo = sql.selectOne("emp.info", list.get(0).getEmp_no());
			map.put("rank_name",  evo.getRank_name());
			model.addAttribute("map",map);
			
			
			for(int i=0;i<list.size();i++) {
					evo = sql.selectOne("emp.info", list.get(i).getEa_receiver());
					
					list.get(i).setEa_receiver_name(evo.getEmp_name());
					list.get(i).setEa_receiver_rank(evo.getRank_name());
					list.get(i).setEa_receiver_dep(evo.getDepartment_name());
			}
			
			model.addAttribute("info_list",list);
						
			if(cnt.equals("1")) {
				return "ea/infoDraft";
			}else if(cnt.equals("2")) {
				model.addAttribute("login", vo);
				return "ea/infoSign";
			}else if(cnt.equals("3")) {
				return "ea/infoRetry";
			}
			return "";
		}
	
		//문서 상태(대기,회수,결재완료,반려) 변경
		@ResponseBody
		@RequestMapping(value="/update_statuas.ea")
		public boolean ea_status_update(String ea_r_statuas, String ea_receiver, String ea_num, String total) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("ea_num", ea_num);
			m.put("ea_status", ea_r_statuas);
			m.put("emp_no", ea_receiver);
			int cnt = sql.selectOne("ea.howManySigned", ea_num);
			if(total.equals(cnt+"")) {
				sql.update("ea.allSignComplete",ea_num);
			}
			if(ea_r_statuas.equals("E2")) {
				sql.update("ea.statuas_update",m);
			}
			if (sql.update("ea.sign_status", m) > 0)
				return true;
			return false;
		}
		
//	//기안서 목록 불러오기
//		@RequestMapping(value = "/ea_file_select", produces = "text/html;charset=utf-8")
//		public String ea_file_select(String ea_num) {
//			
//			List<EaFileVO> list = sql.selectList("ea.file_select",ea_num);
//
//			return new Gson().toJson(list);
//		}
//	
//	//결재 및 반려 작업
//	@RequestMapping(value="/sign_status.ea", produces="text/html;charset=utf-8")
//	public void ea_sign_status(String ea_status,String emp_no, String ea_num) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		System.out.println(emp_no);
//		map.put("emp_no", emp_no);
//		map.put("ea_status", ea_status);
//		map.put("ea_num", ea_num);
//		sql.update("ea.sign_status", map);
//		
//	}
//	//회수함에서 상신 업데이트
//	@RequestMapping(value="/retry_draft.ea", produces="text/html;charset=utf-8")
//	public void ea_retry_draft(String num) {
//		sql.update("ea.retry_draft", num);
//	}
//	
//	//회수함에서 회수기안 삭제
//	@RequestMapping(value="/retry_delete.ea", produces="text/html;charset=utf-8")
//	public void ea_retry_delete(String num) {
//		sql.delete("ea.retry_delete", num);
//	}
//	
//	//회수함 리스트
//		@RequestMapping(value="/retryboxlist.ea", produces="text/html;charset=utf-8")
//		public String ea_retrybox_list(String no) {
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//			List<EaVO> list = sql.selectList("ea.retryboxlist",no);
//			return gson.toJson(list);
//		}
//	
//	//문서 상태(대기,회수,결재완료,반려) 변경
//	@RequestMapping(value="/update_status.ea", produces="text/html;charset=utf-8")
//	public void ea_status_update(String map) {
//		HashMap<String, Object> m = new Gson().fromJson(map, HashMap.class);
//		sql.update("ea.status_update", m);
//	}
//	
//	//기안 상세정보 조회
//	@RequestMapping(value="/info.ea", produces="text/html;charset=utf-8")
//	public String ea_info(String ea_num) {
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//		List<EaVO> list = sql.selectList("ea.info", ea_num);
//		return gson.toJson(list);	
//	}
//	
//	//결재함 리스트
//	@RequestMapping(value="/signboxlist.ea", produces="text/html;charset=utf-8")
//	public String ea_signbox_list(String no) {
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//		List<EaVO> list = sql.selectList("ea.signboxlist",no);
//		return gson.toJson(list);
//	}
//	
//	//전자결재 상신하기
//	@RequestMapping(value="/insert.ea", produces="text/html;charset=utf-8")
//	public void ea_insert(String send_list) {
//		
//		List<EaVO> list = new Gson().fromJson(send_list, new TypeToken<List<EaVO>>() {}.getType());
//	
//		sql.insert("ea.insert",list);
//	}
//	//전자결재(첨부파일)포함 상신하기
//		@RequestMapping(value="/ea_insert.fi", produces="text/html;charset=utf-8")
//		public void ea_insert_file(String param, HttpServletRequest req) {
//			
//			ArrayList<EaVO> list = new Gson().fromJson(param, new TypeToken<List<EaVO>>() {}.getType());
//			ArrayList<EaFileVO> flist = new ArrayList<EaFileVO>();
//			
//			MultipartRequest mReq = (MultipartRequest) req;
//			List<MultipartFile> fileList = mReq.getFiles("file");
//			String imgPath = null;
//			
//			for(int i=0; i< fileList.size();i++) {
//				EaFileVO f_vo = new EaFileVO();
//				MultipartFile file=  fileList.get(i); // getFiles 또는 getFileMap활용.
//				System.out.println(file.getOriginalFilename());
//				System.out.println(file.getName());
//				f_vo.setFile_name(file.getOriginalFilename());
//				imgPath = common.fileUpload("and", file, req);
//				f_vo.setFile_path(imgPath);
//				flist.add(f_vo); 
//			}
//		
//			sql.insert("ea.insert",list);
//			sql.insert("ea.file_insert",flist);
//			
//		}
//	
//	//기안서 목록 불러오기
//	@RequestMapping(value = "/form.ea", produces = "text/html;charset=utf-8")
//	public String ea_select() {
//
//		List<EaCodeVO> list = sql.selectList("ea.formlist");
//
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//		// Gson gson = new Gson();
//
//		return gson.toJson(list);
//	}
//	
//	//최근순 기안 리스트
//	@RequestMapping(value="/recent_all_list.ea", produces="text/html;charset=utf-8")
//	public String ea_recent_list(String no) {
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//		List<EaVO> list = sql.selectList("ea.recentlist",no);
//		return gson.toJson(list);
//	}
//	
//	//카테고리에 맞는 값들 불러오기
//	  @RequestMapping(value="/code_list.ea",produces="text/html;charset=utf-8") 
//	  public String code_list(String cate){ 
//		  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//		  List<EaCodeVO> list = sql.selectList("code.list", cate);
//	
//		  return gson.toJson(list); }
//	  
//	  //사원 이름 검색
//	  @RequestMapping(value="/name_search.ea", produces="text/html;charset=utf-8")
//	  public  String ea_search_name(String name) {
//		  List<EmployeeVO> list = sql.selectList("ea.name_search", name);
//		  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
//		  return gson.toJson(list);
//	  }
//	  
//	  //결재자 정보(사번, 이름, 부서, 계급) 불러오기.
//	  @RequestMapping(value="/signer.ea", produces="text/html;charset=utf-8")
//	  public  String ea_signer(String chipList) {
//		 ArrayList<String> name = new Gson().fromJson(chipList,ArrayList.class);
//		 HashMap<String,Object> map = new HashMap<String, Object>();
//		 map.put("name", name);
//		 List<EmployeeVO> list = sql.selectList("ea.signer",map);
//		 
//		  return new Gson().toJson(list);
//	  }
//	  
//	  //결재자 정보(사번, 이름, 부서, 계급) 불러오기.
//	  @RequestMapping(value="/allSignComplete.ea", produces="text/html;charset=utf-8")
//	  public void allSignComplete(String ea_num) {
//		  sql.update("ea.allSignComplete",ea_num);
//	  }
//	  
//	  //결재자 몇명인지
//	  @RequestMapping("/howManySigned.ea")
//	  public Object howManySigned(String ea_num) {
//		  return sql.selectOne("ea.howManySigned",ea_num);
//	  }
//
}
