package co.kr.jkcompany;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import notice.NoticePageVO;
import notice.NoticeVO;
import notice.ReplyVO;

@Controller
public class BoardController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;
	
	// 공지글수정저장처리 요청
//		@RequestMapping("/update.bo")
//		public String update(NoticeVO vo, NoticePageVO page, MultipartFile file, HttpServletRequest request) {
//			sql.update("no.update", vo);
//			// 응답화면연결
//			return "redirect:info.bo?id=" + vo.getBoard_no();
//		}
//
//		// 공지글수정화면 요청
//		@RequestMapping("/modify.bo")
//		public String modify(Model model, int id, NoticePageVO page) {
//			// DB에서 해당 공지글을 조회한다 ->화면에 출력하도록 Model에 attribute로 담는다
//			model.addAttribute("vo", sql.selectOne("no.info", id));
//			return "notice/modify";
//		}
//
//		// 공지글등록(신규저장)처리 요청
//		@RequestMapping("/insert.bo")
//		public String insert(NoticeVO vo, MultipartFile file, HttpServletRequest request) {
//			// 첨부된 파일이 있는 경우
//
//			sql.insert("bo.insert", vo);
//
//			// 응답화면연결 - 목록화면
//			return "redirect:list.bo";
//		}
//
//		// 신규공지글쓰기화면
//		@RequestMapping("/new.bo")
//		public String notice() {
//			return "notice/new";
//		}
//
//		// 공지글 삭제
//		@RequestMapping("/delete.bo")
//		public String delete(int id, NoticePageVO page, HttpSession session) {
//
//			sql.delete("no.delete", id);
//			return "redirect:list.bo";
//		}

		// 조회하고 출력하는 예제
		@RequestMapping(value = "/list.bo", produces = "text/html;charset=utf-8")
		public String notice_list(HttpSession session, Model model, NoticePageVO page) {
			// 각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
			session.setAttribute("board", "bo");
			model.addAttribute("page", board_list(page));
			return "board/list";
		}

		// 페이지 처리
		public NoticePageVO board_list(NoticePageVO page) {
			page.setTotalList(sql.selectOne("bo.total", page));
			page.setList(sql.selectList("bo.plist", page));
			return page;
		}

		// 제목으로 상세내용
		@RequestMapping(value = "/info.bo", produces = "text/html;charset=utf-8")
		public String notice_info(String id, Model model) {
			NoticeVO vo = sql.selectOne("bo.info", id);
			sql.update("bo.hits", id);
			List<ReplyVO> reply = sql.selectList("re.reply_list", id);
			model.addAttribute("vo", vo);
			model.addAttribute("notice", reply);
			return "board/info";
		}
	
//			//조회하고 출력하는 예제
//			@RequestMapping(value= "/list.bo" , produces="text/html;charset=utf-8")
//			public String notice_list(HttpSession session, Model model) {
//				//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
//				session.setAttribute("board", "bo" );
//				List<NoticeVO> list = sql.selectList("bo.list");
//				//조회해온 값을 모델로 list라는 곳에 담았습니다.
//				model.addAttribute("list", list);			
//				//리턴을 통해 employee 폴더에 list.jsp 를 찾아갑니다.
//				return "board/list";
//			}
	
}
