package co.kr.yhcompany;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	//org.apache.commons.dbcp2.BasicDataSource
	//org.mybatis.spring.SqlSessionFactoryBean
	//org.mybatis.spring.SqlSessionTemplate
	//org.springframework.web.servlet.view.tiles3.TilesViewResolver
	//org.springframework.web.servlet.view.tiles3.TilesConfigurer
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		
		  //암호화하지 않은 비번에 대해 암호화해서 저장하는 처리 ------
		//List<MemberVO> list =
		  //member.member_list(); for(MemberVO vo : list ) { 
			  //비밀번호가 있는 회원에 대해 암호화에 사용할
		 //salt를 만든다 
			//  if( vo.getUserpw()!=null ) { String salt = common.generateSalt();
		  //String pw = common.getEncrypt(salt, vo.getUserpw()); vo.setSalt(salt);
		 //vo.setUserpw(pw); member.member_password_update(vo); } }
		 //-----------------------------------------------
		 
		
		//session.setAttribute("category", "");
		session.removeAttribute("category");
		return "home";
	}
	
}
