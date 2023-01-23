package co.kr.jkcompany;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonService;
import employee.EmployeePageVO;
import employee.EmployeeVO;
import notice.NoticePageVO;
import notice.NoticeVO;

@Controller
public class NoticeController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
		//조회하고 출력하는 예제
//		@RequestMapping(value= "/list.no" , produces="text/html;charset=utf-8")
//		public String notice_list(HttpSession session, Model model) {
//			//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
//			session.setAttribute("notice", "no" );
//			List<NoticeVO> list = sql.selectList("no.list");
//			//조회해온 값을 모델로 list라는 곳에 담았습니다.
//			model.addAttribute("list", list);			
//			//리턴을 통해 employee 폴더에 list.jsp 를 찾아갑니다.
//			return "notice/list";
//		}
	
	// 조회하고 출력하는 예제
		@RequestMapping(value = "/list.no", produces = "text/html;charset=utf-8")
		public String notice_list(HttpSession session, Model model,NoticePageVO page) {

			// 각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
			session.setAttribute("notice", "no");
			model.addAttribute("page", notice_list(page) );
			return "notice/list";
		}
		
		
		// 페이지 처리
		public NoticePageVO notice_list(NoticePageVO page) {
			page.setTotalList( sql.selectOne("no.total", page) ); 
			page.setList( sql.selectList("no.plist", page) );
			return page;
		}
		
		
		// 제목으로 상세내용
		@RequestMapping(value= "/info.no" , produces="text/html;charset=utf-8")
		public String emp_info(String id, Model model) {
			NoticeVO vo = sql.selectOne("no.info",id);
			model.addAttribute("vo", vo);
			return "notice/info";
		}
}
