package co.kr.jkcompany;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonService;
import common.SimpleCode;
import ea.EaFileVO;
import ea.EaVO;

@Controller
public class EaController {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;

//	//조회하고 출력하는 예제
//		@RequestMapping(value= "/main.ea" , produces="text/html;charset=utf-8")
//		public String emp_list(HttpSession session, Model model) {
//			
//			//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
//			session.setAttribute("cate", "emp" );
//			
//			List<EmployeeVO> list = sql.selectList("emp.list");
//			
//			//조회해온 값을 모델로 list라는 곳에 담았습니다.
//			model.addAttribute("list", list);
//			
//			
//			//리턴을 통해 employee 폴더에 list.jsp 를 찾아갑니다.
//			return "employee/list";
//		}

	// 전자결재 홈화면
	@RequestMapping(value = "/main.ea", produces = "text/html;charset=utf-8")
	public String ea_main(Model model) {

		List<EaVO> list = sql.selectList("ea.recentlist", 2);
		model.addAttribute("list", list);

		// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "ea/main";
	}

	// 전자결재 상신작성 화면
	@RequestMapping(value = "/write.ea", produces = "text/html;charset=utf-8")
	public String ea_write(Model model) {

		List<SimpleCode> doc_list = sql.selectList("emp.codeList", "P");
		model.addAttribute("doc_list", doc_list);

		// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "ea/write";

	}

	// 상신함 화면
	@RequestMapping(value = "/draft.ea", produces = "text/html;charset=utf-8")
	public String ea_draft(Model model) {

		List<EaVO> draft_list = sql.selectList("ea.recentlist", 2);
		model.addAttribute("draft_list", draft_list);

		// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
		// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
		return "ea/draft";

	}
	
	// 결재함 화면
		@RequestMapping(value = "/sign.ea", produces = "text/html;charset=utf-8")
		public String ea_sign(Model model) {

			List<EaVO> sign_list = sql.selectList("ea.signboxlist", 2);
			model.addAttribute("sign_list", sign_list);

			// default를 앞에 붙여주고 폴더를 하나 거치면 tile 미적용으로 사이트가 연결됩니다.
			// 리턴으로 employee폴더에 list.jsp를 타일 미적용으로 연결시켰습니다.
			return "ea/sign";

		}
		
		//회수함 리스트
		@RequestMapping(value="/retry.ea", produces="text/html;charset=utf-8")
		public String ea_retry(Model model) {
			List<EaVO> retry_list = sql.selectList("ea.retryboxlist",1);
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
