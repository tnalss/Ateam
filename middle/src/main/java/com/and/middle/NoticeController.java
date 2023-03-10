package com.and.middle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.CommonService;
import notice.NoticeFileVO;
import notice.NoticeVO;
import notice.ReplyVO;

@RestController
public class NoticeController {

	// 공지사항
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;

	@RequestMapping(value = "/notice.no", produces = "text/html;charset=utf-8")
	public String notice() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<NoticeVO> notice = sql.selectList("no.list");
		return gson.toJson(notice);
	}

	// 익명게시판
	@RequestMapping(value = "/secret.no", produces = "text/html;charset=utf-8")
	public String secret(String re) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<NoticeVO> secret = sql.selectList("no.se_list");		
		
		return gson.toJson(secret);
	}

	// 익명게시판 글쓰기 (+ 첨부파일)
	@RequestMapping(value = "/secinsert.no", produces = "text/html;charset=utf-8")
	public String insert(String param, HttpServletRequest req) {
		NoticeVO temp_vo = new Gson().fromJson(param, NoticeVO.class);
		MultipartRequest mReq = (MultipartRequest) req;
		List<MultipartFile> fileList = mReq.getFiles("file");
		String imgPath = null;
		ArrayList<NoticeFileVO> list = new ArrayList<>();
		
		for(int i = 0; i < fileList.size(); i++) {
			NoticeFileVO filevo = new NoticeFileVO();
			MultipartFile file = fileList.get(i);
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getName());
			filevo.setFile_name(file.getOriginalFilename());
			imgPath = new CommonService().fileUpload("no", file, req);
			filevo.setFile_path(imgPath);
			list.add(filevo);
		}
		
		temp_vo.setFileList(list);		
		int cnt = sql.insert("no.se_insert", temp_vo);
		if(list.size() > 0) {
			sql.insert("no.file_insert", temp_vo);
			System.out.println("여기옴?"+cnt);
		}
		return cnt + "";
	}
	
	// 익명게시판 글쓰기
		@RequestMapping(value = "/insert.no", produces = "text/html;charset=utf-8")
		public String insert(String vo) {
			NoticeVO temp_vo = new Gson().fromJson(vo, NoticeVO.class);	
			int cnt = sql.insert("no.se_insert", temp_vo);
			return new Gson().toJson(cnt).toString();
		}

	// 공지사항 글쓰기
	@RequestMapping(value = "/noinsert.no", produces = "text/html;charset=utf-8")
	public String noinsert(String vo) {
		NoticeVO temp_vo = new Gson().fromJson(vo, NoticeVO.class);
		int cnt = sql.insert("no.se_insert", temp_vo);
		return new Gson().toJson(cnt).toString();
	}

	// 익명게시판 수정
	@RequestMapping("/secupdate.no")
	public String secupdate(String no) {
		NoticeVO temp_vo = new Gson().fromJson(no, NoticeVO.class);
		int cnt = sql.update("no.update", temp_vo);
		return new Gson().toJson(cnt).toString();
	}

	// 공지사항 내용
	@RequestMapping(value = "/info.no", produces = "text/html;charset=utf-8")
	public String noticeinfo(String no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		NoticeVO vo = sql.selectOne("no.no_info", no);
		sql.update("no.hits", no);
		return gson.toJson(vo);
	}

	// 익명게시판 내용
	@RequestMapping(value = "/secinfo.no", produces = "text/html;charset=utf-8")
	public String secretinfo(String no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		NoticeVO vo = sql.selectOne("no.no_info", no);
		sql.update("no.hits", no);	
		
		return gson.toJson(vo);
	}

	// 공지사항/익명게시판 글삭제
	@RequestMapping(value = "/delete.no", produces = "text/html;charset=utf-8")
	public void nodelete(int board_no) {
		sql.delete("no.delete", board_no);
	}

	// 공지사항 댓글목록
	@RequestMapping(value = "/reply.no", produces = "text/html;charset=utf-8")
	public String reply(int board_no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<ReplyVO> reply = sql.selectList("re.reply_list", board_no);
		return gson.toJson(reply);
	}

	// 익명게시판 댓글목록
	@RequestMapping(value = "/sec_reply.no", produces = "text/html;charset=utf-8")
	public String sec_reply(int board_no) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<ReplyVO> reply = sql.selectList("re.reply_list", board_no);
		return gson.toJson(reply);
	}
	
	// 공지사항 / 익명게시판 댓글작성
	@RequestMapping(value="/re_insert.no", produces="text/html;charset=utf-8")
	public String replryinsert(String re) {
		ReplyVO temp_vo = new Gson().fromJson(re, ReplyVO.class);
		int cnt = sql.insert("re.reply_no_insert", temp_vo);
		return new Gson().toJson(cnt).toString();
	}	
	// 공지사항 / 익명게시판 댓글 삭제
	@RequestMapping(value="/reply_delete.no", produces="text/html;charset=utf-8")
	public void reply_delete(int reply_no) {
		sql.delete("re.reply_delete", reply_no);		
	}		
	// 공지사항 수정
	@RequestMapping(value="/reply_update.no", produces="text/html;charset=utf-8")
	public String reply_update(String re) {
		ReplyVO temp_vo = new Gson().fromJson(re, ReplyVO.class);
		int cnt = sql.update("re.reply_update", temp_vo);
		return new Gson().toJson(cnt).toString();
	}
	
	// 익명게시판 검색
	@RequestMapping(value="/search.no", produces="text/html;charset=utf-8")	
	public String search (String search) {
		List<NoticeVO> list = sql.selectList("no.search",search);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(list).toString();
	}
		
	@RequestMapping(value="/countReply.no", produces="text/html;charset=utf-8")	
	public String countReply (String board_no) {
		
		return sql.selectOne("re.countReply",board_no);
	}
	
	

	
@RequestMapping(value="/imageFile.no", produces="text/html;charset=utf-8")	
public String imageFile (String no) {
	 
	List<NoticeFileVO> list = sql.selectList("no.imageFile",no);
	if(list.size()!=0) {
		return new Gson().toJson(list);
	}
	
	return null;
}

		
}
