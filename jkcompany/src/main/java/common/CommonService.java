package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import employee.EmployeeVO;
import login.LoginVO;

@Service("common")
public class CommonService {
	@Autowired
	@Qualifier("hanul")
	SqlSession sql;
	//임시로그인을 위한 정보조회
	
	public EmployeeVO tempLogin(String id) {
		EmployeeVO vo = sql.selectOne("emp.tempLogin", id);
		return vo;
	}
	
	public String fileUpload(String category, MultipartFile file, HttpServletRequest request) {
		
		String path = "/Users/parkcj/Documents/app"+request.getContextPath();
		String upload = "/upload/"+category+"/"+ new SimpleDateFormat("yyyy_MM_dd").format(new Date()) ;
		
		path+=upload;
		

		File folder = new File(path);
		if( ! folder.exists() ) folder.mkdirs();
		

		String filename =  UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		try {
			file.transferTo( new File(path,filename) );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return appURL(request)+upload+"/"+filename;

	}
	
	//요청 url의 contextpath
	public String appURL(HttpServletRequest request) {
		return request.getRequestURL().toString().replace(request.getServletPath(),"");
	}
	
	//랜덤 번호 생성
	public String rand6num() {
		Random random = new Random(System.currentTimeMillis());

		int range = (int) Math.pow(10, 6);
		int trim = (int) Math.pow(10, 6 - 1);
		int result = random.nextInt(range) + trim;

		if (result > range) {
			result = result - trim;
		}

		return String.valueOf(result);
	}
		
//임시 비밀번호 이메일 전송처리
	public boolean sendPassword(EmployeeVO vo) {
		boolean send =true;
		HtmlEmail mail =  new HtmlEmail();
		mail.setCharset("utf-8");
		
	
		mail.setHostName("smtp.office365.com"); // 이메일 서버 지정
		mail.setAuthentication("hanul_jk@outlook.com", "1qlalfqjsgh1!");  // 관리자 이메일주소 ,비번
		mail.setStartTLSEnabled(true);
		mail.setSmtpPort(587);
		
		try {
			mail.setFrom("hanul_jk@outlook.com","관리자"); // 전송자 이메일 주소와 전송자 이름
			mail.addTo(vo.getEmail(),vo.getEmp_name());
			mail.setSubject("임시비밀번호입니다.");
			
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>[").append(vo.getEmp_name()).append("] 님의 임시 비밀번호</h3>");
			msg.append("<div>임시비밀번호가 발급되었습니다.</div>");
			msg.append("<div>사번 : ").append(vo.getEmp_no()).append("</div>");
			
			msg.append("<div>아래 임시 비밀번호로 로그인 하신 뒤 비밀번호를 변경하세요</div>");
			msg.append("<div><strong>").append(vo.getEmp_pw().toString()).append("</strong></div>");
			msg.append("</body>");
			msg.append("</html>");
			mail.setHtmlMsg(msg.toString());
			
			
			mail.send();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			send=false;
		}
		
		return send;
	}

}
